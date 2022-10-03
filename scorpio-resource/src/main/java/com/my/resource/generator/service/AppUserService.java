package com.my.resource.generator.service;

import com.my.core.result.Result;
import com.my.resource.entity.app_user.AppUserRequest;
import com.my.resource.entity.app_user.AppUserResponse;
import com.my.resource.generator.entity.OrmUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author MBG
 * @since 2022-10-03 02:27:38
 */
public interface AppUserService extends IService<OrmUser> {
    /** Create */
    int createUser(AppUserRequest req);
    /** Get */
    Result<AppUserResponse> getUserById(Integer id);

    OrmUser getUserByUsername(String username);

    Result<List<AppUserResponse>> getAllUser(int page, int pageSize);

    /** Put */
    int putUser(AppUserRequest req);
    /** Delete */
    int deleteById(AppUserRequest req);
}
