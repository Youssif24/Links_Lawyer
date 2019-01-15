package com.saad.youssif.arabiclawyer.Other;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.saad.youssif.arabiclawyer.Adapters.ClientAdapter;
import com.saad.youssif.arabiclawyer.Helpers.DBHelper;
import com.saad.youssif.arabiclawyer.R;
import com.saad.youssif.arabiclawyer.View.ClientUpdateInterface;

public class ClientCustomDialog extends Dialog implements
        android.view.View.OnClickListener,ClientUpdateInterface {


    public ClientCustomDialog(Context a) {
        super(a);
        // TODO Auto-generated constructor stub
        //this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.update_client_custom_dialog);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_yes:

                break;
            case R.id.btn_no:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }

    @Override
    public void getname_phone(String name, String phone) {
        /*nameEt.setText(name);
        phoneEt.setText(phone);*/
    }
}
