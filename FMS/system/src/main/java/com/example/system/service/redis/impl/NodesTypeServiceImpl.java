package com.example.system.service.redis.impl;

import com.example.system.base.config.LoginUser;
import com.example.system.service.redis.NodesTypeService;
import com.example.system.service.redis.RevenueTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * 收入类型服务impl
 *
 * @author 徐鑫
 * @date 2023/02/21
 */
@Service
public class NodesTypeServiceImpl implements NodesTypeService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public Long add(String type) {
        return redisTemplate.opsForSet()
                .add("nodesTypes:" + LoginUser.getUser().getAccount(),type);
    }

    @Override
    public Long delete(String type) {
        return redisTemplate.opsForSet()
                .remove("nodesTypes:" + LoginUser.getUser().getAccount(),type);
    }

    @Override
    public Set<String> getAll() {
        return redisTemplate.opsForSet()
                .members("nodesTypes:" + LoginUser.getUser().getAccount());
    }
}
