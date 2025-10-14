package com.czx.school.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.czx.school.DO.SC;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czx.school.DTO.ChangeGradeDTO;
import com.czx.school.DTO.ChangeStudentNameDTO;
import com.czx.school.DTO.SelcetSCDTO;
import com.czx.school.mapper.SCMapper;
import com.czx.school.service.SCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SCServiceImpl extends ServiceImpl<SCMapper, SC> implements SCService {
    @Autowired
    private SCMapper scMapper;
    @Override
    public boolean addSC(SC sc){
        return scMapper.insert(sc) > 0;
    }
    @Override
    public boolean deleteBySnoAndCid(SelcetSCDTO selectSelcetSCDTO){
        LambdaQueryWrapper<SC> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SC::getSno, selectSelcetSCDTO.getSno());
        queryWrapper.eq(SC::getCid, selectSelcetSCDTO.getCid());
        return scMapper.delete(queryWrapper) > 0;
    }
    @Override
    public boolean deleteBySno(String sno){
        LambdaQueryWrapper<SC> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SC::getSno, sno);
        return scMapper.delete(queryWrapper) > 0;
    }
    @Override
    public boolean deleteByCid(String cid){
        LambdaQueryWrapper<SC> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SC::getCid, cid);
        return scMapper.delete(queryWrapper) > 0;
    }
    @Override
    public SC selectBySnoAndCid(SelcetSCDTO selectSelcetSCDTO){
        LambdaQueryWrapper<SC> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SC::getSno, selectSelcetSCDTO.getSno());
        queryWrapper.eq(SC::getCid, selectSelcetSCDTO.getCid());
        return scMapper.selectOne(queryWrapper);
    }
    @Override
    public List<SC> selectBySno(String sno){
        LambdaQueryWrapper<SC> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SC::getSno, sno);
        return scMapper.selectList(queryWrapper);
    }
    @Override
    public List<SC> selectByCid(String cid){
        LambdaQueryWrapper<SC> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SC::getCid, cid);
        return scMapper.selectList(queryWrapper);
    }
    @Override
    public boolean updateByStudentNameAndCourseName(ChangeGradeDTO changeGradeDTO){
        LambdaQueryWrapper<SC> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SC::getSno, changeGradeDTO.getSno());
        queryWrapper.eq(SC::getCid, changeGradeDTO.getCid());
        SC updateSC = new SC();
        updateSC.setGrade(changeGradeDTO.getGrade());
        return scMapper.update(updateSC, queryWrapper) > 0;
    }
}