package com.my.login.generator.serviceImpl;

import com.my.login.generator.entity.User;
import com.my.login.generator.mapper.UserMapper;
import com.my.login.generator.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author MBG
 * @since 2022-09-30 03:51:47
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
