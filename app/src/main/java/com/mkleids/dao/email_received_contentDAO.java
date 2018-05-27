package com.mkleids.dao;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.mkleids.bean.email_received_content;
public class email_received_contentDAO {
//    插入数据
    private String filename = android.os.Environment.getExternalStorageDirectory() + "/sdcard/mkleids.db";
    public void add(email_received_content em){
        SQLiteDatabase dababase = SQLiteDatabase.openDatabase(filename,null,SQLiteDatabase.OPEN_READWRITE);
        ContentValues contentvalue = new ContentValues();
        contentvalue.put("MessageID",em.getMessageID());
        contentvalue.put("text",em.getText());
        dababase.insert("email_received_content",null,contentvalue);
    }
}
