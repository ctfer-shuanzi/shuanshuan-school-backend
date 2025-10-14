package com.czx.school.service;

import com.czx.school.DO.Account;
import com.czx.school.DTO.LoginDTO;
import com.czx.school.DTO.RegisterDTO;

public interface AccountService {
    boolean login(LoginDTO loginDTO);
    Account selectByUsername(String username);
    boolean register(RegisterDTO registerDTO);
}
