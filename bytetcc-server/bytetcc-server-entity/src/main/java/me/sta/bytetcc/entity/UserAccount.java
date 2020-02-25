package me.sta.bytetcc.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * UserAccount
 *
 * @author xudong
 * @date 2019/7/10
 */
public class UserAccount {

    private Integer id;

    private BigDecimal value;

    private BigDecimal availValue;

    private BigDecimal frozenValue;

    private String status;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getAvailValue() {
        return availValue;
    }

    public void setAvailValue(BigDecimal availValue) {
        this.availValue = availValue;
    }

    public BigDecimal getFrozenValue() {
        return frozenValue;
    }

    public void setFrozenValue(BigDecimal frozenValue) {
        this.frozenValue = frozenValue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
