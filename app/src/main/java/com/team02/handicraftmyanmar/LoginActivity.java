package com.team02.handicraftmyanmar;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import com.google.firebase.database.ValueEventListener;
import com.team02.handicraftmyanmar.model.FirebaseDB;
import com.team02.handicraftmyanmar.model.accountModel;
import com.team02.handicraftmyanmar.util.SharedPreferencesUtil;


import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity
{
    EditText etLoginName, etLoginPassword;
    Button btnLogin, btnRegister;

    List<accountModel.Account> accountList;
    private final accountModel.Account a = null;

    @Override
    protected void onCreate(Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.login);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister=(Button) findViewById(R.id.btnRegister);
        etLoginName = (EditText) findViewById(R.id.etLoginName);
        etLoginPassword = (EditText) findViewById(R.id.etLoginPassword);

        accountList = new ArrayList<>();

        FirebaseDB.getFirebaseDB().child("Account")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot accountSnapshot : dataSnapshot.getChildren()) {

                            accountModel.Account account = accountSnapshot.getValue(accountModel.Account.class);
                            account.setAccountId(accountSnapshot.getKey().toString());
                            accountList.add(account);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



        btnLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                String Name = etLoginName.getText().toString();
                String Password = etLoginPassword.getText().toString();

                boolean isLoginSuccess = false;

                for (int i=0;i<accountList.size();i++)
                {
                    accountModel.Account tmp=accountList.get(0);
                    Log.i("ss", tmp.getAccountName());
                }
                for (accountModel.Account tmp : accountList) {

                    if (tmp.getAccountName().equals(Name) && tmp.getAccountPassword().equals(Password)) {

                        isLoginSuccess = true;
                        SharedPreferencesUtil.getINSTANCE(LoginActivity.this).clearData();
                        SharedPreferencesUtil.getINSTANCE(LoginActivity.this).setBoolean("isLogin", true);
                        SharedPreferencesUtil.getINSTANCE(LoginActivity.this).setValue("accountId", tmp.getAccountId());
                        SharedPreferencesUtil.getINSTANCE(LoginActivity.this).setValue("accountName",tmp.getAccountName());
                        SharedPreferencesUtil.getINSTANCE(LoginActivity.this).setValue("accountRegion",tmp.getAccountRegion());
                        SharedPreferencesUtil.getINSTANCE(LoginActivity.this).setValue("accountCity",tmp.getAccountCity());
                        finish();
                        break;
                    }
                }
                if (isLoginSuccess) {
                    Intent intent =new Intent(LoginActivity.this, MainActivity.class);

                    startActivity(intent);
                    finish();

                } else {
                    clearPassword();
                    Toast.makeText(getApplicationContext(), "Login UnSuccessful, Please try again!", Toast.LENGTH_SHORT).show();
                }

            }
        });


        btnRegister.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick (View v){
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();


            }
            });
        }

    private void clearPassword() {
        etLoginPassword.setText("");
    }


}

