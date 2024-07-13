package com.my.user.business;

import com.my.common.mybatis.business.AbstractBusiness;
import com.my.user.mapper.SCUserMapper;
import com.my.user.model.SCUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class SCUserBusiness extends AbstractBusiness<SCUser> {
    @Autowired
    private SCUserMapper scUserMapper;

    public SCUser list() {
        return scUserMapper.selectNumberOne(Long.parseLong("1"));
    }
}
