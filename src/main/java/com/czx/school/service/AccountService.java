package com.czx.school.service;

import com.czx.school.entity.Account;
import com.czx.school.DTO.LoginDTO;
import com.czx.school.DTO.RegisterDTO;

import java.util.List;

public interface AccountService {
    boolean login(LoginDTO loginDTO);
    Account selectByUsername(String username);
    Account selectById(Integer id);
    boolean register(RegisterDTO registerDTO);
    boolean delete(String username);
    List<Account> getPages(Integer currentPage,Integer limit);
}
