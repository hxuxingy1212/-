package com.example.system.service.redis;

import java.util.Set;

/**
 * 收入类型服务
 *
 * @author 徐鑫
 * @date 2023/02/21
 */
public interface RevenueTypeService {

    Long add(String type);

    Long delete(String type);

    Set<String> getAll();
}
