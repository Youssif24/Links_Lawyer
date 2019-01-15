package com.saad.youssif.arabiclawyer.Activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.saad.youssif.arabiclawyer.Adapters.ClientAdapter;
import com.saad.youssif.arabiclawyer.Helpers.DBHelper;
import com.saad.youssif.arabiclawyer.Model.ClientDB;
import com.saad.youssif.arabiclawyer.R;
import com.saad.youssif.arabiclawyer.View.Update_View;

import java.util.ArrayList;

public class ClientActivity extends Activity implements Update_View{

   public  RecyclerView recyclerView;
    DBHelper dbHelper;
    ArrayList<ClientDB> clientDBArrayList;
    ClientAdapter clientAdapter;
    RecyclerView.LayoutManager layoutManager;
    FloatingActionButton floatingActionButton;
    android.support.v4.widget.SwipeRefreshLayout refreshLayout;

   // public Context c;
    public Button yes, no;
    EditText nameEt,phoneEt;


     @Override
    protected void attachBaseContext(android.content.Context newBase) {
        super.attachBaseContext(uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("قائمة الموكلين");


        dbHelper=new DBHelper(this);
        recyclerView=findViewById(R.id.recyclerClient);
        floatingActionButton=findViewById(R.id.fab);
        refreshLayout=findViewById(R.id.clientSwipe);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
       showData();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              Intent intent=new Intent(ClientActivity.this,NewClient.class);
              startActivity(intent);
            }
        });


        refreshLayout.setOnRefreshListener(new android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
               // KToast.customColorToast(MainActivity.this,"Refreshing...", Gravity.BOTTOM,KToast.LENGTH_SHORT, R.color.DarkMain);

                //Toast.makeText(MainActivity.this,"Refreshing...",Toast.LENGTH_SHORT).show();
                     showData();
                     if(refreshLayout.isRefreshing())
                {
                    refreshLayout.setRefreshing(false);
                }

            }
        });


    }

    public void showData()
    {
         clientDBArrayList=dbHelper.getAllrecord();
        clientAdapter=new ClientAdapter(clientDBArrayList,ClientActivity.this);
        clientAdapter.OnClickListener(this);
        recyclerView.setAdapter(clientAdapter);
}



    @Override
    public void list(final String id, final String tybe, final String phone, final String name,final int position) {
        final Dialog dialog = new Dialog(ClientActivity.this);
        dialog.setContentView(R.layout.update_client_custom_dialog);
        Button editDialogButton = dialog.findViewById(R.id.btn_yes);
        Button cancelDialogButton = dialog.findViewById(R.id.btn_no);
        final EditText newName=dialog.findViewById(R.id.client_dialog_name);
        final EditText newPhone=dialog.findViewById(R.id.client_dialog_phone);
        newName.setText(name);
        newPhone.setText(phone);

        // if button is clicked, close the custom dialog
        editDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dbHelper.updateData(id,newName.getText().toString(),tybe,newPhone.getText().toString()))
                {
                   showData();
                }
                else
                {
                    Toast.makeText(ClientActivity.this,"لم يتم تعديل البيانات",Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });
        cancelDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

            }
        });
        dialog.show();




    }
}
