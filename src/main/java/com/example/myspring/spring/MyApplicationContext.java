package com.example.myspring.spring;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.beans.Introspector;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description: 模拟spring启动   扫描 -》 创建定义类 —》 通过定义类创建bean —》 单例池。。。
 * 生命周期：
 * 1.实例化普通对象 存入一级缓存，并在creatingSeting中存入beanName，表示正在创建bean
 * 2.依赖注入      如果存在循环依赖，从二级缓存中找到需要注入的代理对象，如果找不到使用一级缓存中的lambda表达式创建代理对象注入，并存入二级缓存
 * 3.填充普通对象的其他属性...
 * 4.根据注解传入普通对象AOP， 如果二级缓存中有代理对象，则跳过这步，保证代理对象的单例
 * 5.其他步骤，初始化前...后...
 * 6.从二级缓存中取代理对象，这个代理对象就含有经过前面几步的完整生命周期的普通对象
 * 7.添加入单例池
 * 8.移除creatingSeting中定义
 *
 * 三级缓存作用：
 * 1.singletonObjects      存放真正的单例bean,从二级缓存中取                                       《beanName，bean》
 * 2.earlysingletonobjects 存放bean的代理对象，从一级缓存取，没有经过完整的生命周期，内部属性存在null     《beanName，ProxyBean》
 * 3.singletonfactory      存放bean的普通对象                                                  《beanName，lambda<bean>》
 *
 * @createTime: 2022/6/22 10:30
 **/
@Slf4j
public class MyApplicationContext {
    private Class<?> config;

    private ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    private ConcurrentHashMap<String, Object> singletonObjects = new ConcurrentHashMap<>();

    private ArrayList<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();

    /**
    构造方法，1.获取配置类上的注解，特别是扫描路径，进行指定包下的类扫描，创建每个类对应的定义类
                解析扫描路径为绝对路径 —》 处理绝对路径下的.class文件 —》 根据注解处理Component则创建定义类
            2.创建单例bean的实例，加入单例池
              根据beanDefinitionMap创建单例bean
     */
    @SneakyThrows
    public MyApplicationContext(Class<?> config) {
        this.config = config;
        //扫描注解,解析地址
        if (config.isAnnotationPresent(ComponentScan.class)) {
            ComponentScan componentScan = config.getAnnotation(ComponentScan.class);
            //得到注解中的值，进行路径解析
            String path = componentScan.path(); //com.example.myspring.service
            //转为文件格式，类路径
            path = path.replace(".", "/");//com/example/myspring/service
            //随便得到一个类加载器
            ClassLoader classLoader = this.getClass().getClassLoader();
            //得到注解标明的值所在的  绝对路径（类路径 + 目标资源文件类路径）
            //com.example.myspring.service -》file:/F:/work/javaIDEA/StudyProject/shirodemo/target/classes/com/example/myspring/service
            URL resource = classLoader.getResource(path);
            String replace = resource.toString().replace("file:/", "");
            //用文件，资源来表示
            File file = new File(replace); //file:\F:\work\javaIDEA\StudyProject\shirodemo\target\classes\com\example\myspring\service
            //如果解析到的路径是文件夹
            if (file.isDirectory()) {
                //遍历文件夹中的所有文件，寻找.class文件进行扫描
                for (File listFile : file.listFiles()) {
                    //得到文件夹下文件的绝对路径
                    String fileAbsolutePath = listFile.getAbsolutePath(); //F:\work\javaIDEA\StudyProject\shirodemo\target\classes\com\example\myspring\service\AppConfig.class
                    //如果是class文件，判断是否有component注解
                    if (fileAbsolutePath.endsWith(".class")) {
                        //截取处理文件路径，转为加载器可以识别的类名格式（截取com到.class，简化版） com.example.myspring.service.AppConfig
                        String className =
                                fileAbsolutePath.substring(fileAbsolutePath.indexOf("com"), fileAbsolutePath.indexOf(".class"));
                        className = className.replace("\\", ".");

                        //通过类加载器，将文件下符合要求的子文件生成为Class类
                        Class<?> aClass = classLoader.loadClass(className);
                        //判断是否有Component注解
                        if (aClass.isAnnotationPresent(Component.class)) {
                            //判断是否实现了BeanPostProcessor接口，用于bean生命周期，调用初始化前、后方法
                            if (BeanPostProcessor.class.isAssignableFrom(aClass)) {
                                BeanPostProcessor beanPostProcessor = (BeanPostProcessor) aClass.newInstance();
                                beanPostProcessorList.add(beanPostProcessor);
                            }


                            Component component = aClass.getAnnotation(Component.class);

                            //这里考虑到多例bean，不能直接创建对象,应该创建一个定义类来描述需要创建的类并存入map供后续使用。
                            BeanDefinition beanDefinition = new BeanDefinition();
                            //类型就是通过加载器生成的类型
                            beanDefinition.setType(aClass);
                            //是否有Scope注解
                            if (aClass.isAnnotationPresent(Scope.class)) {
                                //有则封装对应的值
                                Scope scope = aClass.getAnnotation(Scope.class);
                                String scopeValue = scope.scope();
                                beanDefinition.setScope(scopeValue);
                            } else {
                                //没有默认为单例singletion
                                beanDefinition.setScope("singletion");
                            }
                            //处理完成后放入map中，key就是beanName，Value是定义类
                            //得到指定beanName
                            String beanName = component.beanName();
                            if ("".equals(beanName)) {
//                                String name = aClass.getName();
//                                beanName = aClass.getName().substring(name.lastIndexOf(".") + 1, name.length());
                                beanName = Introspector.decapitalize(aClass.getSimpleName());
                            }
                            beanDefinitionMap.put(beanName, beanDefinition);
                        }

                    }
                }
            }
        }
        //扫描完成，定义类Map初始化成功，创建单例bean
        for (String key : beanDefinitionMap.keySet()) {
            BeanDefinition beanDefinition = beanDefinitionMap.get(key);
            //如果是单例的，创建。否则不处理
            if (beanDefinition.getScope().equals("singletion")) {
                Object bean = this.createBean(key, beanDefinition);
                //存到单例池（map）中
                singletonObjects.put(key, bean);
            }
        }


    }

