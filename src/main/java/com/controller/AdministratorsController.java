package com.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.entity.Administrators;
import com.entity.AdministratorsVO;
import com.entity.User;
import com.entity.response.AppResponse;
import com.redis.RedisUtils;
import com.service.IAdministratorsService;
import com.service.IUserService;
import com.utils.CreateMD5;
import com.utils.UUIDUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-02-26
 */
@RestController
@RequestMapping("/com/administrators")
@Slf4j
@Api("管理员管理")
@Transactional(readOnly = false)
public class AdministratorsController {

    @Resource
    private IAdministratorsService administratorsService;

    @Autowired
    private RedisUtils redisUtils;

    @Resource
    private IUserService userService;

    @Value("${loginTimeOut}")
    private Integer loginTimeOut;

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * @Description：用户登陆
     * @Date:2020/03/02
     * @Param:UserVO
     */
    @ApiOperation(value = "管理员登录")
    @PostMapping(value = "adminLogin")
    @Transactional(readOnly = true)
    public AppResponse adminLogin(AdministratorsVO administrators) throws UnsupportedEncodingException {
        HashMap<String , Object> map = new HashMap<>();
        map.put("Account" , administrators.getAccount());
        map.put("PassWord" , CreateMD5.getMd5(administrators.getPassWord()));
        List<Administrators> list= (List<Administrators>) administratorsService.listByMap(map);
        if (list != null && list.size() == 1){
            String token = UUIDUtil.uuidStr();
            redisUtils.LoginSet(token+"_Account",list.get(0).getAccount(),loginTimeOut);
            redisUtils.LoginSet(token+"_AccountID",list.get(0).getId(),loginTimeOut);
            administrators.setToken(token);
            return AppResponse.success("登录成功！",administrators);
        }else {
            return AppResponse.bizError("登录失败请重试");
        }
    }

    /**
     * @Description：用户登陆
     * @Date:2020/03/02
     * @Param:UserVO
     */
    @ApiOperation(value = "获取用户列表")
    @PostMapping(value = "getUserList")
    @Transactional(readOnly = true)
    public IPage<User> getUserList(){
        IPage<User> page = new Page<>();
        page.setPages(0);
        page.setCurrent(0L);
        IPage<User> userIPage = userService.page(page);
        return userIPage;
    }
}
