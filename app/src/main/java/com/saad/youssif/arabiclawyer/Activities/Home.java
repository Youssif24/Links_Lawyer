package com.saad.youssif.arabiclawyer.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.saad.youssif.arabiclawyer.Other.SharedPrefManager;
import com.saad.youssif.arabiclawyer.R;

public class Home extends AppCompatActivity {
    Button GlastBtn,MokelBtn,TwkeelBtn,kadyaBtn,mozkratBtn,outBtn;
    SharedPrefManager sharedPrefManager;

     @Override
    protected void attachBaseContext(android.content.Context newBase) {
        super.attachBaseContext(uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.home_toolbar);
        sharedPrefManager=SharedPrefManager.getInstance(this);

        GlastBtn=findViewById(R.id.glsat);
        MokelBtn=findViewById(R.id.mokel);
        TwkeelBtn=findViewById(R.id.twkeel);
        kadyaBtn=findViewById(R.id.kadya);
        mozkratBtn=findViewById(R.id.mozkrat);
        outBtn=findViewById(R.id.outBtn);

        GlastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(Home.this,Glsat.class);
                startActivity(i1);

            }
        });
        MokelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(Home.this,ClientActivity.class);
                startActivity(i2);

            }
        });
        TwkeelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3=new Intent(Home.this,Twkeel.class);
                startActivity(i3);

            }
        });
        kadyaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i4=new Intent(Home.this,kadya.class);
                startActivity(i4);

            }
        });
        mozkratBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i5=new Intent(Home.this,mozkrat.class);
                startActivity(i5);

            }
        });

        outBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPrefManager.clearSharedData();
                startActivity(new Intent(Home.this,LoginActivity.class));
                Home.this.finish();
            }
        });

    }
}