    /**
        创建bean,并进行依赖注入，调用初始化、前、后方法
     */
    @SneakyThrows
    private Object createBean(String beanName, BeanDefinition beanDefinition) {
        //得到事先在定义类中bean对应的真正类型
        Class<?> type = beanDefinition.getType();
        //得到无参构造方法
        Constructor<?> constructor = type.getConstructor();
        //实例化
        Object bean = constructor.newInstance();
        //为加了Autowired字段依赖注入
        Class<?> aClass = bean.getClass();
        //得到类下的所有字段
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field field :declaredFields) {
            //判断是否有@Autowired注解
            if (field.isAnnotationPresent(Autowired.class)) {
                //爆破私有属性
                field.setAccessible(true);
                //注入，调用getBean方法
                Object needBean = this.getBean(field.getName());
                field.set(bean,needBean);
            }
        }
        //回调，为实现BeanName接口的类注入beanName
        if (bean instanceof BeanName) {
            BeanName bean1 = (BeanName) bean;
            bean1.setBeanName(beanName);
        }
        //初始化前,在扫描中已经创建了beanPostProcessor实例 还要处理@PostConstruct等等注解
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
            //调用初始化前方法
            beanPostProcessor.postProcessBeforeInitialization(beanName,bean);
        }
        //初始化，调用初始化方法
        if (bean instanceof InitializingBean) {
            InitializingBean bean1 = (InitializingBean) bean;
            bean1.afterPropertiesSet();
        }
        //初始化后
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
            //使用cglib代理
            bean = beanPostProcessor.postProcessAfterInitialization(beanName,bean);
        }
        return bean;
    }

    /**
        得到一个bean，若为单例则从单例池获取，获取不到则调用createBean （内部字段的成员类还没有实例化）
     若为多例，每次都调用createBean返回
     */
    public Object getBean(String beanName) {
        Object object = null;
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) {
            throw new NullPointerException("对应beanName的Bean不存在");
        } else {
            //判断需要的bean是否为单例的
            if (beanDefinition.getScope().equals("singletion")) {
                //单例从单例池中获取
                object = singletonObjects.get(beanName);
                if (object == null) {
                    object = this.createBean(beanName, beanDefinition);
                    Assert.notNull(object, "error");
                    singletonObjects.put(beanName, object);
                }
            } else if (beanDefinition.getScope().equals("prototype")) {
                //多例调用createBean返回对应对象
                object = this.createBean(beanName, beanDefinition);
            }
        }
        return object;
    }
}
