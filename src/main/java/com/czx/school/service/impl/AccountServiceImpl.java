package com.czx.school.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czx.school.DO.Account;
import com.czx.school.DTO.LoginDTO;
import com.czx.school.DTO.RegisterDTO;
import com.czx.school.mapper.AccountMapper;
import com.czx.school.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService{
    @Autowired
    private AccountMapper AccountMapper;
    @Override
    public Account selectByUsername(String username) {
        LambdaQueryWrapper<Account> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Account::getUsername, username);
        return AccountMapper.selectOne(queryWrapper);
    }
    @Override
    public boolean login(LoginDTO loginDTO) {
        Account account = selectByUsername(loginDTO.getUsername());
        if(account.getPassword().equals(loginDTO.getPassword())){
            account.setLastLoginTime(LocalDateTime.now());
            updateById(account);
            return true;
        }
        return false;
    }
    @Override
    public boolean register(RegisterDTO registerDTO) {
        Account account = new Account();
        account.setUsername(registerDTO.getUsername());
        account.setPassword(registerDTO.getPassword());
        account.setRole(registerDTO.getRole());
        account.setCreateTime(LocalDateTime.now());
        account.setLastLoginTime(LocalDateTime.now());
        return save(account);
    }
}
