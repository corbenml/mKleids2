package comben.mkleids.com.mkleids;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class EmailActivity extends AppCompatActivity {
    private List<Fragment> listFragment;
    private MyAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email_content);
        TabLayout tabLayout = (TabLayout)findViewById(R.id.email_main_tab);
        ViewPager viewPager = (ViewPager)findViewById(R.id.email_main_viewpage);
        listFragment=new ArrayList<Fragment>();
        listFragment.add(new email_content_asmail());
        listFragment.add(new email_content_mail());
        listFragment.add(new email_content_asorder());
        adapter = new MyAdapter(getSupportFragmentManager(),this);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(1);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("相关来往邮件");
        tabLayout.getTabAt(1).setText("本次来信");
        tabLayout.getTabAt(2).setText("相关购买订单");
        this.setTitle(""+getIntent().getStringExtra("messageid"));

    }
    class MyAdapter extends FragmentPagerAdapter {
        private Context context;
        public MyAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }
        @Override
        public Fragment getItem(int position) {
            return listFragment.get(position);
        }
        @Override
        public int getCount() {
            return listFragment.size();
        }

        //重写这个方法，将设置每个Tab的标题
        @Override
        public CharSequence getPageTitle(int position) {
            // return titles[position];
             return null;
        }
    }
}
