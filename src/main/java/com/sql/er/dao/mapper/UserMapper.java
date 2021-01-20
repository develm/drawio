package com.sql.er.dao.mapper;

import com.sql.er.dao.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    int insert(User record);

    User selectByPrimaryKey(Long id);

    User selectByPhone(String phone);

    int updateByPrimaryKey(User record);
}