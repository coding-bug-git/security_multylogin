package cn.bug.demo01.controller;

import cn.bug.demo01.secrity.DDAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

/**
 * description
 *
 * @author coding-bug
 * date 2022/3/5 22:02
 */
@RestController
public class TestController {
    @Autowired
    AuthenticationManager authenticationManager;

    @RequestMapping("/dingLogin")
    public Object dingLogin(@RequestBody Map<String, Object> params) {
        String dingId = "admin01";
        // Map<String, String> params = MapUtil.builder("dingId", "admin01")
        //         .put("corpid", "corpid").build();
        DDAuthenticationToken authenticationToken = new DDAuthenticationToken(params.get("dingId"), params.get("corpid"));
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("验证失败dd");
        }
        return authenticate.getPrincipal();
    }

    @RequestMapping("/login")
    public Object login(@RequestBody Map<String, Object> params) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(params.get("username"), params.get("password"));
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("验证失败!");
        }
        return authenticate;
    }

}
