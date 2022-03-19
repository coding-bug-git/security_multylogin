package cn.bug.demo01.secrity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * description
 *
 * @author coding-bug
 * date 2022/3/5 23:03
 */
@Slf4j
@Component
public class DDAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private DDUserDetailsService userDetailsService;

    private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();



    public void setAuthoritiesMapper(GrantedAuthoritiesMapper authoritiesMapper) {
        this.authoritiesMapper = authoritiesMapper;
    }

    public GrantedAuthoritiesMapper getAuthoritiesMapper() {
        return authoritiesMapper;
    }



    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.isInstanceOf(DDAuthenticationToken.class, authentication,
                () -> "DDAuthenticationProvider.onlySupports DDAuthenticationToken");

        // Determine username
        String dingId = (authentication.getPrincipal() == null) ? "NONE_PROVIDED" : authentication.getName();
        UserDetails user = null;

        try {
            user = retrieveUser(dingId, (DDAuthenticationToken) authentication);
        } catch (UsernameNotFoundException notFound) {
            log.debug("User dingId => '" + dingId + "' not found");
        }
        Assert.notNull(user, "retrieveUser returned null - a violation of the interface contract");

        Object principalToReturn = user;

        return createSuccessAuthentication(principalToReturn, authentication, user);
    }

    protected Authentication createSuccessAuthentication(Object principal, Authentication authentication, UserDetails user) {
        DDAuthenticationToken result = new DDAuthenticationToken(
                principal, authentication.getCredentials(),
                authoritiesMapper.mapAuthorities(user.getAuthorities()));
        result.setDetails(authentication.getDetails());

        return result;
    }

    protected final UserDetails retrieveUser(String dingId, DDAuthenticationToken authentication) throws AuthenticationException {
        try {
            UserDetails loadedUser = userDetailsService.loadUserByUsername(dingId);
            if (loadedUser == null) {
                throw new InternalAuthenticationServiceException(
                        "UserDetailsService returned null, which is an interface contract violation");
            }
            return loadedUser;
        } catch (UsernameNotFoundException | InternalAuthenticationServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex);
        }
    }



    @Override
    public boolean supports(Class<?> authentication) {
        // 是否是 其 父类
        return (DDAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
