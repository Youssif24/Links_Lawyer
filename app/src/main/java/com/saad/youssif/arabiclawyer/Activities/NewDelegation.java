package com.saad.youssif.arabiclawyer.Activities;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import com.saad.youssif.arabiclawyer.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NewDelegation extends AppCompatActivity {

    EditText del_num,del_org,del_date;
    Spinner del_clientsSpinner;
    List<String> clientList;
    Button saveDelBtn;
    DBHelper dbHelper;
    String clientName,delDate;
    private DatePickerDialog.OnDateSetListener datePickerDialog;
    private Calendar calendar;

    @Override
    protected void attachBaseContext(android.content.Context newBase) {
        super.attachBaseContext(uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_delegation);
        dbHelper=new DBHelper(this);
        del_clientsSpinner=findViewById(R.id.del_clientsSpinner);
        del_num=findViewById(R.id.new_del_numEt);
        del_date=findViewById(R.id.new_del_dateEt);
        del_org=findViewById(R.id.new_del_orgEt);
        saveDelBtn=findViewById(R.id.save_newDel_btn);
        del_clientsSpinner=findViewById(R.id.del_clientsSpinner);

        clientList=new ArrayList<>();
        clientList=dbHelper.getAllClients();
        ArrayAdapter<String> delsAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, clientList);

        // Drop down layout style - list view with radio button
        delsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        del_clientsSpinner.setAdapter(delsAdapter);

        del_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH);
                int day=calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog=new DatePickerDialog(NewDelegation.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,datePickerDialog,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        datePickerDialog=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                delDate=year+"-"+month+"-"+dayOfMonth;
                del_date.setText(delDate);

            }
        };

        saveDelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clientName=del_clientsSpinner.getSelectedItem().toString();
                if(dbHelper.insertDelegationData(del_num.getText().toString(),clientName,del_org.getText().toString(),del_date.getText().toString()))
                {
                    Toast.makeText(NewDelegation.this,"تم حفظ البيانات",Toast.LENGTH_LONG).show();
                    emptyFields();
                }
                else
                {
                    Toast.makeText(NewDelegation.this,"لم يتم حفظ البيانات",Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    public void emptyFields()
    {
        del_num.setText("");
        del_org.setText("");
        del_date.setText("");
    }
}
