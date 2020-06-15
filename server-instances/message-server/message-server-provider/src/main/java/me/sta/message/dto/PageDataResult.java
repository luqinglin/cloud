package me.sta.message.dto;

import me.sta.message.entity.BaseEntity;

import java.util.List;

/**
 * @Title: PageDataResult
 * @Description: 封装DTO分页数据（记录数和所有记录）
 * @author: youqing
 * @version: 1.0
 * @date: 2018/11/21 11:15
 */
public class PageDataResult<R extends BaseEntity> {

    private Integer code = 200;

    //总记录数量
    private Integer totals;

    private List<?> list;

    private Integer temp;


    public Integer getTemp() {
        return temp;
    }

    public void setTemp(Integer temp) {
        this.temp = temp;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getTotals() {
        return totals;
    }

    public void setTotals(Integer totals) {
        this.totals = totals;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageDataResult{" +
                "code=" + code +
                ", totals=" + totals +
                ", list=" + list +
                '}';
    }
}
