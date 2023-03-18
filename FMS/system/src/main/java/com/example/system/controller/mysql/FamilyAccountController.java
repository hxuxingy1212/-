package com.example.system.controller.mysql;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.system.base.Result.ResultBody;
import com.example.system.base.config.LoginUser;
import com.example.system.pojo.FamilyAccounts;
import com.example.system.pojo.Role;
import com.example.system.pojo.UserInfo;
import com.example.system.service.redis.AppellationService;
import com.example.system.service.mysql.FamilyAccountsService;
import com.example.system.service.mysql.RoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/FamilyAccounts")
public class FamilyAccountController {

    @Autowired
    private FamilyAccountsService service;

    @Autowired
    private AppellationService appellationService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StringRedisTemplate redisTemplate;


    @ApiOperation("登陆验证")
    @GetMapping("/login")
    private ResultBody login(@RequestParam("account") String account,
                             @RequestParam("password") String password,
                             HttpServletRequest request) {

        //从前端获取token
        String tokenx = request.getHeader("authorization");
        //token获取不到则直接返回
        if (tokenx != null && !tokenx.equals("")) {
            return new ResultBody(true, tokenx);
        }


        if (account == null && password == null && account.equals("") && password.equals("")) {
            return ResultBody.error("入参错误");
        }

        LambdaQueryWrapper<FamilyAccounts> queryWrapper = new LambdaQueryWrapper<FamilyAccounts>()
                .eq(FamilyAccounts::getAccount, account)
                .eq(FamilyAccounts::getPassword, password);
        FamilyAccounts familyAccount = service.getOne(queryWrapper);
        if (familyAccount != null) {
            //生成随机验证码
            String token = RandomUtil.randomString(16);
            //放到redis中去
            //复制familyAccount里的属性到UserInfo里
            UserInfo userInfo = BeanUtil.copyProperties(familyAccount, UserInfo.class).setToken(token);
            redisTemplate.opsForHash().putAll("token:" + token, BeanUtil.beanToMap(userInfo, new HashMap<>(),
                    CopyOptions.create().setIgnoreNullValue(true)
                            .setFieldValueEditor((fieldName, fieldValue) -> fieldValue.toString())));//将值转为字符串，不然报转换异常错
            //设置一小时过期
            redisTemplate.expire("token:" + token, 1, TimeUnit.HOURS);
            return new ResultBody(token);
        } else {
            return ResultBody.error("登陆失败");
        }
    }

    @ApiOperation("注册")
    @GetMapping("/register")
    private ResultBody register(@RequestParam("account") String account,
                                @RequestParam("password") String password) {

        if (account == null && password == null && account.equals("") && password.equals("")) {
            return ResultBody.error("入参错误");
        }

        LambdaQueryWrapper<FamilyAccounts> queryWrapper = new LambdaQueryWrapper<FamilyAccounts>()
                .eq(FamilyAccounts::getAccount, account);
        FamilyAccounts familyAccount = service.getOne(queryWrapper);
        if (familyAccount != null) {
            return ResultBody.error("已经注册过");
        } else {
            FamilyAccounts familyAccounts = new FamilyAccounts().setAccount(account).setPassword(password);
            boolean save = service.save(familyAccounts);
            if (save) {
                //注册时添加称谓关系我（redis）
                appellationService.add("我", account);
                //注册时添加称谓关系我（mysql）
                roleService.save(new Role().setFamily_account(account).setName("无名").setAppellation("我"));
                //生成随机验证码
                String token = RandomUtil.randomString(16);
                //查找账号
                LambdaQueryWrapper<FamilyAccounts> qw = new LambdaQueryWrapper<FamilyAccounts>()
                        .eq(FamilyAccounts::getAccount, account)
                        .eq(FamilyAccounts::getPassword, password);
                FamilyAccounts one = service.getOne(queryWrapper);
                //复制familyAccount里的属性到UserInfo里
                UserInfo userInfo = BeanUtil.copyProperties(one, UserInfo.class).setToken(token);
                redisTemplate.opsForHash().putAll("token:" + token, BeanUtil.beanToMap(userInfo, new HashMap<>(),
                        CopyOptions.create().setIgnoreNullValue(true)
                                .setFieldValueEditor((fieldName, fieldValue) -> fieldValue.toString())));//将值转为字符串，不然报转换异常错
                //设置一小时过期
                redisTemplate.expire("token:" + token, 1, TimeUnit.HOURS);

                System.out.println("注册token" + token);
                return new ResultBody(true, token);
            } else {
                return ResultBody.error("注册失败");
            }
        }

    }

    @ApiOperation("获取用户基础信息")
    @GetMapping("getBaseInfo")
    private ResultBody getAllUserInfo() {

        System.out.println(System.currentTimeMillis());

        //获取用户账号签名
        FamilyAccounts familyAccounts = service.getById(LoginUser.getUser().getId());

        System.out.println(System.currentTimeMillis());

        return familyAccounts != null ? new ResultBody(true, familyAccounts) :
                new ResultBody(false, "获取用户基础信息失败");
    }

    @ApiOperation("修改用户基础信息")
    @PostMapping("saveBaseInfo")
    private ResultBody updateBaseInfo(@RequestBody FamilyAccounts familyAccounts) {
        if (familyAccounts != null && familyAccounts.getAccount() != null
                && familyAccounts.getPassword() != null && familyAccounts.getAutograph() != null) {
            boolean b = service.updateById(familyAccounts);
            return b ? ResultBody.ok("修改成功") : ResultBody.error("修改失败");
        }
        return ResultBody.error("修改失败");
    }


}
