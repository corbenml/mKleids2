package com.mkleids.json;
import com.mkleids.bean.order;

public class order_json {
    private String status;
    private int length;
    public order[] data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public order[] getData() {
        return data;
    }

    public void setData(order[] data) {
        this.data = data;
    }
}
