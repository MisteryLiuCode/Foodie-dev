package com.liu.mapper;


import com.liu.mapper.my.MyMapper;
import com.liu.pojo.Users;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsersMapper extends MyMapper<Users> {
}