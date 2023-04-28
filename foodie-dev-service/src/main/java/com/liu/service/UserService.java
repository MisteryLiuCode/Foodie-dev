package com.liu.service;

import com.liu.pojo.Users;
import com.liu.pojo.bo.UserBO;
import org.springframework.stereotype.Service;

/**
 * @author liushuaibiao
 * @date 2023/4/27 13:46
 */
@Service
public interface UserService {


    /**
     * 判断用户名是否存在
     */
    boolean  queryUsernameIsExist(String username);

    /**
     * 创建用户
     * @param userReq
     * @return
     */
    int creatUser(UserBO userReq);

}
