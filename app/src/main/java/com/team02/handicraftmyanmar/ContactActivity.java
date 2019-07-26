package com.team02.handicraftmyanmar;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ContactActivity extends AppCompatActivity {
    String FACEBOOK_URL = "https://www.facebook.com/Live-For-Lives-1975246992532742/?ti=as";
    String FACEBOOK_PAGE_ID = "1975246992532742";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_layout);
        final Button phone = (Button) findViewById(R.id.advertisePhone);
        final Button facebook = (Button) findViewById(R.id.advertiseFacebook);
        Button gmail = (Button) findViewById(R.id.advertiseGmail);
        // Inflate the layout for this fragment
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                String number = (String) phone.getText().toString();
                i.setData(Uri.parse("tel:09791251493"));
                startActivity(i);
            }
        });


        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                String facebookUrl = getFacebookPageURL(getApplicationContext());
                facebookIntent.setData(Uri.parse(facebookUrl));

                startActivity(facebookIntent);
            }
        });

        gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SENDTO);
                i.setType("message/rfc822");
                i.setData(Uri.parse("mailto:"));
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{"thinthinmoe.bcsc@gmail.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                i.putExtra(Intent.EXTRA_TEXT, "body of email");
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {

                }
                startActivity(i);
            }

        });

    }

    public String getFacebookPageURL(Context context) {
        String FACEBOOK_URL = "http://www.facebook.com/thinthinmoe99";
        String FACEBOOK_PAGE_ID = "1975246992532742";
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else { //older versions of fb app
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL; //normal web url
        }
    }
}