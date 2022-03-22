package com.ijys.testapiserver.vo;

public class SimpleData {
    private final long id;
    private final String data;

    public SimpleData(long id, String data) {
        this.id = id;
        this.data = data;
    }

    public long getId() {
        return id;
    }

    public String getData() {
        return data;
    }
}
