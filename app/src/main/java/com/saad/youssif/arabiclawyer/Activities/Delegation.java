package com.saad.youssif.arabiclawyer.Activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.saad.youssif.arabiclawyer.Adapters.DelegationAdapter;
import com.saad.youssif.arabiclawyer.Adapters.IssueAdapter;
import com.saad.youssif.arabiclawyer.Helpers.DBHelper;
import com.saad.youssif.arabiclawyer.Model.DelegationDB;
import com.saad.youssif.arabiclawyer.Model.IssueDB;
import com.saad.youssif.arabiclawyer.R;

import java.util.ArrayList;

public class Delegation extends AppCompatActivity {

    DBHelper dbHelper;
    RecyclerView delegationRecycler;
    ArrayList<DelegationDB> delegationList;
    RecyclerView.LayoutManager layoutManager;
    FloatingActionButton floatingActionButton;
    android.support.v4.widget.SwipeRefreshLayout refreshLayout;
    DelegationAdapter delegationAdapter;




    @Override
    protected void attachBaseContext(android.content.Context newBase) {
        super.attachBaseContext(uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delegation);

        dbHelper=new DBHelper(this);
        delegationRecycler=findViewById(R.id.recyclerDelegation);
        floatingActionButton=findViewById(R.id.delegationFab);
        refreshLayout=findViewById(R.id.delegationSwipe);
        layoutManager=new LinearLayoutManager(this);
        delegationRecycler.setLayoutManager(layoutManager);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Delegation.this,NewDelegation.class);
                startActivity(intent);
            }
        });

        refreshLayout.setOnRefreshListener(new android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // KToast.customColorToast(MainActivity.this,"Refreshing...", Gravity.BOTTOM,KToast.LENGTH_SHORT, R.color.DarkMain);

                //Toast.makeText(MainActivity.this,"Refreshing...",Toast.LENGTH_SHORT).show();
               // showData();
                if(refreshLayout.isRefreshing())
                {
                    refreshLayout.setRefreshing(false);
                }

            }
        });


    }

    public void showData()
    {
        delegationList=dbHelper.getAllDelegations();
        delegationAdapter=new DelegationAdapter(delegationList,Delegation.this);
        // issueAdapter.OnClickListener(this);
        delegationRecycler.setAdapter(delegationAdapter);
    }
    @Override
    protected void onResume() {
        super.onResume();
        showData();
    }
}
