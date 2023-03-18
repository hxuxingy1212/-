package com.example.system.service.redis;

import java.util.Set;

public interface ConsumptionTypeService {
    Long add(String type);

    Long delete(String type);

    Set<String> getAll();
}
