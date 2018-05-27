package com.mkleids.bean;

public class order {
    private int o_id;
    private String o_create_date;
    private String o_num;
    private String wait_ensure;
    private int is_real_shippiing;

    public int getO_id() {
        return o_id;
    }

    public void setO_id(int o_id) {
        this.o_id = o_id;
    }

    public String getO_create_date() {
        return o_create_date;
    }

    public void setO_create_date(String o_create_date) {
        this.o_create_date = o_create_date;
    }

    public String getO_num() {
        return o_num;
    }

    public void setO_num(String o_num) {
        this.o_num = o_num;
    }

    public String getWait_ensure() {
        return wait_ensure;
    }

    public void setWait_ensure(String wait_ensure) {
        this.wait_ensure = wait_ensure;
    }

    public int getIs_real_shippiing() {
        return is_real_shippiing;
    }

    public void setIs_real_shippiing(int is_real_shippiing) {
        this.is_real_shippiing = is_real_shippiing;
    }
}
