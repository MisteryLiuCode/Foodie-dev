package com.liu.controller;

import com.liu.common.RespResult;
import com.liu.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author liushuaibiao
 * @date 2023/4/27 14:03
 */
@RestController
@RequestMapping("/passport")
public class PassportController {

    @Autowired(required = false)
    private UserService userService;

    /**
     * 校验用户是否存在
     * @param username
     * @return
     */
    @GetMapping("/usernameIsExist")
    public RespResult<Integer> usernameIsExist(@RequestParam String username){

        //判断用户名不能为空
        if (StringUtils.isBlank(username)){
            return new RespResult<>(500);
        }
        //查找用户名是否存在
        boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist){
            return new RespResult<>(500);
        }
        //请求成功,用户名没有重复
        return new RespResult<>(200);
    }




}
