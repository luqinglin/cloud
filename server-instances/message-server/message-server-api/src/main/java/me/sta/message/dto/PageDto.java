package me.sta.message.dto;

import java.util.Map;

public class PageDto {

    PageParam pageParam;
    Map<String, Object> paramMap;

    public PageDto(PageParam pageParam, Map<String, Object> paramMap) {
        this.pageParam = pageParam;
        this.paramMap = paramMap;
    }

    public PageParam getPageParam() {
        return pageParam;
    }

    public void setPageParam(PageParam pageParam) {
        this.pageParam = pageParam;
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }
}
