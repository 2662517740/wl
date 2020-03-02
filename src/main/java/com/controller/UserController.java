package com.controller;


import com.entity.User;
import com.entity.UserVO;
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
import java.util.Date;
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
@RequestMapping("/com/user")
@Slf4j
@Api("用户管理")
@Transactional(readOnly = false)
public class UserController {

    @Autowired
    private RedisUtils redisUtils;

    @Resource
    private IUserService userService;

    @Value("${loginTimeOut}")
    private Integer loginTimeOut;

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * @Description：用户注册
     * @Date:2020/02/26
     * @Param:User
     */
    @ApiOperation(value = "用户注册")
    @PostMapping(value = "Registered")
    @Transactional(rollbackFor = Exception.class)
    public AppResponse Registered(User user) throws UnsupportedEncodingException {
        HashMap<String,Object> map = new HashMap<>();
        map.put("UserName" , user.getUserName());
        List<User> list = (List<User>) userService.listByMap(map);
        if (list != null && list.size() != 0){
            return AppResponse.bizError("用户名已存在，请重新输入！");
        }else {
            user.setId(UUIDUtil.uuidStr());
            user.setPassWord(CreateMD5.getMd5(user.getPassWord()));
            user.setCreateBy(user.getId());
            user.setCreateTime(df.format(new Date()));
            user.setLastmodificationTime(df.format(new Date()));
            user.setIsDelete(0);
            user.setModificationBy(user.getId());
            user.setSortNo(1);
            user.setVersion(1);
            boolean save = userService.save(user);
            if (save){
                return AppResponse.success("注册成本");
            }
            return AppResponse.bizError("注册失败，请重试！");
        }
    }

    /**
     * @Description：用户登陆
     * @Date:2020/02/26
     * @Param:UserVO
     */
    @ApiOperation(value = "用户登录")
    @PostMapping(value = "userLogin")
    @Transactional(readOnly = true)
    public AppResponse userLogin(UserVO user) throws UnsupportedEncodingException {
        HashMap<String , Object> map = new HashMap<>();
        map.put("UserName" , user.getUserName());
        map.put("PassWord" ,CreateMD5.getMd5(user.getPassWord()));
//        UserVO vo = new UserVO();
        List<User> list = (List<User>) userService.listByMap(map);
//        String token = "";
        if (list != null && list.size() == 1){
            String token = UUIDUtil.uuidStr();
            redisUtils.LoginSet(token+"_UserName" ,list.get(0).getId(),loginTimeOut);
            redisUtils.LoginSet(token+"_ID",list.get(0).getId(),loginTimeOut);
            user.setToken(token);
        }else {
            return AppResponse.bizError("登录失败，请重试");
        }
        return AppResponse.success("登录成功！" , user);
    }

    /**
     * @Description：修改密码
     * @Date:2020/02/26
     * @Param:UserVO
     */
    @ApiOperation(value = "修改密码")
    @PostMapping(value = "updatePassWord")
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updatePassWord(UserVO user) throws UnsupportedEncodingException{
        user.setPassWord(CreateMD5.getMd5(user.getPassWord()));
        user.setNewPassword(CreateMD5.getMd5(user.getNewPassword()));
        User user1 = userService.getById(user);
        if (user.getPassWord().equals(user1.getPassWord())){
            user1.setVersion(user1.getVersion()+1);
            user1.setPassWord(user.getNewPassword());
            user1.setModificationBy(user.getId());
            user1.setLastmodificationTime(df.format(new Date()));
            userService.updateById(user1);
            return AppResponse.success("修改成功！");
        }else {
            return AppResponse.bizError("修改失败，请重试！");
        }
    }


}
