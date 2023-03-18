package com.example.system.service.redis;


import java.util.Set;

/**
 * 绑定信用卡类型服务
 *
 * @author 徐鑫
 * @date 2023/02/21
 */
public interface BindingCardTypeService {
    Long add(String type);

    Long delete(String type);

    Set<String> getAll();
}
