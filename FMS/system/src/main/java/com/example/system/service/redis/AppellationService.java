package com.example.system.service.redis;

import org.springframework.stereotype.Service;

import java.util.Set;


/**
 * 称谓服务
 *
 * @author 徐鑫
 * @date 2023/02/21
 */
public interface AppellationService {

    Set<String> getAll();

    Long add(String appellation);
    Long add(String appellation,String account);

    Long delete(String appellation);
}
