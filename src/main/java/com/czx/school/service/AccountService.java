package com.czx.school.service;

import com.czx.school.DTO.ChangeAccountDTO;
import com.czx.school.DTO.PageDTO;
import com.czx.school.VO.AccountVO;
import com.czx.school.common.PageResponse;
import com.czx.school.entity.Account;
import com.czx.school.DTO.LoginDTO;
import com.czx.school.DTO.RegisterDTO;

public interface AccountService {
    // 增
    boolean register(RegisterDTO registerDTO);
    // 删
    boolean delete(String username);
    // 改
    boolean login(LoginDTO loginDTO);
    boolean update(ChangeAccountDTO changeAccountDTO);
    // 查
    Account selectByUsername(String username);
    Account selectById(Integer id);
    PageResponse<AccountVO> getPages(PageDTO pageDTO);
}