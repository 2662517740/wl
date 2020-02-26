package com.entity;

import java.math.BigDecimal;
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
public class Waybill extends BaseEntity {

    private static final long serialVersionUID = 1L;


    /**
     * 运单号
     */
    @TableField("WaybillNo")
    private String WaybillNo;

    /**
     * 寄件人姓名
     */
    @TableField("SenderName")
    private String SenderName;

    /**
     * 寄件人地址
     */
    @TableField("SenderAddress")
    private String SenderAddress;

    /**
     * 寄件人电话
     */
    @TableField("SenderPhone")
    private Integer SenderPhone;

    /**
     * 收件人姓名
     */
    @TableField("RecipientName")
    private String RecipientName;

    /**
     * 收件人地址
     */
    @TableField("RecipientAddress")
    private String RecipientAddress;

    /**
     * 收件人电话
     */
    @TableField("RecipientPhone")
    private Integer RecipientPhone;

    /**
     * 重量
     */
    @TableField("Weight")
    private BigDecimal Weight;

    /**
     * 备注
     */
    @TableField("Remarks")
    private String Remarks;

    /**
     * 状态
     */
    @TableField("State")
    private String State;

    /**
     * 时间
     */
    @TableField("Time")
    private LocalDateTime Time;

    /**
     * 是否换单
     */
    @TableField("isChange")
    private Boolean isChange;

    /**
     * 是否补单
     */
    @TableField("isSupplement")
    private Boolean isSupplement;

    /**
     * 是否退回
     */
    @TableField("isReturn")
    private Boolean isReturn;


}
