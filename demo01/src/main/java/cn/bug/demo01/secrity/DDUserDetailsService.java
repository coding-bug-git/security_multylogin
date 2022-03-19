package cn.bug.demo01.secrity;

import cn.bug.demo01.fakedata.FakeUserList;
import cn.bug.demo01.pojo.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * description
 *
 * @author coding-bug
 * date 2022/3/6 9:04
 */
@Component("DDUserDetailService")
public class DDUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String dingId) throws UsernameNotFoundException {
        // 根据dingId 查询 数据库返回user
        User user = FakeUserList.getUserByDingId(dingId);

        List<GrantedAuthority> admin = AuthorityUtils.commaSeparatedStringToAuthorityList("admin");
        // menuMapper.selectPermsByUserId(user.getId());
        return new DDLoginUser(user, admin);
    }
}
