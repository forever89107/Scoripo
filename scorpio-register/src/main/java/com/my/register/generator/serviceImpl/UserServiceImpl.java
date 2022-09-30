package com.my.register.generator.serviceImpl;

import com.my.register.generator.entity.User;
import com.my.register.generator.mapper.UserMapper;
import com.my.register.generator.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author MBG
 * @since 2022-09-30 03:56:48
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
