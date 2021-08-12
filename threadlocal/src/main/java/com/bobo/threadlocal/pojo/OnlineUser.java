package com.bobo.threadlocal.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Bobo
 * @date 2021/8/11
 * @apiNote 全局，当前登录用户信息存储DTO
 */
@Data
@AllArgsConstructor
public class OnlineUser implements Serializable {

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 姓名
     */
    private String name;

}
