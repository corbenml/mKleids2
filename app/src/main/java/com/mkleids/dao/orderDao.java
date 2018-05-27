package com.mkleids.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mkleids.bean.order;

public class orderDao {
//    增加新条目
private String filename = android.os.Environment.getExternalStorageDirectory() + "/mkleids.db";
public void insert_new(order my) throws Exception {
    SQLiteDatabase dababase = SQLiteDatabase.openDatabase(filename,null,SQLiteDatabase.OPEN_READWRITE);
    ContentValues contentvalue = new ContentValues();
    contentvalue.put("o_id",my.getO_id());
    contentvalue.put("o_create_date",my.getO_create_date());
    contentvalue.put("o_num",my.getO_num());
    contentvalue.put("is_real_shippiing",my.getIs_real_shippiing());
    contentvalue.put("wait_ensure",my.getWait_ensure());

    dababase.insert("s_order",null,contentvalue);
}
/*
* 查找数据
* */
public Cursor getOrder(String sql,String[]args){
    SQLiteDatabase dabase = SQLiteDatabase.openDatabase(filename,null,SQLiteDatabase.OPEN_READONLY);
    Cursor cursor = dabase.rawQuery(sql,args);
    return cursor;
}
}
