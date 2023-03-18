package com.example.system.pojo;


import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Node {
    //人物姓名
    private String name;
    //性别
    private String sex;
    //年龄
    private String age;
    //电话
    private String telephone;
    //费用类型,赠与,收取,借出,借入
    private String amountType;
    //人情费用
    private String amount;
    //人物类型,redis中查询得到,亲戚,朋友,家人,其他
    private String type;
    //备注
    private String remake;

}
