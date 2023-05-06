package com.liu.serviceImpl;

import com.liu.mapper.CarouselMapper;
import com.liu.mapper.CategoryCustomMapper;
import com.liu.mapper.CategoryMapper;
import com.liu.pojo.Carousel;
import com.liu.pojo.Category;
import com.liu.pojo.vo.CategoryVO;
import com.liu.service.CarouseService;
import com.liu.service.CategoryService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liushuaibiao
 * @date 2023/5/6 10:35
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private CategoryCustomMapper categoryCustomMapper;

    @Override
    public List<Category> queryAllRootLevelCat() {
        Example example = new Example(Carousel.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("type",1);
        List<Category> categoryList = categoryMapper.selectByExample(example);
        return categoryList;
    }

    public List<CategoryVO> getSubCatList(Integer rootCatId){
        List<CategoryVO> subCatList = categoryCustomMapper.getSubCatList(rootCatId);
        return subCatList;
    }



}
