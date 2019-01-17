package com.saad.youssif.arabiclawyer.Activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.saad.youssif.arabiclawyer.Adapters.ClientAdapter;
import com.saad.youssif.arabiclawyer.Adapters.SittingAdapter;
import com.saad.youssif.arabiclawyer.Helpers.DBHelper;
import com.saad.youssif.arabiclawyer.Model.ClientDB;
import com.saad.youssif.arabiclawyer.Model.SittingDB;
import com.saad.youssif.arabiclawyer.R;

import java.util.ArrayList;

public class Sitting extends AppCompatActivity {

    public RecyclerView recyclerView;
    DBHelper dbHelper;
    ArrayList<SittingDB> sittingList;
    SittingAdapter sittingAdapter;
    RecyclerView.LayoutManager layoutManager;
    FloatingActionButton floatingActionButton;
    android.support.v4.widget.SwipeRefreshLayout refreshLayout;
    @Override
    protected void attachBaseContext(android.content.Context newBase) {
        super.attachBaseContext(uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sitting);
        Toolbar toolbar = findViewById(R.id.sittingToolbar);
        floatingActionButton=findViewById(R.id.sittingFab);
        dbHelper=new DBHelper(this);
        recyclerView=findViewById(R.id.recyclerSitting);
        refreshLayout=findViewById(R.id.sittingSwipe);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        showData();


        refreshLayout.setOnRefreshListener(new android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                showData();
                if(refreshLayout.isRefreshing())
                {
                    refreshLayout.setRefreshing(false);
                }

            }
        });





        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Sitting.this,NewSitting.class));

            }
        });


    }

    public void showData()
    {
        sittingList=dbHelper.getAllSitting();
        sittingAdapter=new SittingAdapter(sittingList,Sitting.this);
        //sittingAdapter.OnClickListener(this);
        recyclerView.setAdapter(sittingAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showData();
    }

}
