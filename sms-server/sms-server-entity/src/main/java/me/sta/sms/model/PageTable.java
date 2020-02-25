package me.sta.sms.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @CreateBy admin
 * @CreateTime 2019/6/18
 * @Description
 */
public class PageTable<T> {
    private long total = 0l;
    private List<T> tList = new ArrayList<T>();


    public PageTable(List<T> tList, long total) {
        this.total = total;
        this.tList = tList;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> gettList() {
        return tList;
    }

    public void settList(List<T> tList) {
        this.tList = tList;
    }

    public long getTotal() {
        return total;
    }

    public List<T> getList() {
        return tList;
    }
}
