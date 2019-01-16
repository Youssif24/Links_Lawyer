package com.saad.youssif.arabiclawyer.Activities;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.saad.youssif.arabiclawyer.Helpers.DBHelper;
import com.saad.youssif.arabiclawyer.R;

import java.util.ArrayList;
import java.util.List;

public class NewIssue extends Activity {
    List<String> tokenList;
    List<String> clientList;
    Spinner tokenSpinner,clientSpinner;
    DBHelper dbHelper;
    String token,client_name;

    EditText new_issue_num,new_issue_type,new_issue_opponent,new_issue_court,new_issue_details;
    Button saveBtn;


    @Override
    protected void attachBaseContext(android.content.Context newBase) {
        super.attachBaseContext(uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper.wrap(newBase));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_issue);
        dbHelper=new DBHelper(this);
        tokenSpinner=findViewById(R.id.tokenSpinner);
        clientSpinner=findViewById(R.id.clientSpinner);
        saveBtn=findViewById(R.id.save_newIssue_btn);
        new_issue_num=findViewById(R.id.new_issue_numEt);
        new_issue_type=findViewById(R.id.new_issue_typeEt);
        new_issue_opponent=findViewById(R.id.new_issue_opponentEt);
        new_issue_court=findViewById(R.id.new_issue_courtEt);
        new_issue_details=findViewById(R.id.new_issue_detailsEt);

        tokenList=new ArrayList<>();
        tokenList.add("صفة الموكل.....");
        tokenList.add("متهم");
        tokenList.add("مدعي عليه");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tokenList);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        tokenSpinner.setAdapter(dataAdapter);

        clientList=new ArrayList<>();
        clientList=dbHelper.getAllClients();
        ArrayAdapter<String> clientsAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, clientList);

        // Drop down layout style - list view with radio button
        clientsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        clientSpinner.setAdapter(clientsAdapter);



        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                token=tokenSpinner.getSelectedItem().toString();
                client_name=clientSpinner.getSelectedItem().toString();
                if(dbHelper.insertIssueData(new_issue_num.getText().toString(),new_issue_type.getText().toString()
                                            ,token,client_name,new_issue_opponent.getText().toString()
                                            ,new_issue_court.getText().toString(),new_issue_details.getText().toString()))
                {
                    Toast.makeText(NewIssue.this,"تم حفظ البيانات",Toast.LENGTH_LONG).show();
                    emptyFields();
                }
                else
                {
                    Toast.makeText(NewIssue.this,"لم يتم حفظ البيانات",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void emptyFields()
    {
        new_issue_num.setText("");
        new_issue_type.setText("");
        new_issue_opponent.setText("");
        new_issue_court.setText("");
        new_issue_details.setText("");
    }

}
