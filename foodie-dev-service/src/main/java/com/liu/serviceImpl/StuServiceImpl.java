package com.liu.serviceImpl;

import com.liu.mapper.StuMapper;
import com.liu.pojo.Stu;
import com.liu.service.StuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author liushuaibiao
 * @date 2023/4/21 14:30
 */
@Service
public class StuServiceImpl implements StuService {

    @Resource
    private StuMapper stuMapper;

    @Override
    public Stu getStuById(Integer id) {
        return stuMapper.selectByPrimaryKey(id);
    }
}
