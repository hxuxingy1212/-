package com.example.system.service.mysql.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.system.pojo.FamilyAccounts;
import com.example.system.service.mysql.FamilyAccountsService;
import com.example.system.dao.FamilyAccountsMapper;
import org.springframework.stereotype.Service;

@Service
public class FamilyAccountsServiceImpl extends ServiceImpl<FamilyAccountsMapper, FamilyAccounts>
    implements FamilyAccountsService {

}




