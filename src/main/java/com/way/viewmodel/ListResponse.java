package com.way.viewmodel;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;

import java.io.Serializable;
import java.util.List;

@JsonAutoDetect(value = JsonMethod.ALL)
public class ListResponse<T> implements Serializable {
    private boolean success = false;
    private List<T> data;
    private long totalCount;

    public ListResponse(boolean success, List<T> data, long totalCount) {
        this.success = success;
        this.data = data;
        this.totalCount = totalCount;
    }

    public boolean isSuccess() {
        return success;
    }

    public List<T> getData() {
        return data;
    }

    public long getTotalCount() {
        return totalCount;
    }
}