package com;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.QueryChainWrapper;
import com.entity.Administrators;
import com.entity.User;
import com.entity.UserVO;
import com.entity.Waybill;
import com.redis.RedisUtils;
import com.service.IAdministratorsService;
import com.service.IUserService;
import com.service.IWaybillService;
import com.utils.CreateMD5;
import com.utils.UUIDUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@SpringBootTest(classes = {test.class})
@RunWith(SpringRunner.class)
public class TestController {

    @Resource
    private RedisUtils redisUtils;


    @Resource
    private IAdministratorsService administratorsService;


    @Autowired
    private IUserService userService;

    @Autowired
    private IWaybillService waybillService;

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     *用户注册
     */
    @Test
    public void testRegistered() throws UnsupportedEncodingException {
        User user = new User();
        user.setUserName("user");
        HashMap<String,Object> map = new HashMap<>();
        map.put("userName" , user.getUserName());
        List<User> list = (List<User>) userService.listByMap(map);
        if(list != null && list.size() != 0){
            System.out.println(list);
            System.out.println("用户名已存在！");
        }else {
            user.setId(UUIDUtil.uuidStr());
            user.setPassWord(CreateMD5.getMd5("123456"));
            user.setCreateBy(user.getId());
            user.setCreateTime(df.format(new Date()));
            user.setLastmodificationTime(df.format(new Date()));
            user.setIsDelete(0);
            user.setModificationBy(user.getId());
            user.setSortNo(1);
            user.setVersion(1);
            user.setPhone("13653241589");
            userService.save(user);
            System.out.println(user);
        }
    }

    /**
     * 用户登录
     */
    @Test
    public void userLogin() throws UnsupportedEncodingException {
        HashMap<String , Object> map = new HashMap<>();
        map.put("userName" , "FANZAO");
        map.put("PassWord" , CreateMD5.getMd5("123456"));
        List<User> list = (List<User>) userService.listByMap(map);
        System.out.print(list);
    }

    /**
     * 修改密码
     */
    @Test
    public void  updatePassWord() throws UnsupportedEncodingException{
        UserVO user = new UserVO();
        user.setId("194ec17ce87a4a29bac67e780bc4fc8e");
        user.setPassWord(CreateMD5.getMd5("123456"));
        user.setNewPassword(CreateMD5.getMd5("654321"));
        User user1 = userService.getById(user);
        if (user1.getPassWord().equals(user.getPassWord())){
            System.out.println(user1.getUserName());
        }
        user1.setVersion(user1.getVersion()+1);
        user1.setPassWord(user.getNewPassword());
        user1.setModificationBy(user.getId());
        user1.setLastmodificationTime(df.format(new Date()));
        userService.updateById(user1);
    }

    /**
     * 管理员登录
     */
    @Test
    public void adminLogin() throws UnsupportedEncodingException {
        HashMap<String , Object> map = new HashMap<>();
        map.put("Account" , "admin");
        map.put("PassWord" , CreateMD5.getMd5("123456"));
        List<Administrators> list= (List<Administrators>) administratorsService.listByMap(map);
        System.out.println(list);
    }

    /**
     * 获取用户列表
     */
    @Test
    public void getUserList(){
//        User user = new User();
        IPage<User> page = new Page<>();
        page.setPages(0);
        page.setCurrent(0L);
        IPage<User> userIPage = userService.page(page);
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println(userIPage.getRecords().size());
        System.out.println(userIPage.getRecords());
    }

    /**
     *新增单个运单
     */
    @Test
    public void setWaybill(){
        Waybill waybill = new Waybill();
        waybill.setId(UUIDUtil.uuidStr());
        waybill.setWaybillNo(UUIDUtil.uuidStr());
        waybill.setSenderName("张三");
        waybill.setSenderAddress("广东河源");
        waybill.setSenderPhone("17865602343");
        waybill.setRecipientName("李四");
        waybill.setRecipientAddress("广东广州");
        waybill.setRecipientPhone("17865602430");
        waybill.setWeight(2.0);
        waybill.setState("待揽收");
        waybill.setTime(df.format(new Date()));
        waybill.setCreateBy("194ec17ce87a4a29bac67e780bc4fc8e");
        waybill.setCreateTime(df.format(new Date()));
        waybill.setModificationBy("194ec17ce87a4a29bac67e780bc4fc8e");
        waybill.setLastmodificationTime(df.format(new Date()));
        waybill.setIsDelete(0);
        waybill.setVersion(0);
        waybillService.save(waybill);
        System.out.println(waybillService.getById(waybill.getId()));
    }



    /**
     * 修改运单
     */
    @Test
    public void updateWaybill(){

    }
}
