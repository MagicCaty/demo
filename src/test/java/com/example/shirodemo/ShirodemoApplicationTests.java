package com.example.shirodemo;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.shirodemo.entity.User;
import com.example.shirodemo.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

//@SpringBootTest
@Slf4j
class ShirodemoApplicationTests {

    @Test
    public void test56() {
        String a = "a";
        String b = new String("a");
        String intern = b.intern();
        System.out.println(intern);
        System.out.println(a.intern() == b.intern());
    }
    @Test
    public void test55() {
        Parent parent = new Parent();
        System.out.println(parent);
        String s = JSON.toJSONString(parent);
        System.out.println(s);
    }

    @Test
    public void test54() {
        LocalDateTime now = LocalDateTime.now();

        List<Student> studentList = Arrays.asList(
                new Student("1", now.toString(), "张三", 18, 20, null),
                new Student("2", now.toString(), "李四", 20, 21, null)
        );
        List<Course> courseList = Arrays.asList(
                new Course("1", "语文"),
                new Course("1", "数学"),
                new Course("1", "英语"),
                new Course("2", "化学")
        );

        //创建studentMap为下面的组合准备
        Map<String, Student> studentMap = studentList.stream()
                .collect(Collectors.toMap(Student::getId, Function.identity()));

        //外层循环课程list
        courseList.forEach(course -> {
            //内存循环studentMap，判断两个集合id是否重复
            studentMap.computeIfPresent(course.getId(), (id, student) -> {
                //用Optional包装 ofNullable根据参数是否又值返回 有值或者空的Optional
                // 再orElseGet判断Optional是否为空，有值则返回包装的值 否则执行函数
                List<Course> courses = Optional.ofNullable(student.getCourseList()).orElseGet(() -> {
                    //每一个学生第一次执行getCourseList都是空的，所以手动new一个List
                    ArrayList<Course> courseArrayList = new ArrayList<>();
                    student.setCourseList(courseArrayList);
                    return courseArrayList;
                });
                courses.add(course);
                return student;
            });
        });
        //手动分页
        int no = 2;
        int size = 1;
        int total = studentList.size();

        Page<Student> studentPage = new Page<>(no, size, total);
        studentPage.setRecords(studentList.stream().skip(Math.max(0, (long) (no - 1) * size)).limit(size).collect(Collectors.toList()));
        System.out.println(studentPage.getRecords());
    }


    @Resource
    private ApplicationContext applicationContext;

    @Test
    public void test53() {
        ArrayList<Object> objects = new ArrayList<>();
        Student student = new Student();
        student.setAge(18);
        objects.add(student);
        student.setAge(88);
        objects.add(student);
        objects.forEach(System.out::println);
    }

