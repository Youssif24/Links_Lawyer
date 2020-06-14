package com.saad.youssif.arabiclawyer.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.saad.youssif.arabiclawyer.R;

public class IssueDetails extends AppCompatActivity {

    TextView client_name_dt_tvID,issue_num_dt_tvID,issue_type_dt_tvID,opp_name_dt_tvID,
    court_name_dt_tvID,token_dt_tvID,details_dt_tvID;

    @Override
    protected void attachBaseContext(android.content.Context newBase) {
        super.attachBaseContext(uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_details);
        client_name_dt_tvID=findViewById(R.id.client_name_dt_tvID);
        issue_num_dt_tvID=findViewById(R.id.issue_num_dt_tvID);
        issue_type_dt_tvID=findViewById(R.id.issue_type_dt_tvID);
        opp_name_dt_tvID=findViewById(R.id.opp_name_dt_tvID);
        court_name_dt_tvID=findViewById(R.id.court_name_dt_tvID);
        token_dt_tvID=findViewById(R.id.token_dt_tvID);
        details_dt_tvID=findViewById(R.id.details_dt_tvID);
        Bundle bundle=getIntent().getExtras();
        client_name_dt_tvID.setText(bundle.getString("client_name"));
        issue_num_dt_tvID.setText(bundle.getString("issue_num"));
        issue_type_dt_tvID.setText(bundle.getString("issue_type"));
        opp_name_dt_tvID.setText(bundle.getString("opponent_name"));
        court_name_dt_tvID.setText(bundle.getString("court"));
        token_dt_tvID.setText(bundle.getString("client_token"));
        details_dt_tvID.setText(bundle.getString("issue_details"));
    }
}
