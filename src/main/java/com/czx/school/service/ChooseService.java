package com.czx.school.service;

import com.czx.school.DTO.ChangeChooseDTO;
import com.czx.school.DTO.PageDTO;
import com.czx.school.common.PageResponse;
import com.czx.school.entity.Choose;

import java.util.List;

public interface ChooseService {
    // 增
    boolean add(Choose choose);
    // 删
    boolean deleteById(String id);
    boolean deleteByCid(String cid);
    boolean deleteBySno(String sno);
    // 改
    boolean update(ChangeChooseDTO changeChooseDTO);
    // 查
    Choose selectById(String id);
    List<Choose> selectByCid(String cid);
    List<Choose> selectBySno(String sno);
    PageResponse<Choose> getPages(PageDTO pageDTO);
}
