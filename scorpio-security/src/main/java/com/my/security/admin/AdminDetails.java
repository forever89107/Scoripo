package com.my.security.admin;

import com.my.security.auth.AbstrUser;
import com.my.security.auth.constant.Authority;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
public class AdminDetails implements UserDetails {

    private static AbstrUser abstrUser;

    protected static final Map<String, String> admin_user = Collections.singletonMap("sys_admin", "pgtalk168");

    public AdminDetails(String username) {
        if (!admin_user.containsKey(username)) return;
        AbstrUser abstrUser = new AbstrUser();
        abstrUser.setUsername(username);
        abstrUser.setPassword(admin_user.get(username));
        abstrUser.setAuthorities(Arrays.asList(Authority.ADMIN, Authority.USER));
        AdminDetails.abstrUser = abstrUser;
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
