package com.liu.controller;

import com.liu.common.RespResult;
import com.liu.common.enums.YesOrNo;
import com.liu.pojo.Carousel;
import com.liu.pojo.Category;
import com.liu.pojo.vo.CategoryVO;
import com.liu.service.CarouseService;
import com.liu.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("index")
@Api(value = "首页",tags = {"首页展示相关接口"})
public class IndexController {

    final private static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private CarouseService carouseService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/carousel")
    @ApiOperation(value = "查询轮播图",notes = "查询轮播图")
    public RespResult<Carousel> queryAll() {
        logger.info("查询轮播图开始");
        List<Carousel> carouselList = carouseService.queryAll(YesOrNo.YES.type);
        return new RespResult(carouselList);
    }

    /**
     * 首页分类展示需求
     * 1,第一次刷新主页查询大分类,渲染展示到首页
     * 2,如果鼠标移到大分类,则加载其子分类的内容,如果已存在子分类,则不需要加载(懒加载)
     */
    @ApiOperation(value = "获取商品分类,一级分类",notes = "获取商品分类,一级分类")
    @RequestMapping("/cats")
    public RespResult<Category> cats(){
        List<Category> categoryList = categoryService.queryAllRootLevelCat();
        return new RespResult(categoryList);
    }



    @ApiOperation(value = "获取商品子分类,二级分类",notes = "获取商品子分类,二级分类")
    @RequestMapping("/cats/{rootCatId}")
    public RespResult<Category> subCat(@ApiParam(name = "rootCatId",value = "一级分类id",required = true)
                                       @PathVariable Integer rootCatId){
        if (rootCatId==null){
            return new RespResult().setRetMsg("父级id不能为空");
        }
        List<CategoryVO> categoryList = categoryService.getSubCatList(rootCatId);
        return new RespResult(categoryList);
    }








}
