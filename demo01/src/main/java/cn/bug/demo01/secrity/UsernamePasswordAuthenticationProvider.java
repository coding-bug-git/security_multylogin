package cn.bug.demo01.secrity;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Collections;

/**
 * description
 *
 * @author coding-bug
 * date 2022/3/17 22:21
 */
@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.isInstanceOf(UsernamePasswordAuthenticationToken.class, authentication,
                () -> "UsernamePasswordAuthenticationProvider.onlySupports UsernamePasswordAuthenticationToken");
        
        String userName = authentication.getName();
        String password = authentication.getCredentials().toString();
        if (!"admin".equals(userName) || !"123456".equals(password)) {
            throw new BadCredentialsException("username or password authentication fail");
        }
        //安全起见密码不要返回
        return new UsernamePasswordAuthenticationToken(userName, null, Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> aClazz) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClazz));
    }
}
