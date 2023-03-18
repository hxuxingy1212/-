package com.example.system.service.redis.impl;

import com.example.system.base.config.LoginUser;
import com.example.system.service.redis.BindingCardTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * 绑定卡类型服务
 *
 * @author 徐鑫
 * @date 2023/02/21
 */
@Service
public class BindingCardTypeServiceImpl implements BindingCardTypeService {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public Long add(String type) {
        return redisTemplate.opsForSet()
                .add("bindingCardTypes:" + LoginUser.getUser().getAccount(),type);
    }

    @Override
    public Long delete(String type) {
        return redisTemplate.opsForSet()
                .remove("bindingCardTypes:" + LoginUser.getUser().getAccount(),type);
    }

    @Override
    public Set<String> getAll() {
        return redisTemplate.opsForSet()
                .members("bindingCardTypes:" + LoginUser.getUser().getAccount());
    }

}
