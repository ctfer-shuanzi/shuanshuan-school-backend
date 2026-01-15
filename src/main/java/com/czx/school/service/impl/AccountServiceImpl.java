package com.czx.school.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czx.school.DTO.PageDTO;
import com.czx.school.entity.Account;
import com.czx.school.DTO.LoginDTO;
import com.czx.school.DTO.RegisterDTO;
import com.czx.school.mapper.AccountMapper;
import com.czx.school.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
    public Account selectById(Integer id) {
        LambdaQueryWrapper<Account> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Account::getId,id);
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

    @Override
    public boolean delete(String username) {
        LambdaQueryWrapper<Account> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Account::getUsername,username);
        Account account = AccountMapper.selectOne(queryWrapper);

        if(account.getDeleted() == 0){
            UpdateWrapper<Account> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("username",username).set("deleted",1);

            return AccountMapper.update(updateWrapper) > 0;
        }
        return AccountMapper.deleteById(account.getId()) > 0;
    }

    @Override
    public List<Account> getPages(PageDTO pageDTO) {
        IPage<Account> page = new Page<>(pageDTO.getCurrentPage(), pageDTO.getLimit());
        IPage<Account> accountIPage = AccountMapper.selectPage(page,null);
        System.out.println(accountIPage.getPages());
        System.out.println(accountIPage.getTotal());
        return accountIPage.getRecords();
    }
}
