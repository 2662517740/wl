package com.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.entity.Waybill;
import com.entity.WaybillVO;
import com.entity.response.AppResponse;
import com.redis.RedisUtils;
import com.service.IWaybillService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-02-26
 */
@RestController
@RequestMapping("/com/waybill")
@Slf4j
@Api("运单管理")
@Transactional(readOnly = false)
public class WaybillController {

    @Autowired
    private RedisUtils redisUtils;

    @Resource
    private IWaybillService waybillService;

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * @Description：新增单个运单
     * @Date:2020/03/04
     * @Param:WaybillVO
     */
    @ApiOperation(value = "新增单个运单")
    @PostMapping("setWaybill")
    @Transactional(rollbackFor = Exception.class)
    public AppResponse setWaybill(WaybillVO waybill){
        waybill.setTime(df.format(new Date()));
        waybill.setCreateBy(redisUtils.get(waybill.getToken()+"_UserName"));
        waybill.setCreateTime(df.format(new Date()));
        waybill.setModificationBy(redisUtils.get(waybill.getToken()+"_UserName"));
        waybill.setLastmodificationTime(df.format(new Date()));
        waybill.setIsDelete(0);
        waybill.setSortNo(0);
        waybill.setVersion(0);
        boolean save = waybillService.save(waybill);
        if (save){
            return AppResponse.success("新增成功！");
        }
        return AppResponse.bizError("新增失败，请重试！");
    }

    /**
     * @Description：修改运单
     * @Date:2020/03/08
     * @Param:WaybillVO
     */
    @ApiOperation(value = "修改运单")
    @GetMapping("updateWaybill")
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateWaybill(WaybillVO waybill){
        waybill.setVersion(waybill.getVersion()+1);
        waybill.setModificationBy(waybill.getId());
        waybill.setLastmodificationTime(df.format(new Date()));
        boolean update = waybillService.updateById(waybill);
        if (update) {
            return AppResponse.success("修改成功！");
        }
        return AppResponse.bizError("修改失败，请重试!");
    }

    /**
     * @Description：查询单个运单
     * @Date:2020/03/08
     * @Param:Waybill
     */
    @ApiOperation(value = "查询单个运单")
    @PostMapping("getWaybill")
    @Transactional(readOnly = true)
    public Waybill getWaybill(Waybill waybill){
        Waybill waybill1 = waybillService.getById(waybill);
        return waybill1;
    }

    /**
     * @Description：查询运单列表
     * @Date:2020/03/08
     * @Param:Waybill
     */
    @ApiOperation(value = "查询运单列表")
    @GetMapping("getWaybillList")
    @Transactional(readOnly = true)
    public IPage<Waybill> getWaybillList(){
        IPage<Waybill> page = new Page<>();
        page.setPages(0);
        page.setCurrent(0L);
        IPage<Waybill> waybillIPage = waybillService.page(page);
        return waybillIPage;
    }

}
