package com.liu.controller;

import com.liu.common.RespResult;
import com.liu.pojo.bo.UserBO;
import com.liu.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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

    @RequestMapping("/regist")
    public RespResult regist(@RequestBody UserBO userBO){

        String username=userBO.getUsername();
        String password=userBO.getPassword();
        String confirmPdw=userBO.getConfirmPassword();
        //校验
        //0.判断用户名和密码必须不能为空
        if (StringUtils.isBlank(username)
                || StringUtils.isBlank(username)
                || StringUtils.isBlank(username)){
            return new RespResult().setRetMsg("用户名或密码不能为空");
        }
        //1.判断用户名是否存在
        boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist){
            return new RespResult().setRetMsg("用户名已经存在");
        }
        //2.密码长度不能少于6位
        if (password.length()<6){
            return new RespResult().setRetMsg("密码长度不能少于6");
        }
        //3.判断两次密码是否一致
        if (!password.equals(confirmPdw)){
            return new RespResult().setRetMsg("两次密码输入不一致");
        }
        //4.实现注册
        userService.creatUser(userBO);
        return new RespResult("success");
    }
}
