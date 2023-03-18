package com.example.system.base.config;

import cn.hutool.core.bean.BeanUtil;
import com.example.system.pojo.UserInfo;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

public class LoginInterceptor implements HandlerInterceptor {

    private StringRedisTemplate stringRedisTemplate;

    public LoginInterceptor(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从前端获取token
        String token = request.getHeader("authorization");
        //token获取不到则直接返回
        if (token == null || Objects.equals(token, ""))
            return true;
        //从redis中获取登录用户信息
        Map<Object, Object> map = stringRedisTemplate.opsForHash().entries("token:" + token);
        UserInfo userInfo = BeanUtil.fillBeanWithMap(map, new UserInfo(), false);
        //保存到ThreadLocal中
        LoginUser.saveUser(userInfo);
        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //移除用户信息
        LoginUser.removeUser();
    }
}
