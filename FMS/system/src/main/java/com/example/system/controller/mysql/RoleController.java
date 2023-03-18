package com.example.system.controller.mysql;

import com.example.system.base.Result.ResultBody;
import com.example.system.pojo.Role;
import com.example.system.service.mysql.RoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService service;

    @ApiOperation("获取全部role")
    @GetMapping("getAll")
    public ResultBody getAll() {
        return new ResultBody(service.getAll());
    }

    @ApiOperation("保存成功")
    @PostMapping("save")
    public ResultBody add(@RequestBody Role role) {
        return service.add(role) ? ResultBody.ok("保存成功") : ResultBody.error("保存失败");
    }

    @PostMapping("update")
    public ResultBody update(@RequestBody Role role) {
        return service.updateById(role) ? ResultBody.ok("修改成功") : ResultBody.error("修改失败");
    }

    @GetMapping("delete/{id}")
    public ResultBody delete(@PathVariable Integer id) {
        service.deleteById(id);
        return service.removeById(id) ? ResultBody.error("删除失败") : ResultBody.ok("删除成功");
    }

}
