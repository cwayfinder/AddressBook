package com.way.viewmodel;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;

import java.io.Serializable;

@JsonAutoDetect(value = JsonMethod.ALL)
public class SingleResponse<T> implements Serializable {
    private boolean success = false;
    private T data;

    public SingleResponse(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public T getData() {
        return data;
    }
}