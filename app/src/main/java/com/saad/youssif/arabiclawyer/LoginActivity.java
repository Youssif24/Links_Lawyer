package com.saad.youssif.arabiclawyer;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements LoginView {

    LawyerPresenter lawyerPresenter;
    Button enterBtn;
    EditText nameEt,passwordEt;
    ProgressDialog progressDialog;
    //this is test comment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        enterBtn=findViewById(R.id.signInBtn);
        nameEt=findViewById(R.id.login_nameEt);
        passwordEt=findViewById(R.id.login_passwordEt);
        progressDialog=new ProgressDialog(this);
        lawyerPresenter=new LawyerPresenter(this,this);
        enterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.setMessage("جاري تسجيل الدخول........");
                progressDialog.show();
                lawyerPresenter.lawyerLogin(nameEt.getText().toString(),passwordEt.getText().toString());
            }
        });
    }

    @Override
    public void showLoginResult(String result) {
        Toast.makeText(this,result,Toast.LENGTH_LONG).show();
        /*if(result.equals("success"))
        {
            Toast.makeText(this,"تم تسجيل الدخول بنجاح",Toast.LENGTH_LONG).show();
            progressDialog.dismiss();
        }
        else
        {
            Toast.makeText(this,"من فضلك حاول مرة اخري......",Toast.LENGTH_LONG).show();
            progressDialog.dismiss();
        }*/

    }
}
