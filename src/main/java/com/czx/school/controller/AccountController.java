package com.czx.school.controller;

import com.czx.school.DTO.ChangeAccountDTO;
import com.czx.school.DTO.LoginDTO;
import com.czx.school.DTO.PageDTO;
import com.czx.school.DTO.RegisterDTO;
import com.czx.school.VO.AccountVO;
import com.czx.school.common.PageResponse;
import com.czx.school.common.Response;
import com.czx.school.entity.Account;
import com.czx.school.common.ErrorCode;
import com.czx.school.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;
    // 增
    @PostMapping(value = "/register")
    public Response<String> register(@RequestBody RegisterDTO registerDTO) {
        if (accountService.selectByUsername(registerDTO.getUsername()) != null) {
            return Response.fail(ErrorCode.ACCOUNT_ALREADY_EXIST.getCode(), ErrorCode.ACCOUNT_ALREADY_EXIST.getMsg());
        }

        return accountService.register(registerDTO)
                ? Response.success("注册成功，将自动跳转至登陆页面",null)
                : Response.fail(ErrorCode.ACCOUNT_REGISTER_FAILURE.getCode(), ErrorCode.ACCOUNT_REGISTER_FAILURE.getMsg());
    }
    // 删
    @PostMapping(value = "/delete")
    public Response<String> deleteAccount(@RequestParam String name){
        if (accountService.selectByUsername(name) == null) {
            return Response.fail(ErrorCode.ACCOUNT_NOT_EXIST.getCode(),ErrorCode.ACCOUNT_NOT_EXIST.getMsg());
        }

        return accountService.delete(name)
                ? Response.success("软删除或硬删除成功",null)
                : Response.fail(ErrorCode.ACCOUNT_DELETE_FAILURE.getCode(),ErrorCode.ACCOUNT_DELETE_FAILURE.getMsg());
    }
    // 改
    @PostMapping(value = "/login")
    public Response<String> login(@RequestBody LoginDTO loginDTO) {
        if (accountService.selectByUsername(loginDTO.getUsername()) == null) {
            return Response.fail(ErrorCode.ACCOUNT_NOT_EXIST.getCode(),ErrorCode.ACCOUNT_NOT_EXIST.getMsg());
        }

        return accountService.login(loginDTO)
                ? Response.success("登录成功",null)
                : Response.fail(ErrorCode.ACCOUNT_LOGIN_FAILURE.getCode(), ErrorCode.ACCOUNT_LOGIN_FAILURE.getMsg());
    }
    @PostMapping(value = "/update")
    public Response<String> update(@RequestBody ChangeAccountDTO changeAccountDTO){
        if(accountService.selectByUsername(changeAccountDTO.getOldName()) == null){
            return Response.fail(ErrorCode.ACCOUNT_NOT_EXIST.getCode(),ErrorCode.ACCOUNT_NOT_EXIST.getMsg());
        }

        return accountService.update(changeAccountDTO)
                ? Response.success("修改成功",null)
                : Response.fail(ErrorCode.ACCOUNT_UPDATE_FAILURE.getCode(),ErrorCode.ACCOUNT_UPDATE_FAILURE.getMsg());
    }
    // 查
    @GetMapping(value = "/get/{id}")
    public Response<Account> getById(@PathVariable Integer id){
        Account account = accountService.selectById(id);

        return account != null
                ? Response.success("获取成功",account)
                : Response.fail(ErrorCode.ACCOUNT_NOT_EXIST.getCode(),ErrorCode.ACCOUNT_NOT_EXIST.getMsg());
    }
    @PostMapping(value = "/page")
    public Response<PageResponse<AccountVO>> getPages(@RequestBody PageDTO pageDTO){
        PageResponse<AccountVO> records = accountService.getPages(pageDTO);

        return !records.getList().isEmpty()
                ? Response.success("获取成功",records)
                : Response.fail(ErrorCode.PAGE_EMPTY.getCode(),ErrorCode.PAGE_EMPTY.getMsg());
    }
}