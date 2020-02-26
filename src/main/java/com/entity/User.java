package com.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2020-02-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;


    /**
     * 账号
     */
    @TableField("UserName")
    private String UserName;

    /**
     * 密码
     */
    @TableField("PassWord")
    private String PassWord;

    /**
     * 邮箱
     */
    @TableField("Email")
    private String Email;

    /**
     * 手机号
     */
    @TableField("Phone")
    private String Phone;

}
