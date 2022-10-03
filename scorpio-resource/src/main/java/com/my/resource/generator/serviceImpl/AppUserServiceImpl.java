package com.my.resource.generator.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.my.core.result.Result;
import com.my.resource.converter.AppUserConverter;
import com.my.resource.entity.app_user.AppUserRequest;
import com.my.resource.entity.app_user.AppUserResponse;
import com.my.resource.exception.UnprocessableEntityException;
import com.my.resource.generator.entity.OrmUser;
import com.my.resource.generator.mapper.UserMapper;
import com.my.resource.generator.service.AppUserService;
import lombok.SneakyThrows;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author MBG
 * @since 2022-10-03 02:27:38
 */
@Service
public class AppUserServiceImpl extends ServiceImpl<UserMapper, OrmUser> implements AppUserService {
    private final UserMapper mapper;

    @Autowired
    public AppUserServiceImpl(UserMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public int createUser(AppUserRequest req) {
        QueryWrapper<OrmUser> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("username", req.getUsername().trim())
                .or()
                .eq("email", req.getEmail().trim());
        boolean hadAccount = mapper.exists(queryWrapper);
        if (hadAccount) {
            throw new UnprocessableEntityException("This email address has been used.");
        }
        OrmUser user = AppUserConverter.toOrmUser(req);
        return mapper.insert(user);
    }

    @Override
    public Result<AppUserResponse> getUserById(Integer id) {
        QueryWrapper<OrmUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        OrmUser user = mapper.selectOne(queryWrapper);
        AppUserResponse response = AppUserConverter.toAppUserResponse(user);
        return new Result<>(response);
    }

    @Override
    public OrmUser getUserByUsername(String username) {
        QueryWrapper<OrmUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username.trim());
        return mapper.selectOne(queryWrapper);

    }

    @Override
    public Result<List<AppUserResponse>> getAllUser(int page, int pageSize) {
        Page<OrmUser> res =
                mapper.selectPage(new Page<>(page, pageSize), new QueryWrapper<>());
        List<AppUserResponse> response_list = AppUserConverter.toAppUserResponses(res.getRecords());
        return new Result<>(response_list);
    }

    @SneakyThrows
    @Override
    public int putUser(AppUserRequest req)  {
        QueryWrapper<OrmUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", req.getUsername());
        OrmUser ormUser = mapper.selectOne(queryWrapper);
        if (null != ormUser) {
            ormUser.setDisplayname(req.getDisplayname());
            return mapper.updateById(ormUser);
        } else
            throw new NotFoundException("Can't find user.");
    }

    @SneakyThrows
    @Override
    public int deleteById(AppUserRequest req) {
        QueryWrapper<OrmUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", req.getUsername());
        OrmUser ormUser = mapper.selectOne(queryWrapper);
        if (null != ormUser) {
            ormUser.setDisplayname(req.getDisplayname());
            return mapper.deleteById(ormUser);
        } else
            throw new NotFoundException("Can't find user.");
    }
}
