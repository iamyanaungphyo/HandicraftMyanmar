package com.team02.handicraftmyanmar;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.team02.handicraftmyanmar.util.SharedPreferencesUtil;
import com.team02.handicraftmyanmar.LoginActivity;


public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;
    private boolean InternetCheck = true;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        progress = (ProgressBar) findViewById(R.id.progressBar);
        progress.setVisibility(View.VISIBLE);

        PostDelayMethod();
    }


    public void PostDelayMethod() {


        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                boolean InternetResult = checkConnection();

                if (!InternetResult) {
                    progress.setVisibility(View.VISIBLE);
                    progress.setVisibility(View.GONE);

                    DialogAppear();

                } else if (SharedPreferencesUtil.getINSTANCE(SplashActivity.this).getBoolean("isLogin", false)) {
                    progress.setVisibility(View.GONE);
                    Intent intent=new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }  else {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        }, SPLASH_TIME_OUT);
    }

    public void DialogAppear() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this);

        builder.setTitle("Network Error!");
        builder.setMessage("No Internet Connection");

        builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                PostDelayMethod();
                recreate();
            }
        });

        builder.setCancelable(false);
        if (!checkConnection()) {
            builder.show();
        }

    }

    protected boolean isOnline() {

        ConnectivityManager conManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return conManager.getActiveNetworkInfo() != null && conManager.getActiveNetworkInfo().isConnectedOrConnecting();

    }

    private boolean checkConnection() {

        if (isOnline()) {
            return InternetCheck;
        } else {
            return false;
        }
    }

}
