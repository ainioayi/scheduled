package com.bobo.threadlocal;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Bobo
 * @date 2021/8/11
 * @apiNote 数据库拦截器
 */
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {

    private String createTime = "createTime", createBy = "createBy", updateTime = "updateTime", updateBy = "updateBy",isDeleted="isDeleted";

    /**
     * 新增拦截
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        LocalDateTime date = LocalDateTime.now();
        this.setFieldValByName(createTime, date, metaObject);
        this.setFieldValByName(updateTime, date, metaObject);
        this.setFieldValByName(isDeleted, 0, metaObject);
        try{
            //获取登录用户信息
            Long userId = ThreadLocalHolder.getId();
            this.setFieldValByName(createBy, userId, metaObject);
            this.setFieldValByName(updateBy, userId, metaObject);
        }catch (Exception e){
            log.error("更新新增时间和人异常:[{}]" , e.getMessage() );
            this.setFieldValByName(createBy, "", metaObject);
            this.setFieldValByName(updateBy, "", metaObject);
        }
    }

    /**
     * 修改拦截
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        this.setFieldValByName(updateTime, LocalDateTime.now(), metaObject);
        try{
            //获取登录用户信息
            Long userId = ThreadLocalHolder.getId();
            this.setFieldValByName(updateBy, userId, metaObject);
        }catch (Exception e){
            log.error("更新修改时间和人异常:[{}]" , e.getMessage() );
            this.setFieldValByName(updateBy, "", metaObject);
        }
    }
}
