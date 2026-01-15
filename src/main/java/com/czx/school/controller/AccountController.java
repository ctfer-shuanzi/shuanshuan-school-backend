package com.czx.school.controller;

import com.czx.school.DTO.LoginDTO;
import com.czx.school.DTO.PageDTO;
import com.czx.school.DTO.RegisterDTO;
import com.czx.school.VO.Response;
import com.czx.school.entity.Account;
import com.czx.school.error.ErrorCode;
import com.czx.school.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping(value = "/register")
    public Response<String> register(@RequestBody RegisterDTO registerDTO) {
        if (accountService.selectByUsername(registerDTO.getUsername()) != null) {
            return Response.fail(ErrorCode.ACCOUNT_ALREADY_EXIST.getCode(), ErrorCode.ACCOUNT_ALREADY_EXIST.getMsg());
        }
        return accountService.register(registerDTO)
                ? Response.success("注册成功，将自动跳转至登陆页面")
                : Response.fail(ErrorCode.ACCOUNT_REGISTER_FAILURE.getCode(), ErrorCode.ACCOUNT_REGISTER_FAILURE.getMsg());
    }

    @PostMapping(value = "/delete")
    public Response<String> deleteAccount(@RequestParam String name){
        if (accountService.selectByUsername(name) == null) {
            return Response.fail(ErrorCode.ACCOUNT_NOT_EXIST.getCode(),ErrorCode.ACCOUNT_NOT_EXIST.getMsg());
        }
        return accountService.delete(name)
                ? Response.success("软删除或硬删除成功")
                : Response.fail(ErrorCode.ACCOUNT_DELETE_FAILURE.getCode(),ErrorCode.ACCOUNT_DELETE_FAILURE.getMsg());
    }

    @PostMapping(value = "/login")
    public Response<String> login(@RequestBody LoginDTO loginDTO) {
        if (accountService.selectByUsername(loginDTO.getUsername()) == null) {
            return Response.fail(ErrorCode.ACCOUNT_NOT_EXIST.getCode(),ErrorCode.ACCOUNT_NOT_EXIST.getMsg());
        }
        return accountService.login(loginDTO)
                ? Response.success("登录成功")
                : Response.fail(ErrorCode.ACCOUNT_LOGIN_FAILURE.getCode(), ErrorCode.ACCOUNT_LOGIN_FAILURE.getMsg());
    }

    @GetMapping(value = "/get/{id}")
    public Response<Account> getById(@PathVariable Integer id){
        Account account = accountService.selectById(id);
        return account == null
                ? Response.fail(ErrorCode.ACCOUNT_NOT_EXIST.getCode(),ErrorCode.ACCOUNT_NOT_EXIST.getMsg())
                : Response.success(account);
    }

    @PostMapping(value = "/page")
    public Response<List<Account>> getPages(@RequestBody PageDTO pageDTO){
        List<Account> records = accountService.getPages(pageDTO);
        if(records.isEmpty()){
            return Response.fail(ErrorCode.PAGE_EMPTY.getCode(),ErrorCode.PAGE_EMPTY.getMsg());
        }
        return Response.success(records);
    }
}

