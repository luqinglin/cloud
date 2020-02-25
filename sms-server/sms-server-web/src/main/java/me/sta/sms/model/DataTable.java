package me.sta.sms.model;


import java.io.Serializable;
import java.util.List;

/**
 * layui 数据表格
 *
 * @author Rlax
 */
public class DataTable<T> {

    private int code = 0;
    private String msg;
    private long count;

    private List<T> data;

    public DataTable() {
    }

    public DataTable(PageTable<T> page) {
        this.count = page.getTotal();
        this.data = page.getList();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
