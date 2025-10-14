package com.czx.school.service;

import com.czx.school.DO.SC;
import com.czx.school.DTO.ChangeGradeDTO;
import com.czx.school.DTO.SelcetSCDTO;

import java.util.List;

public interface SCService {
    boolean addSC(SC sc);
    boolean deleteBySnoAndCid(SelcetSCDTO selectSelcetSCDTO);
    boolean deleteByCid(String cid);
    boolean deleteBySno(String sno);
    SC selectBySnoAndCid(SelcetSCDTO selectSelcetSCDTO);
    List<SC> selectByCid(String cid);
    List<SC> selectBySno(String sno);
    boolean updateByStudentNameAndCourseName(ChangeGradeDTO changeGradeDTO);
}
