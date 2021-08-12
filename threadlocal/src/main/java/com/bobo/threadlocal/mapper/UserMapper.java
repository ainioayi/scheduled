package com.bobo.threadlocal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bobo.threadlocal.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * @author Bobo
 * @date 2021/8/11
 * @apiNote
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

}
