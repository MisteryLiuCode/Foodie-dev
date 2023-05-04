package com.liu.serviceImpl;


import cn.hutool.core.collection.CollectionUtil;
import com.liu.common.enums.Sex;
import com.liu.common.utils.DateUtils;
import com.liu.common.utils.IdUtils;
import com.liu.common.utils.MD5Utils;
import com.liu.mapper.UsersMapper;
import com.liu.pojo.Users;
import com.liu.pojo.bo.UserBO;
import com.liu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import tk.mybatis.mapper.entity.Example;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Date;

/**
 * @author liushuaibiao
 * @date 2023/4/27 13:46
 */
@Service
public class UserServiceImpl implements UserService {

    private final String USER_FACE="https://misteryliu.oss-cn-beijing.aliyuncs.com/imageimage-20230427180842406.png";

    @Autowired
    public UsersMapper usersMapper;

    /**
     * @Transactional(propagation = Propagation.SUPPORTS)
     * SUPPORTS顾名思义是支持,所以意思是如果有其他bean调用这个事务
     * 那么他也支持事务,如果调用这个bean的事务不支持事务,那么他也不支持
     * @param username
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUsernameIsExist(String username) {
        Example example = new Example(Users.class);
        example.createCriteria().andEqualTo("username",username);
        Users user = usersMapper.selectOneByExample(example);
        return user==null? false : true;
    }

    @Transactional(propagation =Propagation.REQUIRED)
    @Override
    public int creatUser(UserBO userReq) {
        Users user = new Users();
        user.setId(IdUtils.snowflakeId());
        user.setUsername(userReq.getUsername());
        try {
            user.setPassword(MD5Utils.getMD5Str(userReq.getPassword()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        user.setNickname(userReq.getUsername());
        user.setFace(USER_FACE);
        try {
            user.setBirthday(DateUtils.parse("1900-01-01",DateUtils.DATE_FORMAT_DATE_ONLY));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        //默认性别为保密
        user.setSex(Sex.serect.type);
        user.setCreatedTime(new Date());
        user.setUpdatedTime(new Date());
        return usersMapper.insert(user);
    }

    @Override
    public Users queryUserForLogin(String username, String password) {
        Example example = new Example(Users.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("username",username).andEqualTo("password",password);
        Users users = usersMapper.selectOneByExample(example);
        return users;
    }


}
