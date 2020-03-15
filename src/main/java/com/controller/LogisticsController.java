package com.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.entity.Logistics;
import com.redis.RedisUtils;
import com.service.ILogisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-02-26
 */
@RestController
@RequestMapping("/com/logistics")
@Slf4j
@Api("物流管理")
@Transactional(readOnly = false)
public class LogisticsController {

    @Autowired
    private RedisUtils redisUtils;

    @Resource
    private ILogisticsService logisticsService;

    /**
     * @Description：通过运单号查询运单物流
     * @Date:2020/03/15
     * @Param:Logistics
     */
    @ApiOperation(value = "通过运单号查询运单物流")
    @PostMapping("getlogistics")
    @Transactional(readOnly = true)
    public IPage<Logistics> getlogistics(Logistics logistics){
        IPage<Logistics> page = new Page<>();
        page.setCurrent(0L);
        page.setPages(0);
        IPage<Logistics> logisticsIPage = logisticsService.page(page,new QueryWrapper<Logistics>().eq("WaybillNo",logistics.getWaybillNo()).orderByDesc("create_time"));
        return logisticsIPage;
    }



}
