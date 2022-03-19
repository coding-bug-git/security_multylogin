package cn.bug.demo02.controller;

import cn.bug.demo02.security.EcStaffUsernamePasswordAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description
 *
 * @author coding-bug
 * date 2022/3/5 22:02
 */
@RestController
public class TestController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/hello")
    public String hello() {
        return "hello springSecurity";
    }


    @PostMapping("/login")
    public Object login() {
        // 用户验证
        Authentication authentication = null;
        try {
            // 该方法会去调用EcStaffDetailsServiceImpl.loadUserByUsername
            // 因为这个自定token只被自定provider的support所支持
            // 所以才会provider中注入的EcStaffDetailsServiceImpl，在security配置文件注入的
            authentication = authenticationManager.authenticate(new EcStaffUsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                //密码不匹配，需自定义返回前台消息
                throw new UserPasswordNotMatchException();
            } else {
                throw new CustomException(e.getMessage());
            }
        }

        //登录成功
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        return null;
    }

    @PostMapping("/login1")
    public Object login() {
        // 用户验证
        Authentication authentication = null;
        try {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            // 该方式使用的security内置token会使用内置DaoAuthenticationProvider认证
            // UserDetailsServiceImpl是在security config中配置的
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {

                throw new UserPasswordNotMatchException();
            } else {

                throw new CustomException(e.getMessage());
            }
        }

        LoginUser loginUser = (LoginUser) authentication.getPrincipal();// 该方法会去调用
        return null;
    }
}
