package com.example.gateway;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class SpringDataRedisApplicationTests {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void Test(){
        User user = new User("xuxin","123");
        String s = JSON.toJSONString(user);
        stringRedisTemplate.opsForValue().set("user",s);
        String namexx = stringRedisTemplate.opsForValue().get("user");
        User user1 = JSON.parseObject(namexx, User.class);
        System.out.println(user1);
    }
}
