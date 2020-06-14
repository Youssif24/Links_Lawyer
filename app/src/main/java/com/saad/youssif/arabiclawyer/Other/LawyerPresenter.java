package com.saad.youssif.arabiclawyer.Other;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.saad.youssif.arabiclawyer.View.ApiClient;
import com.saad.youssif.arabiclawyer.View.ApiInterface;
import com.saad.youssif.arabiclawyer.View.LoginView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class LawyerPresenter {
    Context context;
    LoginView loginView;
    public LawyerPresenter(Context context, LoginView loginView)
    {
        this.context=context;
        this.loginView=loginView;

    }

    public void lawyerLogin(String name,String password)
    {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<String> call = apiInterface.check_user(name,password);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e("LoginResponse", response.raw().request().toString());

                if (response.isSuccessful()) {
                    loginView.showLoginResult(response.body());

                }
                else
                {
                    Toast.makeText(context,"error",Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
               Toast.makeText(context,t.toString(),Toast.LENGTH_LONG).show();

            }

        });
    }
}
