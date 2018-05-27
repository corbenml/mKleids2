package com.mkleids.json;
import com.mkleids.bean.email_received_head;
public class email_receive_head_json {
    private String status;
    private int length;
    public email_received_head[] data;

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

    public email_received_head[] getData() {
        return data;
    }

    public void setData(email_received_head[] data) {
        this.data = data;
    }
}
