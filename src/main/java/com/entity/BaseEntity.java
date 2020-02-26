package com.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;

@Data
public class BaseEntity implements Serializable {

    @TableId("ID")
    private String id;
    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改人
     */
    private String modificationBy;

    /**
     * 修改时间
     */
    @TableField("lastmodification_Time")
    private String lastmodificationTime;

    /**
     * 是否删除
     */
    @TableLogic
    private int isDelete;

    /**
     * 序号
     */
    @TableField("sort_No")
    private int sortNo;

    private int version;//版本号

}
