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
public class Supplement extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 运单号
     */
    @TableField("WaybillNo")
    private String WaybillNo;

    /**
     * 原因
     */
    @TableField("Reason")
    private String Reason;

}
