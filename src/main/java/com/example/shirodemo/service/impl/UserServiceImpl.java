package com.example.shirodemo.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.shirodemo.entity.User;
import com.example.shirodemo.dao.UserDao;
import com.example.shirodemo.service.UserService;
import org.springframework.stereotype.Service;


/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2022-02-15 21:06:15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao,User> implements UserService {
    @Override
    public User queryTest(String id) {
        //
        UserDao baseMapper = this.baseMapper;
        return this.baseMapper.queryTestO(id);
    }


//    @Resource
//    private UserDao userDao;
//
//    /**
//     * 通过ID查询单条数据
//     *
//     * @param id 主键
//     * @return 实例对象
//     */
//    @Override
//    public User queryById(String id) {
//        return this.userDao.queryById(id);
//    }
//
//    /**
//     * 查询多条数据
//     *
//     * @param offset 查询起始位置
//     * @param limit 查询条数
//     * @return 对象列表
//     */
//    @Override
//    public List<User> queryAllByLimit(int offset, int limit) {
//        return this.userDao.queryAllByLimit(offset, limit);
//    }
//
//    /**
//     * 新增数据
//     *
//     * @param user 实例对象
//     * @return 实例对象
//     */
//    @Override
//    public User insert(User user) {
//        this.userDao.insert(user);
//        return user;
//    }
//
//    /**
//     * 修改数据
//     *
//     * @param user 实例对象
//     * @return 实例对象
//     */
//    @Override
//    public User update(User user) {
//        this.userDao.update(user);
//        return this.queryById(user.getId());
//    }
//
//    /**
//     * 通过主键删除数据
//     *
//     * @param id 主键
//     * @return 是否成功
//     */
//    @Override
//    public boolean deleteById(String id) {
//        return this.userDao.deleteById(id) > 0;
//    }
}