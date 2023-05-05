package com.liu.controller;

import com.liu.common.RespResult;
import com.liu.common.utils.MD5Utils;
import com.liu.pojo.Users;
import com.liu.pojo.bo.UserBO;
import com.liu.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.security.NoSuchAlgorithmException;


/**
 * @author liushuaibiao
 * @date 2023/4/27 14:03
 */
@RestController
@RequestMapping(value = "/passport",method = {RequestMethod.POST,RequestMethod.GET})
@Api(value = "注册登录",tags={"用于注册登录相关接口"})
public class PassportController {

    @Autowired
    private UserService userService;

    /**
     * 校验用户是否存在
     * @param username
     * @return
     */
    @ApiOperation(value = "用户名是否存在",notes = "用户名是否存在")
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

    @ApiOperation(value = "用户注册",notes = "用户注册")
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

    /**
     * 检索用户名和密码是否匹配,用于登录
     */

    @ApiOperation(value = "用户登录",notes = "用户登录")
    @RequestMapping("/login")
    public RespResult<Users> login(@RequestBody UserBO userBO) throws NoSuchAlgorithmException {
        String username=userBO.getUsername();
        String password=userBO.getPassword();
        //校验
        //0.判断用户名和密码必须不能为空
        if (StringUtils.isBlank(username)
                || StringUtils.isBlank(password)
                ){
            return new RespResult().setRetMsg("用户名或密码不能为空");
        }
        //4.实现注册
        Users userResult = userService.queryUserForLogin(username, MD5Utils.getMD5Str(password));
        if (userResult==null){
            return new RespResult().setRetMsg("用户名或密码错误");
        }
        return new RespResult(userResult);
    }

    @ApiOperation(value = "用户退出登录",notes = "用户退出登录",httpMethod = "POST")
    @PostMapping("/logout")
    public RespResult logout(@RequestParam String userId){
        //清除用户相关信息的 cookie,这里我没使用 cookie,所以无需清除
        return new RespResult("success");
    }

}
