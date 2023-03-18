package com.example.system.service.redis.impl;

import com.example.system.base.config.LoginUser;
import com.example.system.service.redis.ConsumptionTypeService;
import com.example.system.service.redis.RevenueTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;


/**
 * 消费类型服务impl
 *
 * @author 徐鑫
 * @date 2023/02/21
 */
@Service
public class ConsumptionTypeServiceImpl implements ConsumptionTypeService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public Long add(String type) {
        return redisTemplate.opsForSet()
                .add("consumptionTypes:" + LoginUser.getUser().getAccount(),type);
    }

    @Override
    public Long delete(String type) {
        return redisTemplate.opsForSet()
                .remove("consumptionTypes:" + LoginUser.getUser().getAccount(),type);
    }

    @Override
    public Set<String> getAll() {
        return redisTemplate.opsForSet()
                .members("consumptionTypes:" + LoginUser.getUser().getAccount());
    }
}
