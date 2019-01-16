package com.saad.youssif.arabiclawyer.Activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.saad.youssif.arabiclawyer.Adapters.IssueAdapter;
import com.saad.youssif.arabiclawyer.Helpers.DBHelper;
import com.saad.youssif.arabiclawyer.Model.IssueDB;
import com.saad.youssif.arabiclawyer.R;

import java.util.ArrayList;

public class Issue extends AppCompatActivity {

    DBHelper dbHelper;
    RecyclerView issueRecycler;
    ArrayList<IssueDB> issueList;
    RecyclerView.LayoutManager layoutManager;
    FloatingActionButton floatingActionButton;
    android.support.v4.widget.SwipeRefreshLayout refreshLayout;
    IssueAdapter issueAdapter;


    @Override
    protected void attachBaseContext(android.content.Context newBase) {
        super.attachBaseContext(uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue);
        dbHelper=new DBHelper(this);
        issueRecycler=findViewById(R.id.recyclerIssue);
        floatingActionButton=findViewById(R.id.issueFab);
        refreshLayout=findViewById(R.id.issueSwipe);
        layoutManager=new LinearLayoutManager(this);
        issueRecycler.setLayoutManager(layoutManager);
        showData();


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Issue.this,NewIssue.class);
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
        issueList=dbHelper.getAllIssues();
        issueAdapter=new IssueAdapter(issueList,Issue.this);
       // issueAdapter.OnClickListener(this);
        issueRecycler.setAdapter(issueAdapter);
    }
}
