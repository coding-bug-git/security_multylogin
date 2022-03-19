package cn.bug.demo02.security;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;


public class EcStaffUsernamePasswordAuthenticationProvider extends DaoAuthenticationProvider {


    public boolean supports(Class<?> authentication) {
        return (EcStaffUsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}