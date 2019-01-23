package com.saad.youssif.arabiclawyer.Activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.saad.youssif.arabiclawyer.Adapters.ViewPagerAdapter;
import com.saad.youssif.arabiclawyer.Fragments.DelegationFragment;
import com.saad.youssif.arabiclawyer.Fragments.IssuesFragment;
import com.saad.youssif.arabiclawyer.Fragments.SittingFragment;
import com.saad.youssif.arabiclawyer.Helpers.DBHelper;
import com.saad.youssif.arabiclawyer.Other.SharedPrefManager;
import com.saad.youssif.arabiclawyer.R;

public class SingleClient extends FragmentActivity {

    TextView idTv,nameTv,phoneTv,typeTv;
    String clientID;
    DBHelper dbHelper;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void attachBaseContext(android.content.Context newBase) {
        super.attachBaseContext(uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_client);
        viewPager = findViewById(R.id.viewpager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setCustomFont();
        sharedPrefManager = SharedPrefManager.getInstance(this);

        dbHelper = new DBHelper(this);
        idTv = findViewById(R.id.single_client_id);
        nameTv = findViewById(R.id.single_client_name);
        phoneTv = findViewById(R.id.single_client_phone);
        typeTv = findViewById(R.id.single_client_type);
        getIntentData();

    }

    public void getIntentData()
    {
        Intent intent=getIntent();
        clientID=intent.getExtras().getString("id");
        idTv.setText(clientID);
        nameTv.setText(intent.getExtras().getString("name"));
        phoneTv.setText(intent.getExtras().getString("phone"));
        typeTv.setText(intent.getExtras().getString("type"));
        sharedPrefManager.setClientId(clientID);
    }

    public void setCustomFont() {

        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();

        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);

            int tabChildsCount = vgTab.getChildCount();

            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    //Put your font in assests folder
                    //assign name of the font here (Must be case sensitive)
                    ((TextView) tabViewChild).setTypeface(Typeface.createFromAsset(getAssets(), "font/Cairo-Light.ttf"));
                }
            }
        }
    }


}
