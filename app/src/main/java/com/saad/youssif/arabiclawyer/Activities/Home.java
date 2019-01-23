package com.saad.youssif.arabiclawyer.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.saad.youssif.arabiclawyer.Other.SharedPrefManager;
import com.saad.youssif.arabiclawyer.R;

import static com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype.RotateBottom;

public class Home extends AppCompatActivity {
    Button GlastBtn,MokelBtn,TwkeelBtn,kadyaBtn,notesBtn,outBtn;
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
        outBtn=findViewById(R.id.outBtn);
        notesBtn=findViewById(R.id.notesBtn);

        GlastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(Home.this,Sitting.class);
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
                Intent i3=new Intent(Home.this,Delegation.class);
                startActivity(i3);

            }
        });
        kadyaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i4=new Intent(Home.this,Issue.class);
                startActivity(i4);

            }
        });


        outBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final NiftyDialogBuilder dialogBuilder= NiftyDialogBuilder.getInstance(Home.this);

                dialogBuilder
                        .withTitle("تسجيل خروج")
                        .withTitleColor("#FFFFFF")
                        .withDividerColor("#11000000")
                        .withMessage("هل تريد تسجيل الخروج ؟")
                        .withMessageColor("#FFFFFFFF")
                        .withDialogColor("#3D5069")
                        .withIcon(R.drawable.delete_forever_24dp)
                        .withDuration(600)
                        .withEffect(RotateBottom)
                        .withButton1Text("لا")
                        .withButton2Text("نعم")
                        .isCancelableOnTouchOutside(false)

                        //.setCustomView(R.layout.alert_dialog_custom_view,v.getContext())         //.setCustomView(View or ResId,context)
                        .setButton1Click(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                dialogBuilder.dismiss();
                                return;
                            }
                        })
                        .setButton2Click(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                sharedPrefManager.clearSharedData();
                                startActivity(new Intent(Home.this,LoginActivity.class));
                                Home.this.finish();
                                dialogBuilder.dismiss();
                                return;
                            }
                        })
                        .show();

            }
        });

        notesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i4=new Intent(Home.this,Notes.class);
        startActivity(i4);
        }
        });

        }
        }
