package com.my.security.user;

import com.my.resource.generator.entity.OrmUser;
import com.my.security.auth.AbstrUser;
import com.my.security.auth.constant.Authority;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserDlDetails implements UserDetails {

    private static AbstrUser abstrUser;
    private static String uuid;
    private static String emplId;
    private static String name;
    private static String displayName;

    public UserDlDetails(OrmUser ormUser) {
        AbstrUser abstrUser = new AbstrUser();
        abstrUser.setUsername(ormUser.getUsername());
        abstrUser.setPassword(ormUser.getPassword());
        abstrUser.setAuthorities(Collections.singletonList(Authority.USER));
        UserDlDetails.abstrUser = abstrUser;
        uuid = ormUser.getUuid();
        name = ormUser.getName();
        displayName = ormUser.getDisplayname();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return abstrUser.getAuthorities().stream()
                .map(auth -> new SimpleGrantedAuthority(auth.name()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return abstrUser.getPassword();
    }

    @Override
    public String getUsername() {
        return abstrUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
