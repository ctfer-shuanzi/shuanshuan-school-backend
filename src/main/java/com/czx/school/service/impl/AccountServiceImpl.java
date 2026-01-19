package com.czx.school.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czx.school.DTO.ChangeAccountDTO;
import com.czx.school.DTO.PageDTO;
import com.czx.school.VO.AccountVO;
import com.czx.school.common.PageResponse;
import com.czx.school.entity.Account;
import com.czx.school.DTO.LoginDTO;
import com.czx.school.DTO.RegisterDTO;
import com.czx.school.mapper.AccountMapper;
import com.czx.school.service.AccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService{
    @Autowired
    private AccountMapper accountMapper;
    // 增
    @Override
    public boolean register(RegisterDTO registerDTO) {
        Account account = new Account();
        BeanUtils.copyProperties(registerDTO,account);

        account.setCreateTime(LocalDateTime.now());
        account.setLastLoginTime(LocalDateTime.now());
        account.setDeleted(0);

        return accountMapper.insert(account) > 0;
    }
    // 删
    @Override
    public boolean delete(String username) {
        Account account = accountMapper.selectOne(new LambdaQueryWrapper<Account>().eq(Account::getUsername,username));

        if(account.getDeleted() == 0){
            return accountMapper.update(new UpdateWrapper<Account>().eq("username",username).set("deleted",1)) > 0;
        }

        return accountMapper.deleteById(account.getId()) > 0;
    }
    // 改
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
    public boolean update(ChangeAccountDTO changeAccountDTO) {
        UpdateWrapper<Account> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("username",changeAccountDTO.getOldName());

        Account newAccount = new Account();
        BeanUtils.copyProperties(changeAccountDTO,newAccount);

        return accountMapper.update(newAccount,updateWrapper) > 0;
    }
    // 查
    @Override
    public Account selectByUsername(String username) {
        LambdaQueryWrapper<Account> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Account::getUsername, username);
        return accountMapper.selectOne(queryWrapper);
    }
    @Override
    public Account selectById(Integer id) {
        LambdaQueryWrapper<Account> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Account::getId,id);
        return accountMapper.selectOne(queryWrapper);
    }
    @Override
    public PageResponse<AccountVO> getPages(PageDTO pageDTO) {
        IPage<Account> page = new Page<>(pageDTO.getCurrentPage(), pageDTO.getLimit());
        IPage<Account> result = accountMapper.selectPage(page,null);

        List<Account> records = result.getRecords();
        List<AccountVO> voList = new ArrayList<>();

        if(records!=null){
            for(Account record : records){
                AccountVO accountVO = new AccountVO();
                BeanUtils.copyProperties(record,accountVO);
                voList.add(accountVO);
            }
        }

        PageResponse<AccountVO> pageResult = new PageResponse<>();
        pageResult.setList(voList);
        pageResult.setTotal(result.getTotal());
        pageResult.setCurrent(result.getCurrent());
        pageResult.setSize(result.getSize());
        pageResult.setPages(result.getPages());

        return pageResult;
    }
}