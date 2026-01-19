package com.czx.school.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.czx.school.DTO.ChangeChooseDTO;
import com.czx.school.DTO.PageDTO;
import com.czx.school.common.PageResponse;
import com.czx.school.entity.Choose;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czx.school.entity.Student;
import com.czx.school.mapper.ChooseMapper;
import com.czx.school.service.ChooseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChooseServiceImpl extends ServiceImpl<ChooseMapper, Choose> implements ChooseService {
    @Autowired
    private ChooseMapper chooseMapper;
    // 增
    @Override
    public boolean add(Choose choose){return chooseMapper.insert(choose) > 0;}
    // 删
    @Override
    public boolean deleteById(String id) {return chooseMapper.deleteById(id);}
    @Override
    public boolean deleteBySno(String sno){return chooseMapper.delete(new LambdaQueryWrapper<Choose>().eq(Choose::getSno, sno)) > 0;}
    @Override
    public boolean deleteByCid(String cid){return chooseMapper.delete(new LambdaQueryWrapper<Choose>().eq(Choose::getCid, cid)) > 0;}
    // 改
    @Override
    public boolean update(ChangeChooseDTO changeChooseDTO) {
        Choose choose = new Choose();
        BeanUtils.copyProperties(changeChooseDTO,choose);

        return chooseMapper.update(choose,new UpdateWrapper<Choose>().eq("id",changeChooseDTO.getId())) > 0;
    }
    // 查
    @Override
    public Choose selectById(String id) {
        return chooseMapper.selectOne(new LambdaQueryWrapper<Choose>().eq(Choose::getId,id));
    }
    @Override
    public List<Choose> selectBySno(String sno){
        return chooseMapper.selectList(new LambdaQueryWrapper<Choose>().eq(Choose::getSno, sno));
    }
    @Override
    public List<Choose> selectByCid(String cid){
        return chooseMapper.selectList(new LambdaQueryWrapper<Choose>().eq(Choose::getCid, cid));
    }
    @Override
    public PageResponse<Choose> getPages(PageDTO pageDTO) {
        IPage<Choose> page = new Page<>(pageDTO.getCurrentPage(),pageDTO.getLimit());
        IPage<Choose> result = chooseMapper.selectPage(page,null);

        List<Choose> records = result.getRecords();

        PageResponse<Choose> pageResponse = new PageResponse<>();
        pageResponse.setList(records);
        pageResponse.setTotal(result.getTotal());
        pageResponse.setCurrent(result.getCurrent());
        pageResponse.setSize(result.getSize());
        pageResponse.setPages(result.getPages());

        return pageResponse;
    }
}