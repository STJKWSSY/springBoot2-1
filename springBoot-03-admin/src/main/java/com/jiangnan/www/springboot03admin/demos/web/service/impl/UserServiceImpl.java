package com.jiangnan.www.springboot03admin.demos.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiangnan.www.springboot03admin.demos.web.mapper.UserMapper;
import com.jiangnan.www.springboot03admin.demos.web.pojo.User;
import com.jiangnan.www.springboot03admin.demos.web.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户服务实现类
 *
 * @author jiangNan
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
