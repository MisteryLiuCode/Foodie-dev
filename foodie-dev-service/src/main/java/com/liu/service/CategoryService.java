package com.liu.service;

import com.liu.pojo.Carousel;
import com.liu.pojo.Category;
import com.liu.pojo.vo.CategoryVO;

import java.util.List;

/**
 * @author liushuaibiao
 * @date 2023/5/6 10:35
 */
public interface CategoryService {

    /**
     * 查询所有一级 分类
     * @return
     */
    List<Category> queryAllRootLevelCat();


    List<CategoryVO> getSubCatList(Integer rootCatId);

}
