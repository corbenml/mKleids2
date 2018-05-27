package com.mkleids.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.mkleids.bean.email_received_head;

public class email_received_headDao {
//    增加新条目
static private String filename = android.os.Environment.getExternalStorageDirectory() + "/mkleids.db";
public void insert_new(email_received_head my) throws Exception {
    SQLiteDatabase dababase = SQLiteDatabase.openDatabase(filename,null,SQLiteDatabase.OPEN_READWRITE);
    ContentValues contentvalue = new ContentValues();
    contentvalue.put("Sender",my.getSender());
    contentvalue.put("recipientUserID",my.getReceiveDate());
    contentvalue.put("subject",my.getSubject());
    contentvalue.put("messageID",my.getMessageID());
    contentvalue.put("ExternalMessageID",my.getExternalMessageID());
    contentvalue.put("ReceiveDate",my.getReceiveDate());
   contentvalue.put("Readed",my.getReaded());
    contentvalue.put("Flagged",my.getFlagged());
    contentvalue.put("ItemID",my.getItemID());
   contentvalue.put("reply",my.getReply());
    contentvalue.put("shop_cr",my.getShop_cr());
    contentvalue.put("MessageType",my.getMessageType());

    dababase.insert("email_receive_header",null,contentvalue);
    dababase.close();
}
/*
* 对回复邮件进行更新
* */
public String getUnreplyID(){
    SQLiteDatabase dabase = SQLiteDatabase.openDatabase(filename,null,SQLiteDatabase.OPEN_READONLY);
    Cursor cursor = dabase.rawQuery("select messageID from email_receive_header where reply=0 and Sender!=? ",new String[]{"eBay"});
    cursor.moveToFirst();
    String str="";
    if(cursor.getCount()>0)
    str=cursor.getString(cursor.getColumnIndexOrThrow("messageID"));
    while(cursor.moveToNext()){
        str+=","+cursor.getString(cursor.getColumnIndexOrThrow("messageID"));
    }
    dabase.close();
    return str;

}
/*
* 查找数据
* */
public Cursor getHeader(String sql,String[]args){
    SQLiteDatabase dabase = SQLiteDatabase.openDatabase(filename,null,SQLiteDatabase.OPEN_READONLY);
    Cursor cursor = dabase.rawQuery(sql,args);
    return cursor;
}
    /*
     * 更新数据库
     * */
    public void updateEmailState(String messageid,String isread,int isreply){
        SQLiteDatabase dabase = SQLiteDatabase.openDatabase(filename,null,SQLiteDatabase.OPEN_READWRITE);
        ContentValues values = new ContentValues();
        values.put("Readed", isread);//key为字段名，value为值
        values.put("reply",isreply);
        dabase.update("email_receive_header", values, "messageID=?", new String[]{messageid});
        dabase.close();
    }
/*
* 获取邮件数量
* */
public static int getTotal(String shopcr){
    SQLiteDatabase dabase = SQLiteDatabase.openDatabase(filename,null,SQLiteDatabase.OPEN_READONLY);
    Cursor cursor = dabase.rawQuery("select count(*) as total from email_receive_header where reply=0 and Sender!=?" +
            " and  shop_cr=?",new String[]{"eBay",shopcr});
    cursor.moveToFirst();
    int total=cursor.getInt(cursor.getColumnIndexOrThrow("total"));
    dabase.close();
    return total;
}
    /*
     * 获取所有未读邮件数量
     * */
    public static int getTotalUnread(String stuta,String shopcr){
        SQLiteDatabase dabase = SQLiteDatabase.openDatabase(filename,null,SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = dabase.rawQuery("select count(*) as total from email_receive_header where " +
                "shop_cr=? and Readed=? ",new String[]{shopcr,stuta});
        cursor.moveToFirst();
        int total=cursor.getInt(cursor.getColumnIndexOrThrow("total"));
        dabase.close();
        return total;
    }
    /*
     * 获取所有未读邮件数量
     * */
    public static int getTotalEbayUnread(String shopcr){
        SQLiteDatabase dabase = SQLiteDatabase.openDatabase(filename,null,SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = dabase.rawQuery("select count(*) as total from email_receive_header where " +
                "shop_cr=? and Sender=? and Readed=? ",new String[]{shopcr,"eBay","No"});
        cursor.moveToFirst();
        int total=cursor.getInt(cursor.getColumnIndexOrThrow("total"));
        dabase.close();
        return total;
    }
}
