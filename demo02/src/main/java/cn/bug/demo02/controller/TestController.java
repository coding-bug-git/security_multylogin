package cn.bug.demo02.controller;

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

    @GetMapping("/hello")
    public String hello() {
        return "hello springSecurity";
    }


    @PostMapping("/login")
    public Object login() {
        return "login";
    }

    @PostMapping("/login1")
    public Object login1() {

        return "login1";
    }
}
