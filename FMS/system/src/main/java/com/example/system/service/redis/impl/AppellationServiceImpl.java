package com.example.system.service.redis.impl;

import com.example.system.base.config.LoginUser;
import com.example.system.service.redis.AppellationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * 名称服务impl
 *
 * @author 徐鑫
 * @date 2023/02/21
 */
@Service
public class AppellationServiceImpl implements AppellationService {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public Set<String> getAll() {
        return redisTemplate.opsForSet().members("appellations:"+ LoginUser.getUser().getAccount());
    }

    @Override
    public Long add(String appellation) {
        return redisTemplate.opsForSet().add("appellations:"+ LoginUser.getUser().getAccount(), appellation);
    }

    @Override
    public Long add(String appellation, String account) {
        return redisTemplate.opsForSet().add("appellations:"+ account,appellation);
    }

    @Override
    public Long delete(String appellation) {
       return redisTemplate.opsForSet().remove("appellations:"+ LoginUser.getUser().getAccount(), appellation);
    }
}
