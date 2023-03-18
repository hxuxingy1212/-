package com.example.system.controller.redis;

import com.example.system.base.Result.ResultBody;
import com.example.system.service.redis.RevenueTypeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 收入类型控制器
 *
 * @author 徐鑫
 * @date 2023/02/21
 */
@RestController
@RequestMapping("/revenueTypes")
public class RevenueTypeController {
    @Autowired
    private RevenueTypeService service;

    @ApiOperation("获取所有收入类型")
    @GetMapping("getAll")
    private ResultBody getAll() {
        return new ResultBody(true,service.getAll());
    }

    @ApiOperation("添加收入类型")
    @GetMapping("add/{type}")
    private ResultBody add(@PathVariable String type) {
        Long size = service.add(type);
        return size == null || size == 0 ? ResultBody.error("添加收入类型失败" + size) : ResultBody.ok("成功");
    }

    @ApiOperation("删除收入类型")
    @GetMapping("delete/{type}")
    private ResultBody delete(@PathVariable String type) {
        Long size = service.delete(type);
        return size == null || size == 0 ? ResultBody.error("删除收入类型失败" + size) : ResultBody.ok("成功");
    }
}
