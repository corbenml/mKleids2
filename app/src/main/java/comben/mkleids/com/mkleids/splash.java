package comben.mkleids.com.mkleids;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class splash extends AppCompatActivity {
private Intent serviceIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        启动后台服务并查看是否已运行，否则启动
        serviceIntent = new Intent(splash.this, getContactService.class);
        //if(!isServiceExisted(this,"comben.mkleids.com.mkleids.getContactService"))
        startService(serviceIntent);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splash.this,MainActivity.class);
                intent.putExtra("sender","init");

                splash.this.startActivity(intent);
                splash.this.finish();
            }
        },5000);
    }

}
