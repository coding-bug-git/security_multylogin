package cn.bug.demo02.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


public class EcStaffUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {


    public EcStaffUsernamePasswordAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    private static final long serialVersionUID = 8665690993060353849L;


}