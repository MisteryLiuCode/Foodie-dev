package com.liu.mapper;


import com.liu.mapper.my.MyMapper;
import com.liu.pojo.Category;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper extends MyMapper<Category> {



}