package cn.bug.demo01.secrity;

import cn.bug.demo01.pojo.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * description
 *
 * @author coding-bug
 * date 2022/3/6 9:11
 */
public class DDLoginUser implements UserDetails {
    private String username;
    private String password;
    private List<GrantedAuthority> authorities;
    private User user;



    public DDLoginUser(String username, String password, List<GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public DDLoginUser(User user, List<GrantedAuthority> authorities) {
        this.user = user;
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.authorities = authorities;
    }


    public User getUser() {
        return user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
