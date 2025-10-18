package com.czx.school.controller;

import com.czx.school.DTO.LoginDTO;
import com.czx.school.DTO.RegisterDTO;
import com.czx.school.VO.Response;
import com.czx.school.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/login")
    public Response<String> login(@RequestBody LoginDTO loginDTO) {
        if (accountService.selectByUsername(loginDTO.getUsername()) == null) {
            return Response.error("1001", "用户不存在，请先注册");
        }
        return accountService.login(loginDTO) ? Response.success("登录成功") : Response.error("1002", "登录失败，用户名或密码错误");
    }

    @PostMapping("/register")
    public Response<String> register(@RequestBody RegisterDTO registerDTO) {
        if (accountService.selectByUsername(registerDTO.getUsername()) != null) {
            return Response.error("1003", "用户已存在");
        }
        return accountService.register(registerDTO) ? Response.success("注册成功") : Response.error("1004", "注册失败");
    }
}

