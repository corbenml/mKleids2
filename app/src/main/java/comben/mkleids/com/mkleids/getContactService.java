package comben.mkleids.com.mkleids;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.mkleids.service.HttpgetContent;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

public class getContactService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    private int itest=0;
    private HttpgetContent hg = null;
    @Override
    public void onCreate() {
        Log.d("MyService","Service onCreate");

        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        copyDB();
        Log.d("MyService","Service onStarted");
        Timer time = new Timer();

        hg = new HttpgetContent();
        time.schedule(new TimerTask() {
            @Override
            public void run() {
                printtest();
            }
        },1000,60*1000);
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        Log.d("MyService","Service onDestroy");
        super.onDestroy();
    }
//    test print
    public void printtest(){
        itest++;
       Log.d("Service priingt","test"+itest);
        //hg.getEmail_receive_content();
//        下面是测试通多代码部分，已经获取数据并出入成功
      hg.doGetEmailReceiveHeader();
//       获取订单
       hg.doGetOrder();
//       更新回信状态
        hg.doPostUpdateEmailStudus();
    }
    //    初始化数据库，如果数据库存在则不做任何动作，如果数据库文件不存在指定位置，进行copy
    public void copyDB() {
        String filename = android.os.Environment.getExternalStorageDirectory() + "/mkleids.db";
        File file = new File(filename);
        if (!file.exists())
//        copy DB
            try {
                InputStream is = getResources().openRawResource(R.raw.mkleids);
                FileOutputStream os = new FileOutputStream("/sdcard/mkleids.db");
                byte[] buffer = new byte[8192];
                int count = 0;
                while ((count = is.read(buffer)) >= 0) {
                    os.write(buffer, 0, count);
                }
                Log.d("Copy DB", "copy complete\ncopy complete!"+filename);
                if(file.exists())
                    Log.d("COPY DB","DB file is aliver");
                else
                    Log.d("copy db","db file not aliver");
                os.close();
                is.close();
            } catch (Exception e) {
                Log.d("Copy DB Error", e.getMessage());
            }
        else
            Log.d("CP DB","db dervice dont need copy");
    }
}