    @Test
    public void test52() {
//        DateTimeFormatter.ofPattern()
        Student student = new Student();
        student.setAge(18);
        try {
            Method getAge = student.getClass().getDeclaredMethod("getAge");
            Integer invoke = (Integer) getAge.invoke(student);
            System.out.println(invoke);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void TEST51() {
//        userService.save(new User("6", "测试time2", "123", "123",LocalDateTime.now(), 16));
//        List<User> list = userService.lambdaQuery().ge(User::getGranularityMinute, LocalDateTime.now().plusDays(-1)).list();
//        List<User> list1 = userService.list();
//        System.out.println(list1);
        List<User> userList = userService.getBaseMapper().getUserList();
        System.out.println(userList);
    }

    @Test
    public void test51() {
        BigDecimal decimal1 = BigDecimal.ONE;
        System.out.println(decimal1);
    }

    @Test
    public void test50() {
        List<Integer> collect = Arrays.asList(1, 2, 3, 4).stream().filter(e -> {
            if (e == 1) {
                return false;
            }
            return e > 0;
        }).collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void test49() {
        String str = "1,2,3,4";
        String[] split = str.split(",");
        List<String> strings = Arrays.asList(split);
        strings.forEach(System.out::println);
    }


    @Test
    public void test48() {
//        "12".startsWith()
        System.out.println("12" + null);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime 第一周的星期一 = now.with(TemporalAdjusters.dayOfWeekInMonth(1, DayOfWeek.MONDAY));
        LocalDateTime of = LocalDateTime.of(LocalDate.from(第一周的星期一), LocalTime.MIN);
        System.out.println(of);
    }


    @Test
    public void test47() {
        LocalDateTime begin = LocalDateTime.now().with(LocalDateTime.MIN);
        List<LocalDateTime> rangeDateTime = getRangeDateTime(10, begin);
        System.out.println(rangeDateTime);
    }

    private final static ZoneId ZONE_ID = ZoneId.of("Asia/Shanghai");

    private static List<LocalDateTime> getRangeDateTime(int minute, LocalDateTime begin) {
        LocalDateTime now = LocalDateTime.now(ZONE_ID);
        LocalDateTime today = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 0, 0, 0, 0);
        LocalDateTime beginTime = LocalDateTime.of(begin.getYear(), begin.getMonth(), begin.getDayOfMonth(), 0, 0, 0, 0);
        today.getLong(ChronoField.MINUTE_OF_HOUR);
        int size = 24 * 60 / minute;
        List<LocalDateTime> returnList = new ArrayList<>(size);


        LocalDateTime timeToList = today;
        for (int i = 0; i < size; i++) {
            timeToList = timeToList.plusMinutes(minute);
            returnList.add(timeToList);
        }
        return returnList;
    }

    @Test
    public void test46() {
        Student student1 = new Student("14", "2022-04-15", "张三", 18, 50, null);
        Student student2 = new Student("14", "2022-04-15", "张三", 18, 49, null);
        Student student3 = new Student("14", "2022-04-16", "张三", 18, 10, null);
        Student student4 = new Student("15", "2022-05-20", "李四", 20, 12, null);
        Student student5 = new Student("15", "2022-05-21", "李四", 20, 63, null);
        List<Student> students = Arrays.asList(student1, student2, student3, student4, student5);
        HashMap<String, HashMap<LocalDate, Integer>> stringHashMapHashMap = new HashMap<>();
        for (Student student : students) {
            HashMap<LocalDate, Integer> itemMap = stringHashMapHashMap.computeIfAbsent(student.getId(), key -> new HashMap());
            LocalDate localDate = LocalDate.parse(student.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            itemMap.merge(localDate, student.getFraction(), Integer::sum);
        }
        System.out.println(stringHashMapHashMap); //{14={2022-04-15=99, 2022-04-16=100}, 15={2022-05-21=63, 2022-05-20=12}}
        boolean 张三 = students.stream().allMatch(e -> Objects.equals("张三", e.getName()));
        System.out.println(张三);
    }

    @Test
    public void test45() {
        ArrayList<Double> doubleArrayList = new ArrayList<>();
        extendsM(doubleArrayList);     //合法，逆变后只要是Number的子类就行

        ArrayList<Integer> integerArrayList = new ArrayList<>();
        extendsM(integerArrayList);    //合法，逆变后只要是Number的子类就行
    }

    public void extendsM(ArrayList<? extends Number> objects) {
        Number number = objects.get(0); //正确，但具体类型只能强转
//        objects.add(2); //报错，由于无法确定元素类型，所以无法加入元素
    }

    @Test
    public void test43() {
        ArrayList<Number> objects = new ArrayList<>();
        objects.add(3.1);
        Integer integer = superM(objects);
        System.out.println(integer);
    }

    public Integer superM(ArrayList<? super Integer> objects) {
        objects.add(1);
        objects.add(2);
        Object object = objects.get(2);
        Integer integer = (Integer) object; //只能获取Object类型
        return integer;
    }

    @Test
    public void test42() {
        System.out.println(sumNum(3));
    }

    public int sumNum(int n) {
        if (n == 1) {

            return 1;

        }

        return n + sumNum(n - 1);
    }

    @Test
    public void test41() {
        double d = 11.6;
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(d));
        BigDecimal bigDecimal1 = bigDecimal.setScale(0, RoundingMode.HALF_UP);
        System.out.println(bigDecimal1.intValue());
    }

    @Test
    public void test40() {
        String time1 = "1999-12-12 23:30:30";
        String time2 = "2022-4-10 10:30:30";
        System.out.println(time1.compareTo(time2));
    }

    @Test
    public void testMyTimeConflict() {
        ArrayList<String> list = new ArrayList<>();
        list.add("08:00-09:00");
        list.add("09:00-12:00");
        list.add("18:00-20:00");
        list.add("13:00-16:30");
//        list.add("14:00-16:30");
        list.add("16:30-17:00");
        list.forEach(System.out::println);
        System.out.println("========排序分割符=======");
        //是否冲突标志位
        boolean isConflict = false;
        Collections.sort(list);
        list.forEach(System.out::println);
        for (int i = 0; i < list.size(); i++) {
            //最后一个时间段不需要比较了
            if (i == list.size()) {
                break;
            }
            String[] nextTimes = list.get(i).split("-");
            for (int j = 0; j < list.size(); j++) {
                //不和自己比较以及只和自己之后的时间比较
                if (i == j || i > j) {
                    continue;
                }
                String[] lastTimes = list.get(j).split("-");
                //拼接开始时间和结束时间为一个完成的时间格式
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDateTime now = LocalDateTime.now();
                String strNowTime = dateTimeFormatter.format(now);
                String endTime = strNowTime + " " + nextTimes[1] + ":00"; //["18:00", "20:00"] -> 2022-04-11 20:00:00
                String startTime = strNowTime + " " + lastTimes[0] + ":00"; //["18:00", "20:00"] -> 2022-04-11 18:00:00
                //time1 > time2 则返回 1
                if (endTime.compareTo(startTime) > 0) {
                    isConflict = true;
                    break;
                }
            }
            if (isConflict) {
                break;
            }
        }
        if (isConflict) {
            System.out.println("时间段冲突");
        } else {
            System.out.println("时间段无冲突");
        }

    }

    @Test
    public void test() {
        List<String> list = new ArrayList<String>();
        list.add("08:00-09:00");
        list.add("09:00-12:00");
        list.add("18:00-20:00");
        list.add("13:00-16:30");
        list.add("16:30-17:00");

        boolean flag = checkOverlap(list);
        for (String time : list) {
            System.out.println(time);
        }

        System.out.println("\n当前时间段列表重叠验证结果为：" + flag);

    }


    public static boolean checkOverlap(List<String> list) {
        Collections.sort(list);//排序ASC
        boolean flag = false;//是否重叠标识
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) {
                //跳过第一个时间段不做判断
                String[] itime = list.get(i).split("-");
                for (int j = 0; j < list.size(); j++) {
                    //如果当前遍历的i开始时间小于j中某个时间段的结束时间那么则有重叠，反之没有重叠
                    //这里比较时需要排除i本身以及i之后的时间段，因为已经排序了所以只比较自己之前(不包括自己)的时间段
                    if (j == i || j > i) {
                        continue;
                    }

                    Date day = new Date();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

                    String[] jtime = list.get(j).split("-");
                    //此处DateUtils.compare为日期比较(返回负数date1小、返回0两数相等、返回正整数date1大)
                    int compare = compareDate(
                            (df.format(day) + " " + itime[0] + ":00"),
                            (df.format(day) + " " + jtime[1] + ":00"),
                            "yyyy-MM-dd HH:mm:ss");
                    if (compare < 0) {
                        flag = true;
                        break;//只要存在一个重叠则可退出内循环
                    }
                }
            }

            //当标识已经认为重叠了则可退出外循环
            if (flag) {
                break;
            }
        }

        return flag;
    }

