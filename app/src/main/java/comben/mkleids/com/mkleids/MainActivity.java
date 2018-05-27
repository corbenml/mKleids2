package comben.mkleids.com.mkleids;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;

import java.util.ArrayList;
import java.util.List;
import com.mkleids.dao.email_received_headDao;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static MainActivity instance;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MyAdapter adapter;
    private List<Fragment> list;
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = (TabLayout) findViewById(R.id.email_head_tablayout);
        viewPager = (ViewPager) findViewById(R.id.email_head_viewpage);
        list = new ArrayList<>();
        String shopcr = this.getIntent().getExtras().getString("sender","Y");
        if(shopcr.equals("init")) shopcr="Y";
        email_left_frament em_unreply = new email_left_frament();
        em_unreply.setStudus(shopcr,"unreply",email_received_headDao.getTotal(shopcr));
        email_left_frament em_ebay = new email_left_frament();
        em_ebay.setStudus(shopcr,"ebay",email_received_headDao.getTotalEbayUnread(shopcr));
        email_left_frament em_all = new email_left_frament();
        em_all.setStudus(shopcr,"all",email_received_headDao.getTotalUnread("No",shopcr));
        list.add(em_unreply);
        list.add(em_ebay);
        list.add(em_all);
        adapter = new MyAdapter(getSupportFragmentManager(),this);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
       // tabLayout.getTabAt(0).setText("未处理");
       // tabLayout.getTabAt(1).setText("eBay 系统来信");
       // tabLayout.getTabAt(2).setText("所有邮件");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        instance=this;
        //ListAdapter listAdapter = new ListAdapter(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_email_Y) {
            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra("sender","Y");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            // Handle the camera action

        } else if (id == R.id.nav_email_J) {
            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra("sender","J");
             intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }else if (id == R.id.nav_email_F) {
            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra("sender","F");
           intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        } else if (id == R.id.nav_email_C) {
            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra("sender","C");
           intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }else if (id == R.id.nav_email_Z) {
            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra("sender","Z");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }else if (id == R.id.nav_email_D) {
            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra("sender","D");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }else if (id == R.id.nav_email_P) {
            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra("sender","P");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }else if (id == R.id.nav_email_BX) {
            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra("sender","BX");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }else if (id == R.id.nav_email_QD) {
            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra("sender","QD");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        } else if (id == R.id.nav_return_today) {

        } else if (id == R.id.nav_return_3day) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public static boolean isServiceExisted(Context context, String className) {
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList = activityManager
                .getRunningServices(Integer.MAX_VALUE);

        if (!(serviceList.size() > 0)) {
            return false;
        }

        for (int i = 0; i < serviceList.size(); i++) {
            RunningServiceInfo serviceInfo = serviceList.get(i);
            ComponentName serviceName = serviceInfo.service;

            if (serviceName.getClassName().equals(className)) {
                return true;
            }
        }
        return false;
    }
    public static MainActivity getInstance(){
        // 因为我们程序运行后，Application是首先初始化的，如果在这里不用判断instance是否为空
        return instance;
    }
    class MyAdapter extends FragmentPagerAdapter {
        private Context context;
        public MyAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }
        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }
        @Override
        public int getCount() {
            return list.size();
        }

        //重写这个方法，将设置每个Tab的标题
        @Override
        public CharSequence getPageTitle(int position) {
           // return titles[position];
           return list.get(position).getArguments().getString("total");
           // return null;
        }
    }
}
