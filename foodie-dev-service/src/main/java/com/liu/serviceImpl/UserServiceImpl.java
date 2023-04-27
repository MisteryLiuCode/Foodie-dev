package com.liu.serviceImpl;


import com.liu.mapper.UsersMapper;
import com.liu.pojo.Users;
import com.liu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

/**
 * @author liushuaibiao
 * @date 2023/4/27 13:46
 */
@Service
public class UserServiceImpl implements UserService {

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
}
