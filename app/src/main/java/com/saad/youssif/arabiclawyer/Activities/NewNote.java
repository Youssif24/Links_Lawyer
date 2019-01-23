package com.saad.youssif.arabiclawyer.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.saad.youssif.arabiclawyer.Helpers.DBHelper;
import com.saad.youssif.arabiclawyer.R;

public class NewNote extends AppCompatActivity {

    EditText courtName,client,opponent,prev_sitting,decision;
    Button saveNoteBtn;
    DBHelper dbHelper;

    @Override
    protected void attachBaseContext(android.content.Context newBase) {
        super.attachBaseContext(uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        courtName=findViewById(R.id.new_note_court_name);
        client=findViewById(R.id.new_note_client);
        opponent=findViewById(R.id.new_note_opponent);
        prev_sitting=findViewById(R.id.new_note_prev_sitting);
        decision=findViewById(R.id.new_note_decision);
        saveNoteBtn=findViewById(R.id.save_newNote_btn);
        dbHelper=new DBHelper(this);

        saveNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dbHelper.insertNote(courtName.getText().toString().trim(),client.getText().toString().trim()
                        ,opponent.getText().toString().trim(),prev_sitting.getText().toString().trim()
                        ,decision.getText().toString().trim()))
                {
                    Toast.makeText(NewNote.this,"تم تسجيل البيانات",Toast.LENGTH_SHORT).show();
                    emptyFields();
                }
                else
                {
                    Toast.makeText(NewNote.this,"لم يتم تسجيل البيانات",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void emptyFields()
    {
        courtName.setText("");
        client.setText("");
        opponent.setText("");
        prev_sitting.setText("");
        decision.setText("");
    }
}
