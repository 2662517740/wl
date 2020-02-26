package com.entity;

import java.time.LocalDateTime;
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
public class Logistics extends BaseEntity {

    private static final long serialVersionUID = 1L;


    /**
     * 运单号
     */
    @TableField("WaybillNo")
    private String WaybillNo;

    /**
     * 状态
     */
    @TableField("State")
    private String State;

    /**
     * 详情
     */
    @TableField("Details")
    private String Details;

    /**
     * 时间
     */
    @TableField("Time")
    private LocalDateTime Time;


}
