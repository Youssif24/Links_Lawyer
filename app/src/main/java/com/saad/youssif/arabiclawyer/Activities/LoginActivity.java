package com.saad.youssif.arabiclawyer.Activities;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.security.ProviderInstaller;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.saad.youssif.arabiclawyer.Other.LawyerPresenter;
import com.saad.youssif.arabiclawyer.View.LoginView;
import com.saad.youssif.arabiclawyer.R;
import com.saad.youssif.arabiclawyer.Other.SharedPrefManager;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginActivity extends AppCompatActivity implements LoginView{

    LawyerPresenter lawyerPresenter;
    Button loginBtn,registerbtn;
    EditText nameEt,passwordEt;
    ProgressDialog progressDialog;
    ConnectivityManager connectivityManager;
    SharedPrefManager sharedPrefManager;
  //  FirebaseAuth firebaseAuth;
   // EditText emailEt;
  //  ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        registerbtn=findViewById(R.id.registerBtn);
        loginBtn=findViewById(R.id.loginBtn);
        nameEt=findViewById(R.id.login_nameEt);
        passwordEt=findViewById(R.id.login_passwordEt);
        progressDialog=new ProgressDialog(this);

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rgIntent= new Intent(LoginActivity.this,Register.class);
                startActivity(rgIntent);
            }
        });
        //firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        lawyerPresenter=new LawyerPresenter(this,this);
        sharedPrefManager=SharedPrefManager.getInstance(this);
        connectivityManager= (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        register_from_shrd();
        //handleSSLHandshake();
       // initializeSSLContext(this);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkConnection())
                {
                    if(validateData())
                    {
                     progressDialog.setMessage("جاري تسجيل الدخول........");
                        progressDialog.show();
                      //  userLogin();
                       lawyerPresenter.lawyerLogin(nameEt.getText().toString().trim(),passwordEt.getText().toString().trim());



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
            Intent intent=new Intent(LoginActivity.this,Home.class);
            startActivity(intent);
            //destory activity
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

   /*public void userLogin()
    {
        String username=nameEt.getText().toString().trim();
        String password=passwordEt.getText().toString().trim();
        firebaseAuth.signInWithEmailAndPassword(username,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(LoginActivity.this,"تم تسجيل الدخول بنجاح",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this,"لم يتم تسجيل الدخول",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();

                    }
                });
    }
*/
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
            startActivity(new Intent(LoginActivity.this,Home.class));
            LoginActivity.this.finish();
        }
    }

    /**
     * Enables https connections
     */
   /* @SuppressLint("TrulyRandom")
    public static void handleSSLHandshake() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
        } catch (Exception ignored) {
        }
    }

    public static void initializeSSLContext(Context mContext) {
        try {
            SSLContext.getInstance("TLSv1.2");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            ProviderInstaller.installIfNeeded(mContext.getApplicationContext());
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }*/

}
