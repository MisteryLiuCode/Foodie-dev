package com.liu.serviceImpl;


import com.liu.common.utils.DateUtils;
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
    public Users creatUser(UserBO userReq) {
        Users users = new Users();
        users.setUsername(userReq.getUsername());
        try {
            users.setPassword(MD5Utils.getMD5Str(userReq.getPassword()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        users.setNickname(userReq.getUsername());
        users.setFace(USER_FACE);
        try {
            users.setBirthday(DateUtils.parse("1900-01-01",DateUtils.DATE_FORMAT_DATE_ONLY));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
