package com.team02.handicraftmyanmar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.team02.handicraftmyanmar.model.FirebaseDB;
import com.team02.handicraftmyanmar.model.itemModel;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    TextView tvName, tvDescription, tvPrice, tvSize, tvQuantity, tvDelivery, tvPhone, tvMail, tvAddress;

    ImageView ivShowItemImage;
    itemModel.Item item;
    List<itemModel.Item> itemList;

    Button btnOk, btnCancel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itemdetail);

        btnOk = (Button) findViewById(R.id.btnBuy);
        btnCancel = (Button) findViewById(R.id.btnNoBuy);

        tvName = (TextView) findViewById(R.id.tvName);

        tvDescription = (TextView) findViewById(R.id.tvDescription);

        tvPrice = (TextView) findViewById(R.id.tvPrice);

        tvSize = (TextView) findViewById(R.id.tvSize);

        tvQuantity = (TextView) findViewById(R.id.tvQuantity);

        tvDelivery = (TextView) findViewById(R.id.tvDelivery);

        tvPhone = (TextView) findViewById(R.id.tvPhone);

        tvAddress = (TextView) findViewById(R.id.tvAddress);

        ivShowItemImage = (ImageView) findViewById(R.id.ivShowItemImage);

        itemList = new ArrayList<>();

        final String id = getIntent().getStringExtra("data");

        Log.i("ID DATA", id);
        Query query = FirebaseDB.getFirebaseDB().child("Item").child(id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                item = dataSnapshot.getValue(itemModel.Item.class);

                item.setItemId(dataSnapshot.getKey().toString());
                itemList.add(item);

                for (itemModel.Item tmp : itemList) {
                    Glide.with(DetailActivity.this)
                            .load(tmp.getItemImage()).into(ivShowItemImage);

                    tvName.setText(tmp.getItemName().toString());

                    tvDescription.setText(tmp.getItemDescription().toString());

                    tvPrice.setText(tmp.getItemPrice().toString());

                    tvSize.setText(tmp.getItemSize().toString());

                    tvQuantity.setText(tmp.getItemQuantity().toString());

                    tvDelivery.setText(tmp.getItemDelivery().toString());

                    tvPhone.setText(tmp.getItemPhone().toString());



                    tvAddress.setText(tmp.getItemAddress().toString());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                String number = (String) tvPhone.getText().toString();
                i.setData(Uri.parse("tel:"+number));
                startActivity(i);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DetailActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
