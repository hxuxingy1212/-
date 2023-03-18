package com.example.system.service.redis;

import java.util.Set;

/**
 * 节点类型服务
 *
 * @author 徐鑫
 * @date 2023/02/26
 */
public interface NodesTypeService {
    Long add(String type);

    Long delete(String type);

    Set<String> getAll();
}
