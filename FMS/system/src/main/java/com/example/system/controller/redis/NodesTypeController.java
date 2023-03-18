package com.example.system.controller.redis;

import com.example.system.base.Result.ResultBody;
import com.example.system.service.redis.NodesTypeService;
import com.example.system.service.redis.RevenueTypeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 节点类型控制器
 *
 * @author 徐鑫
 * @date 2023/02/21
 */
@RestController
@RequestMapping("/nodesTypes")
public class NodesTypeController {
    @Autowired
    private NodesTypeService service;

    @ApiOperation("获取所有节点类型")
    @GetMapping("getAll")
    private ResultBody getAll() {
        return new ResultBody(true,service.getAll());
    }

    @ApiOperation("添加节点类型")
    @GetMapping("add/{type}")
    private ResultBody add(@PathVariable String type) {
        Long size = service.add(type);
        return size == null || size == 0 ? ResultBody.error("添加节点类型失败" + size) : ResultBody.ok("成功");
    }

    @ApiOperation("删除节点类型")
    @GetMapping("delete/{type}")
    private ResultBody delete(@PathVariable String type) {
        Long size = service.delete(type);
        return size == null || size == 0 ? ResultBody.error("删除节点类型失败" + size) : ResultBody.ok("成功");
    }
}
