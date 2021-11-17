package io.github.gozhuyinglong.wechatdevelop.controller;

import io.github.gozhuyinglong.wechatdevelop.utils.JwtUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
public class AuthTestController {

    @GetMapping("/test")
    public void test(HttpServletRequest request) {
        String username = JwtUtil.getUsername(request);
        System.out.println("-------------------鉴权测试-------------------");
    }
}
