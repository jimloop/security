package com.jim.security.service.impl;

import com.jim.security.entity.User;
import com.jim.security.mapper.UserMapper;
import com.jim.security.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2021-05-10 15:43:11
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Long id) {
        return this.userMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<User> queryAllByLimit(int offset, int limit) {
        return this.userMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param User 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User User) {
        this.userMapper.insert(User);
        return User;
    }

    /**
     * 修改数据
     *
     * @param User 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User User) {
        this.userMapper.update(User);
        return this.queryById(User.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.userMapper.deleteById(id) > 0;
    }
}
