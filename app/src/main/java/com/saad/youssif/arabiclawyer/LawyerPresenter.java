package com.saad.youssif.arabiclawyer;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                    loginView.showLoginResult(response.body().toString());

                }
                else
                {
                    Toast.makeText(context,"error",Toast.LENGTH_LONG);

                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(context,t.toString(),Toast.LENGTH_LONG).show();

            }

        });
    }
}
