package com.goat.zxt.common.utils;

import java.io.Serializable;

public class QueryRequest implements Serializable {

	private static final long serialVersionUID = -4869594085374385813L;

    private Integer page;
    private Integer limit;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
