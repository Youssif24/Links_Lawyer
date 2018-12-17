package com.saad.youssif.arabiclawyer.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.saad.youssif.arabiclawyer.R;

public class Home extends AppCompatActivity {
    Button GlastBtn,MokelBtn,TwkeelBtn,kadyaBtn,mozkratBtn,outBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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
                Intent i2=new Intent(Home.this,Client.class);
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
            public void onClick(View v) {
                Intent i6=new Intent(Home.this,Exit.class);
                startActivity(i6);

            }
        });
    }
}
