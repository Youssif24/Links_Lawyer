package com.saad.youssif.arabiclawyer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements LoginView {

    LawyerPresenter lawyerPresenter;
    Button enterBtn;
    EditText nameEt,passwordEt;
    ProgressDialog progressDialog;
    ConnectivityManager connectivityManager;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        enterBtn=findViewById(R.id.signInBtn);
        nameEt=findViewById(R.id.login_nameEt);
        passwordEt=findViewById(R.id.login_passwordEt);
        progressDialog=new ProgressDialog(this);
        lawyerPresenter=new LawyerPresenter(this,this);
        sharedPrefManager=SharedPrefManager.getInstance(this);
        connectivityManager= (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        register_from_shrd();
        enterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkConnection())
                {
                    if(validateData())
                    {
                        progressDialog.setMessage("جاري تسجيل الدخول........");
                        progressDialog.show();
                        lawyerPresenter.lawyerLogin(nameEt.getText().toString(),passwordEt.getText().toString());
                    }

                }
                else
                {
                    Toast.makeText(LoginActivity.this,"من فضلك تأكد من إتصالك بالانترنت....",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    @Override
    public void showLoginResult(String result) {
        if(result.equals("success"))
        {
            Toast.makeText(this,"تم تسجيل الدخول بنجاح",Toast.LENGTH_LONG).show();
            save_user_data(nameEt.getText().toString(),passwordEt.getText().toString());
            progressDialog.dismiss();
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            LoginActivity.this.finish();
        }
        else
        {
            Toast.makeText(this,"من فضلك حاول مرة اخري......",Toast.LENGTH_LONG).show();
            progressDialog.dismiss();
        }

    }

    public boolean checkConnection()
    {
        NetworkInfo info=connectivityManager.getActiveNetworkInfo();
        return info!=null&&info.isConnected();
    }

    public boolean validateData()
    {
        if(nameEt.getText().toString().trim().equals(""))
        {
            nameEt.setError("إدخل الإسم");
            return false;
        }
        else if(passwordEt.getText().toString().trim().equals(""))
        {
            passwordEt.setError("إدخل كلمة المرور");
            return false;
        }
        else
        {
            return true;
        }

    }

    public void save_user_data(String username,String password)
    {
        sharedPrefManager.setName(username.trim());
        sharedPrefManager.setPassword(password.trim());
    }
    public void register_from_shrd()
    {
        String name=sharedPrefManager.getName();
        String pass=sharedPrefManager.getPassword();
        if(!(TextUtils.isEmpty(name))&&!(TextUtils.isEmpty(pass)))
        {
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            LoginActivity.this.finish();
        }
    }
}
