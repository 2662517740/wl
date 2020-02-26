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
public class Administrators extends BaseEntity {

    private static final long serialVersionUID = 1L;


    /**
     * 账号
     */
    @TableField("Account")
    private String Account;

    /**
     * 密码
     */
    @TableField("PassWord")
    private String PassWord;


}
