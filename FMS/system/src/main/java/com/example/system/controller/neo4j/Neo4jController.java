package com.example.system.controller.neo4j;


import com.example.system.service.neo4j.Neo4jService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * neo4j控制器
 *
 * @author 徐鑫
 * @date 2023/02/26
 */
@Api(tags = "Neo4j服务接口")
@RestController
@RequestMapping("neo4j")
public class Neo4jController {

    @Autowired
    private Neo4jService service;


    @ApiOperation("添加节点")
    @GetMapping("addNode/{node}")
    private Object addNode(@PathVariable String node){
//        return service.addNode(node);
        return null;
    }

    @ApiOperation("删除节点")
    @GetMapping("queryEvent/{event}")
    private Object queryEvent(@PathVariable String event){
//        return service.queryEvent(event);
        return null;
    }

}
