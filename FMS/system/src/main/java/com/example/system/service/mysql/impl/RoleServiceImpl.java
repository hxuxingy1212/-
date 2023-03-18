package com.example.system.service.mysql.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.system.base.config.LoginUser;
import com.example.system.dao.RoleMapper;
import com.example.system.pojo.Role;
import com.example.system.service.mysql.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
        implements RoleService {

    @Autowired
    private RoleMapper mapper;

    @Override
    public List<Role> getAll() {
        QueryWrapper<Role> qw = new QueryWrapper<>();
        qw.eq("family_account",LoginUser.getUser().getAccount());
        return mapper.selectList(qw);
    }

    @Override
    public Boolean deleteById(Integer id) {
        return mapper.deleteById(id) == 1;
    }

    @Override
    public boolean add(Role role) {
        if (LoginUser.getUser().getAccount() == null && LoginUser.getUser().getAccount().equals(""))
            return false;
        Role insertRole = role.setFamily_account(LoginUser.getUser().getAccount());
        int insert = mapper.insert(role);
        return insert == 1;
    }
}




