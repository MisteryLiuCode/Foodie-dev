package com.liu.service;

import com.liu.pojo.Carousel;

import java.util.List;

/**
 * @author liushuaibiao
 * @date 2023/5/6 10:35
 */
public interface CarouseService {

    List<Carousel> queryAll(Integer isShow);

}
