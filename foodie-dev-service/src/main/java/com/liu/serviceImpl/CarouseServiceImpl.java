package com.liu.serviceImpl;

import com.liu.mapper.CarouselMapper;
import com.liu.pojo.Carousel;
import com.liu.service.CarouseService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liushuaibiao
 * @date 2023/5/6 10:35
 */
@Service
public class CarouseServiceImpl implements CarouseService {

    @Resource
    private CarouselMapper carouselMapper;

    @Override
    public List<Carousel> queryAll(Integer isShow) {
        Example example = new Example(Carousel.class);
        example.orderBy("sort").desc();
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isShow",isShow);
        List<Carousel> carouselList = carouselMapper.selectByExample(example);
        return carouselList;
    }
}
