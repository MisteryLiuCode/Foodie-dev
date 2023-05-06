package com.liu.mapper;


import com.liu.mapper.my.MyMapper;
import com.liu.pojo.Category;
import com.liu.pojo.vo.CategoryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryCustomMapper extends MyMapper<Category> {

    List<CategoryVO> getSubCatList(Integer rootCatId);


}