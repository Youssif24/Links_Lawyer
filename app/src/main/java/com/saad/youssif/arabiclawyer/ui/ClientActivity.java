package com.saad.youssif.arabiclawyer.ui;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.saad.youssif.arabiclawyer.Adapters.ClientAdapter;
import com.saad.youssif.arabiclawyer.DBHelper;
import com.saad.youssif.arabiclawyer.Model.ClientDB;
import com.saad.youssif.arabiclawyer.R;

import java.util.ArrayList;

public class ClientActivity extends Activity {

    RecyclerView recyclerView;
    DBHelper dbHelper;
    ArrayList<ClientDB> clientDBArrayList;
    public static ClientAdapter clientAdapter;
    RecyclerView.LayoutManager layoutManager;
    FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("قائمة الموكلين");
        dbHelper=new DBHelper(this);
        recyclerView=findViewById(R.id.recyclerClient);
        floatingActionButton=findViewById(R.id.fab);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        clientDBArrayList=dbHelper.getAllrecord();
        clientAdapter=new ClientAdapter(clientDBArrayList,this);
        recyclerView.setAdapter(clientAdapter);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(ClientActivity.this,NewClient.class);
                startActivity(intent);


            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        clientAdapter.notifyDataSetChanged();
    }
}
