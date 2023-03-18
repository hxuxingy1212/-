package com.example.system.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Transient;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
@TableName(value = "role", schema = "FMS")
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private Long id;
    private String name;
    private String sex;
    private String appellation;//称谓
    private BigDecimal income;
    private BigDecimal outcome;
    private String telephone;
    private String vx;
    private String family_account;
    @TableField(exist = false)
    private Boolean disabled = true;
}