    public static Integer compareDate(String DATE1, String DATE2, String dateFormat) {
        DateFormat df = new SimpleDateFormat(dateFormat);
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            System.out.println("-------" + e);
        }

        return null;
    }


    @Test
    public void test37() {
//        String t1 = "12:00";
//        String t2 = "11:00";
//        System.out.println(t1.compareTo(t2));
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime localDateTime = now.plusDays(0);
        System.out.println(now.compareTo(localDateTime));
    }

    @Test
    public void test36() {
//        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
//        updateWrapper.set("name","e");
//        updateWrapper.eq("id","1");
//        boolean update = userService.update(updateWrapper);
//
        boolean lu = userService.lambdaUpdate().set(User::getName, "444").eq(User::getId, 1)
                .update();
    }

//    @Test
//    public void test35() {
//        Student student1 = new Student("1", 1);
//        Student student2 = new Student("2", 2);
//        Student student3 = new Student("4", 4);
//        List<Student> students = Arrays.asList(student1, student2, student3);
//        List<Student> collect = students.stream().peek(student -> student.setAge(18)).collect(Collectors.toList());
//        System.out.println(collect);
//    }

    @Test
    public void test31() {
        Student student = new Student();
//        String a = student.a;

    }

    @Test
    public void testMap() {
        Map<String, String> map = new HashMap<>();
        map.put("a", "A");
        map.put("b", "B");
        //无论key是否存在都放入map，返回新值
        String val = map.compute("b", (k, v) -> "B2"); // 返回 B2
        System.out.println(val);
        String v1 = map.compute("c", (k, v) -> "C"); // 返回 C
        System.out.println(v1);
        System.out.println(map);
    }

    @Test
    public void testMap3() {
        Map<String, String> map = new HashMap<>();
        map.put("a", "A");
        map.put("b", "B");
        //先判断key再计算value，存在不操作，并返回旧值，不存在建立映射并返回新值，不允许null
        String v = map.computeIfAbsent("b", k -> "B2");  // 返回 B
        System.out.println(v);
        String v1 = map.computeIfAbsent("c", k -> "C"); // 返回 C
        System.out.println(v1);
        System.out.println(map);
    }

    @Test
    public void testMap2() {
        Map<String, String> map = new HashMap<>();
        map.put("a", "A");
        map.put("b", "B");
        //先计算value再判断key，存在不操作并返回旧值，不存在建立映射并返回旧值，运行null
        String v = map.putIfAbsent("b", "B2");  // 返回 B
        System.out.println(v);
        String v1 = map.putIfAbsent("c", "C");  // 返回 null（旧值）
        System.out.println(v1);
        System.out.println(map);
    }

    @Test
    public void testMap4() {
        Map<String, String> map = new HashMap<>();
        map.put("a", "A");
        map.put("b", "B");
        map.put("c", null);//允许
        map.putIfAbsent("d", null);//允许
        map.compute("e", null);//空指针
        map.computeIfAbsent("f", null);//空指针
    }


    @Test
    public void test34() {
        Object userServiceImpl = applicationContext.getBean("userServiceImpl");
        try {
            Class<?> aClass = userServiceImpl.getClass();
//            Class<?> targetClass = AopUtils.getTargetClass(userServiceImpl);
            Method test = aClass.getDeclaredMethod("test");
            test.setAccessible(true);
            test.invoke(userServiceImpl);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test33() {
        UserServiceImpl userServiceImpl = (UserServiceImpl) applicationContext.getBean("userServiceImpl");
        Class<? extends UserServiceImpl> aClass = userServiceImpl.getClass();
        try {
            Method[] declaredMethods = aClass.getDeclaredMethods();
            for (Method method : declaredMethods) {
                System.out.println(method.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test32() {

        int[] monthArray = new int[31];
        for (int i = 1; i <= 31; i++) {
            monthArray[i - 1] = i;
        }
        System.out.println(Arrays.toString(monthArray));
    }

    @Test
    public void test22() {
        A a = new A();
        try {
            Method private1 = A.class.getDeclaredMethod("private1");
            private1.setAccessible(true);
            int invoke = (int) private1.invoke(a);
            System.out.println(invoke);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void test21() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(" age");
        queryWrapper.eq("id", 1);
        User user = userService.getBaseMapper().selectOne(queryWrapper);
        System.out.println(user);
    }

    @Test
    public void test20() {
        int age = userService.getBaseMapper().queryTestO("123456", "2022", "3", "25");
        System.out.println(age);
    }

    @Test
    public void test19() {
        User one = userService.lambdaQuery()
                .eq(User::getId, "1").one();
        System.out.println(one);
    }

    @Test
    public void test189() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime localDateTime = now.plusDays(-1);
        System.out.println(localDateTime);
    }

    @Test
    public void test18() throws Exception {
        User user1 = new User("1", "张三", "1", "2");
        User user2 = new User("1", "李四", "1", "2");

    }

    @Test
    public void test17() throws Exception {
        User user1 = new User("1", "张三", "1", "2");
        User user2 = new User("1", "张三", "1", "2");
        User user3 = new User("1", "李四", "2", "3");
        ArrayList<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        List<User> collect = users.parallelStream()
                .collect(Collectors.toMap(o -> (o.getId() + o.getName()), a -> a, (o1, o2) ->
                        {
                            try {
                                Class<? extends User> aClass1 = o1.getClass();
                                Class<? extends User> aClass2 = o2.getClass();
                                Method getPassword1 = aClass1.getDeclaredMethod("getPassword");
                                Method getPassword2 = aClass2.getDeclaredMethod("getPassword");
                                Method setPassword1 = aClass1.getDeclaredMethod("setPassword", String.class);
                                String invoke1 = (String) getPassword1.invoke(o1);
                                String invoke2 = (String) getPassword2.invoke(o2);
                                setPassword1.invoke(o1, invoke1 + invoke2);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
//                            o1.setPassword(o1.getPassword()+o2.getPassword());
//                            o1.setPermission(o1.getPermission()+ o2.getPermission());
//   [User(id=1, name=李四, password=2, permission=3), User(id=1, name=张三, password=11, permission=22)]
                            return o1;
                        }

                )).values().stream().collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void test16() {
        ArrayList<Object> l1 = new ArrayList<>();
        l1.add(1);
        l1.add(2);
        l1.add(3);
        ArrayList<Object> l2 = new ArrayList<>();
        l2.add(2);
        l2.add(3);
        l2.add(4);
        l2.retainAll(l1);
        System.out.println(l2);


    }


    @Test
    public void test15() {
        BigDecimal bigDecimal = new BigDecimal("0");
        BigDecimal bigDecimal1 = new BigDecimal("0");
        System.out.println(bigDecimal.compareTo(bigDecimal1));

    }

    @Resource
    UserServiceImpl userService;

    @Test
    void test1() {
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        int result = com.compare(2, 1);
        System.out.println(result);
    }

    @Test
    void test2() {
        Comparator<Integer> com = (o1, o2) -> Integer.compare(o1, o2);
        int result = com.compare(1, 2);
        System.out.println(result);
    }

    @Test
    void test3() {
        Comparator<Integer> com = (o1, o2) -> Integer.compare(o1, o2);
        int result = com.compare(1, 2);
        System.out.println(result);
    }


    @Test
    public void test5() {
        String str = "{\n" +
                "  \"success\": true,\n" +
                "  \"message\": \"操作成功！\",\n" +
                "  \"code\": 200,\n" +
                "  \"result\": {\n" +
                "    \"maintenanceIp\": null,\n" +
                "    \"upgradeTime\": \"2022-02-20 21:48:42\",\n" +
                "    \"idCode\": \"91350200MA356L2T74\",\n" +
                "    \"updateTime\": \"2022-02-20 09:18:03\",\n" +
                "    \"randomCode\": 1100,\n" +
                "    \"enterpriceId\": \"1461620293730668546\",\n" +
                "    \"userId\": null,\n" +
                "    \"license\": \"duQG3SHkyvO9LsKDKGL2Kluv0G9xDTlQMzwuXPk0hnnVqdEM2O4A7KNy9l96p4FLnJFMTo0w3Mrp7BeONySNdPHFL4NSSiolz7cUyWxuneud7GQ4dvH7uaa8H8mjYiWXUHbOP2Ryf63C7EbyEEXVZ7esg5Q6jDiEAzfCHY\",\n" +
                "    \"createTime\": \"2022-02-20 00:48:42\",\n" +
                "    \"platformVersion\": \"1\",\n" +
                "    \"id\": \"1493930333844803585\",\n" +
                "    \"maintenancePort\": null,\n" +
                "    \"enterpriseVersion\": \"1\"\n" +
                "  },\n" +
                "  \"timestamp\": 1645195002222\n" +
                "}";
        JSONObject jsonObject = JSONObject.parseObject(str);
        EnterpriseVersion code = jsonObject.getObject("result", EnterpriseVersion.class);


        System.out.println(code);
    }

    @Test
    public void test6() {
        User user = userService.getBaseMapper().selectOne(
                new QueryWrapper<User>().eq("id", null));
        ;
        User user1 = userService.getBaseMapper().selectOne(
                new QueryWrapper<User>().eq("id", ""));
        ;

    }

    //条件不能放list
    @Test
    public void test7() {
        List<Integer> integers = Arrays.asList(123456, 654321);
        List<User> list = userService.lambdaQuery().eq(User::getPassword, integers).list();
        System.out.println(list);
    }


    @Test
    public void test8() {
        int i = DateUtil.dayOfMonth(new Date());
        System.out.println(i);
    }

    @Test
    public void test9() {
        ArrayList<Object> objects = null;
        boolean notEmpty = CollectionUtil.isNotEmpty(objects);
        System.out.println(notEmpty);
    }

    @Test
    public void test10() {
        LocalDate now = LocalDate.now();
        int dayOfMonth = now.plusDays(-1).getDayOfMonth();
        int dayOfYear = now.plusDays(-1).getDayOfYear();
        int year = now.plusYears(1).getYear();
        System.out.println(year);
    }

    @Test
    public void test11() {
        ArrayList<Object> list1 = new ArrayList<>();
        ArrayList<Object> list2 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(2);
        list1.add(3);
        System.out.println(list1);
        HashSet<Object> objects = new HashSet<>(list1);
        System.out.println(objects);
    }

    @Test
    public void test12() {
        DateTime dateTime = DateUtil.offsetMonth(new Date(), -12);
        System.out.println(dateTime);
    }

    @Test
    public void test13() {
        HashSet<User> users = new HashSet<>();
        User user1 = new User("1", "张三", "123456", "333");
        User user2 = new User("2", "李四", "321151", "333");
        users.add(user2);
        users.add(user1);
        System.out.println(users);
    }

    @Test
    public void test14() {
//        2022-02-
        Date date = new Date();
//        String format = DateUtil.format(date, "yyyy-MM-dd HH:mm:ss");
        DateTime dateTime = DateUtil.offsetMonth(date, -1);
        String format = DateUtil.format(dateTime, "yyyy-MM");
        System.out.println(format);
    }

    @Test
    public void test1111() {
        Parent parent = new Parent();
        System.out.println("Parent parent = new Parent();是否为子类Student的实例？" + (parent instanceof Student));
        System.out.println("Parent parent = new Parent();是否为父类Parent实例？" + (parent instanceof Parent));
        Parent student = new Student();
        System.out.println("Parent student= new Student();是否为子类Student的实例？" + (student instanceof Student));
        System.out.println("Parent student= new Student();是否为父类Parent实例？" + (student instanceof Parent));
        Student student1 = (Student) student;
        System.out.println("Student student1 = (Student) student;向下转型后是否为Student实例？" + (student1 instanceof Student));
        System.out.println("Student student1 = (Student) student;向下转型后是否为父类Parent实例？" + (student1 instanceof Parent));

    }
}


class A implements Runnable {
    static BigDecimal bigDecimal1;

    public void testMethod(String a, B b) {

    }

    private int private1() {
        System.out.println("私有方法1");
        int i = private2();
        return i;
    }

    private int private2() {
        System.out.println("私有方法2");
        return 2;
    }


    @Override
    public void run() {

    }
}

class B {
    private static final String helloword = "test";

}

