package com.wisewin.backend.entity.bo;

import com.wisewin.backend.common.base.BaseModel;

import java.math.BigDecimal;

public class MoneyBO extends BaseModel{
    private Integer id;
    private BigDecimal  money;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
