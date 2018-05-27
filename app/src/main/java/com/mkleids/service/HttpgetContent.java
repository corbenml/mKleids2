package com.mkleids.service;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.mkleids.json.email_receive_head_json;
import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import com.mkleids.bean.email_received_head;
import com.mkleids.dao.email_received_headDao;
import com.mkleids.bean.order;
import com.mkleids.dao.orderDao;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.LocalDate;

public class HttpgetContent {
    //public static final String strurl = "http://192.168.1.102:8080/WebServiceProject/android/email_receive_content";
    public static final String strurl = "http://101.132.99.78:8080/WebServiceProject/android/email_receive_content";
    public static final String updateurl = "http://101.132.99.78:8080/WebServiceProject/android/email_update";
    public static final String strOrderurl = "http://101.132.99.78:8080/WebServiceProject/android/s_orderservlet";
    public static final String strupdate_email_studus = "http://101.132.99.78:8080/WebServiceProject/android/update_email_studus";
   // HttpResponse httprespons = null;
    public void getEmail_receive_content(){
        try {
            URL url = new URL(strurl);
            HttpURLConnection httpurlconection = (HttpURLConnection)url.openConnection();
            httpurlconection.setRequestMethod("GET");
            httpurlconection.setReadTimeout(5000);
            httpurlconection.setRequestProperty("Content-Type", "text/html; charset=GB2312");
            httpurlconection.setDoInput(true);
            httpurlconection.setDoOutput(true);
            InputStream in = null;
            if(httpurlconection.getResponseCode()==200){
                in = httpurlconection.getInputStream();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] arr = new byte[1024];
                int len=0;
                while((len=in.read(arr))!=-1){
                    bos.write(arr,0,len);
                }
                byte[] b = bos.toByteArray();
                String ss = new String(b,"utf-8");
                Log.d("kodulf", ss);
            }
            //关闭流
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // 利用post 方法获取邮件头部信息
    public static String doGetEmailReceiveHeader() {
        // HttpClient 6.0被抛弃了
        String result = "";
        BufferedReader reader = null;
        try {
            URL url = new URL(strurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(10 * 1000);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //conn.setUseCaches(false);
            //conn.setRequestProperty("Connection", "Keep-Alive");
           // conn.setRequestProperty("Charset", "UTF-8");
            // 设置文件类型:
           // conn.setRequestProperty("Content-Type","application/json; charset=UTF-8");
           // conn.setRequestProperty("Content-Type","text/html; charset=UTF-8");
            // 设置接收类型否则返回415错误
            //conn.setRequestProperty("accept","*/*")此处为暴力方法设置接受所有类型，以此来防范返回415;
            //conn.setRequestProperty("accept","application/json");
           String kk="id="+ android.os.Build.SERIAL; //Serial Numbe
            conn.setRequestProperty("Content-Length", String.valueOf(kk.length()));
            conn.setRequestProperty("Cache-Control", "max-age=0");
            conn.getOutputStream().write(kk.getBytes());
            int code=conn.getResponseCode();
            InputStream in = null;
            if (code==200) {
                in = conn.getInputStream();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] arr = new byte[1024];
                int len=0;
                while((len=in.read(arr))!=-1){
                    bos.write(arr,0,len);
                }
                byte[] b = bos.toByteArray();
                String ss = new String(b,"utf-8");
                JSONObject rot = new JSONObject(ss);
                String status = rot.getString("status");
                int total = rot.getInt("length");
                //读取多个数据
                JSONArray array = rot.getJSONArray("data");
                Log.d("kodulf", ss);
                Log.d("JSON status",status);
                Log.d("JSON length",total+"");
                if("success".equals(status)) {
                    email_received_head emh = new email_received_head();
                    int maxid = 0;
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject lan = array.getJSONObject(i);
                        emh.setSender(lan.getString("sender"));
                        emh.setRecipientUserID(lan.getString("recipientUserID"));
                        emh.setSubject(lan.getString("subject"));
                        emh.setMessageID(lan.getString("messageID"));
                        emh.setExternalMessageID(lan.getString("externalMessageID"));
                        emh.setReceiveDate(lan.getString("receiveDate"));
                        emh.setReaded(lan.getString("readed"));
                        emh.setFlagged(lan.getInt("flagged"));
                        emh.setItemID(lan.getString("itemID"));
                        emh.setReply(lan.getInt("reply"));
                        emh.setShop_cr(lan.getString("shop_cr"));
                        emh.setMessageType(lan.getString("messageType"));
                        new email_received_headDao().insert_new(emh);
                        Log.d("id= ", lan.getInt("id") + "");
                        if (maxid < lan.getInt("id")) maxid = lan.getInt("id");
                        Log.d("subject", lan.getString("subject"));
                    }
                    if (maxid > 0)
                        doGetUpdate("email_receive_head_id", maxid);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
//    利用post 获取订单内容
public static String doGetOrder() {
    String result = "";
    BufferedReader reader = null;
    try {
        URL url = new URL(strOrderurl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setConnectTimeout(10 * 1000);
        // 发送POST请求必须设置如下两行
        conn.setDoOutput(true);
        conn.setDoInput(true);
        //conn.setUseCaches(false);
        //conn.setRequestProperty("Connection", "Keep-Alive");
        // conn.setRequestProperty("Charset", "UTF-8");
        // 设置文件类型:
        // conn.setRequestProperty("Content-Type","application/json; charset=UTF-8");
        // conn.setRequestProperty("Content-Type","text/html; charset=UTF-8");
        // 设置接收类型否则返回415错误
        //conn.setRequestProperty("accept","*/*")此处为暴力方法设置接受所有类型，以此来防范返回415;
        //conn.setRequestProperty("accept","application/json");
        String kk="id="+ android.os.Build.SERIAL; //Serial Numbe
        conn.setRequestProperty("Content-Length", String.valueOf(kk.length()));
        conn.setRequestProperty("Cache-Control", "max-age=0");
        conn.getOutputStream().write(kk.getBytes());
        int code=conn.getResponseCode();
        InputStream in = null;
        if (code==200) {
            in = conn.getInputStream();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] arr = new byte[1024];
            int len=0;
            while((len=in.read(arr))!=-1){
                bos.write(arr,0,len);
            }
            byte[] b = bos.toByteArray();
            String ss = new String(b,"utf-8");
            JSONObject rot = new JSONObject(ss);
            String status = rot.getString("status");
            int total = rot.getInt("length");
            //读取多个数据
            JSONArray array = rot.getJSONArray("data");
            Log.d("Get Order Return", ss);
            Log.d("Sorder JSON status",status);
            Log.d("JSON length",total+"");
            if("success".equals(status)) {
                order emh = new order();
                int maxid = 0;
                for (int i = 0; i < array.length(); i++) {
                    JSONObject lan = array.getJSONObject(i);
                    emh.setO_id(lan.getInt("o_id"));
                    emh.setIs_real_shippiing(lan.getInt("is_real_shippiing"));
                    emh.setO_create_date(lan.getString("o_create_date"));
                    emh.setO_num(lan.getString("o_num"));
                    emh.setWait_ensure(lan.getString("wait_ensure"));
                    new orderDao().insert_new(emh);
                    Log.d("o_id= ", lan.getInt("o_id") + "");
                    if (maxid < lan.getInt("o_id")) maxid = lan.getInt("o_id");
                    Log.d("o_num", lan.getString("o_num"));
                }
                if (maxid > 0)
                    doGetUpdate("s_order_id", maxid);
            }

        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    return result;
}
// 更新邮件状态
public static String doPostUpdateEmailStudus() {
    // HttpClient 6.0被抛弃了
    String result = "";
    BufferedReader reader = null;
    try {
        URL url = new URL(strupdate_email_studus);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setConnectTimeout(10 * 1000);
        // 发送POST请求必须设置如下两行
        conn.setDoOutput(true);
        conn.setDoInput(true);
        //conn.setUseCaches(false);
        //conn.setRequestProperty("Connection", "Keep-Alive");
        // conn.setRequestProperty("Charset", "UTF-8");
        // 设置文件类型:
        // conn.setRequestProperty("Content-Type","application/json; charset=UTF-8");
        // conn.setRequestProperty("Content-Type","text/html; charset=UTF-8");
        // 设置接收类型否则返回415错误
        //conn.setRequestProperty("accept","*/*")此处为暴力方法设置接受所有类型，以此来防范返回415;
        //conn.setRequestProperty("accept","application/json");
        String messageIDs = new email_received_headDao().getUnreplyID();
        if (messageIDs != "") {
            String kk = "id=" + android.os.Build.SERIAL + "&&messageIDs=" + messageIDs; //Serial Numbe
            Log.d("DO POST UPDATE EMAIL", kk);
            conn.setRequestProperty("Content-Length", String.valueOf(kk.length()));
            conn.setRequestProperty("Cache-Control", "max-age=0");
            conn.getOutputStream().write(kk.getBytes());
            int code = conn.getResponseCode();
            InputStream in = null;
            if (code == 200) {
                in = conn.getInputStream();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] arr = new byte[1024];
                int len = 0;
                while ((len = in.read(arr)) != -1) {
                    bos.write(arr, 0, len);
                }
                byte[] b = bos.toByteArray();
                String ss = new String(b, "utf-8");
                JSONObject rot = new JSONObject(ss);
                String status = rot.getString("status");
                int total = rot.getInt("length");
                //读取多个数据
                JSONArray array = rot.getJSONArray("data");
                Log.d("update_email_studus", ss);
                Log.d("JSON status", status);
                Log.d("JSON length", total + "");
                if ("success".equals(status)) {
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject lan = array.getJSONObject(i);
                        new email_received_headDao().updateEmailState(lan.getString("messageID"), lan.getString("readed"),
                                lan.getInt("reply"));
                        Log.d("id= ", lan.getString("messageID"));
                        Log.d("reply=", lan.getInt("reply") + "");
                    }
                }

            }
        }
        } catch(Exception e){
            e.printStackTrace();
        } finally{
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }
    return result;
}
    // 更新在线数据库
    public static boolean doGetUpdate(String clu,int process) {
        String result = "";
        BufferedReader reader = null;
        try {
            URL url = new URL(updateurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(10 * 1000);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            String kk="id="+ android.os.Build.SERIAL+"&&clu="+clu+"&&process="+process; //Serial Numbe
            conn.setRequestProperty("Content-Length", String.valueOf(kk.length()));
            conn.setRequestProperty("Cache-Control", "max-age=0");
            conn.getOutputStream().write(kk.getBytes());
            int code=conn.getResponseCode();
            if (code==200) {
                Log.d("Update Online DB",""+clu+process);
            }
        } catch (Exception e) {
            Log.d("Update Online DB Error",""+clu+process);
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

}
