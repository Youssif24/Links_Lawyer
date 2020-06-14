package com.saad.youssif.arabiclawyer.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.saad.youssif.arabiclawyer.Model.User;
import com.saad.youssif.arabiclawyer.R;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Register extends AppCompatActivity {

    Button loginBtn,registBtn;
    EditText nameEt,emailEt,phoneEt,passwordEt;
    private DatabaseReference mDatabase;
    public FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_lawyer);
        loginBtn=findViewById(R.id.btnLogin);
        registBtn=findViewById(R.id.registerBtn);
        nameEt=findViewById(R.id.nameEt);
        emailEt=findViewById(R.id.emailEt);
        phoneEt=findViewById(R.id.phoneEt);
        passwordEt=findViewById(R.id.passwordEt);
        progressDialog=new ProgressDialog(this);
        mDatabase= FirebaseDatabase.getInstance().getReference();
        firebaseAuth=FirebaseAuth.getInstance();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lgIntent=new Intent(Register.this,LoginActivity.class);
                startActivity(lgIntent);
                Register.this.finish();
            }
        });
        registBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String name=nameEt.getText().toString().trim();
                String email=emailEt.getText().toString().trim();
                //String phone=phoneEt.getText().toString().trim();
                String password=passwordEt.getText().toString().trim();
                progressDialog.setMessage("Registering....");
                progressDialog.show();
                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            progressDialog.dismiss();
                            passwordEt.setText("");
                            emailEt.setText("");
                            Toast.makeText(Register.this, "Success", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            progressDialog.dismiss();
                            Toast.makeText(Register.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }

    public void createNewUser(String userId,String name,String email,String phone, String password)
    {
        User user = new User(name, email,phone,password);

        mDatabase.child("users").child(userId).setValue(user);
    }
}
