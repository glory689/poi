package com.baizhi.service;

import com.baizhi.entity.Student;
import com.baizhi.mapper.PoiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PoiServiceImpl implements PoiService{
    @Autowired
    private PoiMapper poiMapper;
    @Override
    public void insert(List<Student> list) {
        poiMapper.insert(list);
    }
}
