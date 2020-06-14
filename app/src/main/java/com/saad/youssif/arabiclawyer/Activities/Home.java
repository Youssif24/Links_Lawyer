package com.saad.youssif.arabiclawyer.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.saad.youssif.arabiclawyer.Adapters.GridAdapter;
import com.saad.youssif.arabiclawyer.Other.SharedPrefManager;
import com.saad.youssif.arabiclawyer.R;

import static com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype.RotateBottom;

public class Home extends AppCompatActivity {
   /* Button GlastBtn,MokelBtn,TwkeelBtn,kadyaBtn,notesBtn,outBtn;*/
    SharedPrefManager sharedPrefManager;
   //GridView gridView;
   CardView clients_card,issues_card,deleg_card,sittings_card,note_card,logout_card;

     @Override
    protected void attachBaseContext(android.content.Context newBase) {
        super.attachBaseContext(uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sharedPrefManager=SharedPrefManager.getInstance(this);
        clients_card=findViewById(R.id.clients_card);
        issues_card=findViewById(R.id.issues_card);
        deleg_card=findViewById(R.id.delegs_card);
        sittings_card=findViewById(R.id.sittings_card);
        note_card=findViewById(R.id.notes_card);
        logout_card=findViewById(R.id.logout_card);
        clients_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2=new Intent(Home.this,ClientActivity.class);
                startActivity(i2);
            }
        });
        issues_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i4=new Intent(Home.this,Issue.class);
                startActivity(i4);
            }
        });
        sittings_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(Home.this,Sitting.class);
                startActivity(i1);
            }
        });
        deleg_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3=new Intent(Home.this,Delegation.class);
                startActivity(i3);
            }
        });
        note_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i4=new Intent(Home.this,Notes.class);
                startActivity(i4);
            }
        });

        logout_card.setOnClickListener(new View.OnClickListener() {
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
        /*Toolbar toolbar = findViewById(R.id.home_toolbar);
        sharedPrefManager=SharedPrefManager.getInstance(this);
        gridView=findViewById(R.id.gridView);
        gridView.setAdapter(new GridAdapter(this));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if(position==0)
                {
                    Intent i2=new Intent(Home.this,ClientActivity.class);
                    startActivity(i2);

                }
                else if(position==1)
                {
                    Intent i1=new Intent(Home.this,Sitting.class);
                    startActivity(i1);

                }
                else if(position==2)
                {
                    Intent i4=new Intent(Home.this,Issue.class);
                    startActivity(i4);
                }
                else if(position==3)
                {
                    Intent i3=new Intent(Home.this,Delegation.class);
                    startActivity(i3);
                }
                else if (position==4)
                {
                    Intent i4=new Intent(Home.this,Notes.class);
                    startActivity(i4);
                }
                else
                {
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
            }
        });

       /* sharedPrefManager=SharedPrefManager.getInstance(this);

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
        });*/

        }
}
