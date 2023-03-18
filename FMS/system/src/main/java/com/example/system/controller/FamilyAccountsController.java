package com.example.system.controller;

import com.example.system.base.Result.ResultBody;
import com.example.system.pojo.FamilyAccounts;
import com.example.system.service.FamilyAccountsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/familyAccounts")
public class FamilyAccountsController {

    @Autowired
    private FamilyAccountsService service;

    @GetMapping("{id}")
    public ResultBody getById(@PathVariable Integer id) {
        return new ResultBody(true, service.getById(id));
    }

    @PostMapping
    public ResultBody save(@RequestBody FamilyAccounts familyAccounts) {
        boolean flag = service.save(familyAccounts);
        return new ResultBody(flag);
    }

    @PutMapping
    public ResultBody update(@RequestBody FamilyAccounts familyAccounts) {
        boolean flag = service.updateById(familyAccounts);
        return new ResultBody(flag);
    }

    @DeleteMapping("{id}")
    public ResultBody delete(@PathVariable Integer id) {
        return new ResultBody(service.removeById(id));
    }
}
