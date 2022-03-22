package com.ijys.testapiclient.vo;

public class SimpleData {
    private long id;
    private String data;

    public SimpleData() {
    }

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

    @Override
    public String toString() {
        return "SimpleData{" +
                "id=" + id +
                ", data='" + data + '\'' +
                '}';
    }
}

