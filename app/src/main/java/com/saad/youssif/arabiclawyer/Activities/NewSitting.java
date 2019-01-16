package com.saad.youssif.arabiclawyer.Activities;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.saad.youssif.arabiclawyer.Helpers.DBHelper;
import com.saad.youssif.arabiclawyer.Model.IssueDB;
import com.saad.youssif.arabiclawyer.Model.SittingDB;
import com.saad.youssif.arabiclawyer.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NewSitting extends AppCompatActivity {

    EditText client_nameEt,opponent_name,brol_num,delay_date,judgment;
    Spinner issue_numSpinner,clientSpinner;
   // List<String> clientList;
    List<String> issueNumList;
    Button saveSittingBtn;
    DBHelper dbHelper;
    String issueNum,sittingDate;
    private DatePickerDialog.OnDateSetListener datePickerDialog;
    private Calendar calendar;
    TextInputLayout delayTextInputLayout;




    @Override
    protected void attachBaseContext(android.content.Context newBase) {
        super.attachBaseContext(uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_sitting);
        issue_numSpinner=findViewById(R.id.issue_numSpinner);
       // clientSpinner=findViewById(R.id.sittingClientSpinner);
        client_nameEt=findViewById(R.id.new_sitting_clientEt);
        opponent_name=findViewById(R.id.new_sitting_opponentEt);
        brol_num=findViewById(R.id.new_sitting_brolEt);
        delay_date=findViewById(R.id.new_sitting_delayEt);
        judgment=findViewById(R.id.new_sitting_judgmentEt);
        saveSittingBtn=findViewById(R.id.save_newSitting_btn);
        delayTextInputLayout=findViewById(R.id.sitting_textInput_delay);
        dbHelper=new DBHelper(this);



       /* clientList=new ArrayList<>();
        clientList=dbHelper.getAllClients();
        ArrayAdapter<String> clientsAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, clientList);

        // Drop down layout style - list view with radio button
        clientsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        clientSpinner.setAdapter(clientsAdapter);*/

        issueNumList=new ArrayList<>();
        issueNumList=dbHelper.getAllIssuesNum();
        ArrayAdapter<String> issuesAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, issueNumList);

        // Drop down layout style - list view with radio button
        issuesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        issue_numSpinner.setAdapter(issuesAdapter);

        issue_numSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                IssueDB issueDB=new IssueDB();
                issueDB=dbHelper.getClient_Opponent_name(Integer.valueOf(issue_numSpinner.getSelectedItem().toString()));
                opponent_name.setText(issueDB.getOpponent_name().toString());
                client_nameEt.setText(issueDB.getClient_name().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        delay_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH);
                int day=calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog=new DatePickerDialog(NewSitting.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,datePickerDialog,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });

        datePickerDialog=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                sittingDate=year+"-"+month+"-"+dayOfMonth;
                delay_date.setText(sittingDate);

            }
        };




        saveSittingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                issueNum=issue_numSpinner.getSelectedItem().toString();
                if(dbHelper.insertSittingData(issueNum,client_nameEt.getText().toString(),opponent_name.getText().toString()
                                                ,brol_num.getText().toString(),delay_date.getText().toString(),judgment.getText().toString()))
                {
                    Toast.makeText(NewSitting.this,"تم حفظ البيانات",Toast.LENGTH_LONG).show();
                    emptyFields();
                    List<SittingDB> testSitting=new ArrayList<>();
                    testSitting=dbHelper.getAllSitting();
                    Toast.makeText(NewSitting.this,testSitting.get(0).getClient_name(),Toast.LENGTH_LONG).show();

                }
                else
                {
                    Toast.makeText(NewSitting.this,"لم يتم حفظ البيانات",Toast.LENGTH_LONG).show();
                }
            }
        });



    }

    private void emptyFields()
    {
        opponent_name.setText("");
        brol_num.setText("");
        delay_date.setText("");
        judgment.setText("");
    }
}
