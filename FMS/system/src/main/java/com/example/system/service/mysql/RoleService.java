package com.example.system.service.mysql;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.system.pojo.Role;

import java.util.List;

public interface RoleService extends IService<Role> {

    List<Role> getAll();

    Boolean deleteById(Integer id);

    boolean add(Role role);
}
