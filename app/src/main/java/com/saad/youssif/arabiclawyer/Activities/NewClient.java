package com.saad.youssif.arabiclawyer.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.saad.youssif.arabiclawyer.Helpers.DBHelper;
import com.saad.youssif.arabiclawyer.R;

public class NewClient extends AppCompatActivity {
    EditText NationID,Name,Type,Phone;
    Button saveBtn;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_client);
        NationID=findViewById(R.id.new_client_idEt);
        Name=findViewById(R.id.new_client_nameEt);
        Type=findViewById(R.id.new_client_typeEt);
        Phone=findViewById(R.id.new_client_phoneEt);
        saveBtn=findViewById(R.id.save_newClient_btn);
        dbHelper=new DBHelper(this);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean checkAdding=dbHelper.insertClientData(NationID.getText().toString(),Name.getText().toString()
                ,Type.getText().toString(),Phone.getText().toString()
                );

                if(checkAdding==true)
                {
                    Toast.makeText(NewClient.this,"تمت الإضافة بنجاح",Toast.LENGTH_LONG).show();
                    emptyFields();
                }
                else {
                    Toast.makeText(NewClient.this,"لم تتم الإضافة",Toast.LENGTH_LONG).show();
                }


            }
        });
    }

    public void emptyFields()
    {
        NationID.setText("");
        Name.setText("");
        Type.setText("");
        Phone.setText("");
    }
}
