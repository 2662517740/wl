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
public class Address extends BaseEntity {

    private static final long serialVersionUID = 1L;


    /**
     * 姓名
     */
    @TableField("Name")
    private String Name;

    /**
     * 电话
     */
    @TableField("Phone")
    private Integer Phone;

    /**
     * 地址
     */
    @TableField("Address")
    private String Address;

    /**
     * 归属
     */
    @TableField("Ascription")
    private String Ascription;

    /**
     * 是否默认地址
     */
    @TableField("isDefault")
    private Boolean isDefault;

}
