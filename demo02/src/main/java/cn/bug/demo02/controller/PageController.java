package cn.bug.demo02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * description
 *
 * @author coding-bug
 * date 2022/3/6 10:47
 */
@Controller
public class PageController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
