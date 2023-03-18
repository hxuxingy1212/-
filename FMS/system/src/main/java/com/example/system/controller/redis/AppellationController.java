package com.example.system.controller.redis;

import com.example.system.base.Result.ResultBody;
import com.example.system.service.redis.AppellationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 称谓控制器
 *
 * @author 徐鑫
 * @date 2023/02/26
 */
@RestController
@RequestMapping("/appellations")
public class AppellationController {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private AppellationService service;

    @ApiOperation("获取所有称谓关系")
    @GetMapping("getAll")
    private ResultBody getAll() {
        return new ResultBody(true,service.getAll());
    }

    @ApiOperation("添加称谓关系")
    @GetMapping("add/{appellation}")
    private ResultBody add(@PathVariable String appellation) {
        Long size = service.add(appellation);
        return size == null || size == 0 ? ResultBody.error("添加称谓失败" + size) : ResultBody.ok("成功");
    }

    @ApiOperation("删除称谓关系")
    @GetMapping("delete/{appellation}")
    private ResultBody delete(@PathVariable String appellation) {
        Long size = service.delete(appellation);
        return size == null || size == 0 ? ResultBody.error("删除称谓失败" + size) : ResultBody.ok("成功");
    }



}
