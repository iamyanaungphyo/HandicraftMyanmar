package com.team02.handicraftmyanmar.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.team02.handicraftmyanmar.R;
import com.team02.handicraftmyanmar.controller.itemController;
import com.team02.handicraftmyanmar.model.FirebaseDB;
import com.team02.handicraftmyanmar.model.itemModel;
import com.team02.handicraftmyanmar.util.SharedPreferencesUtil;

import java.io.IOException;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

public class SaleFragment extends Fragment {

    @BindView(R.id.relativeLayout)
    RelativeLayout relativeLayout;

    @BindView(R.id.ivItemImage)
    ImageView ivItemImage;

    @BindView(R.id.etItemName)
    EditText etItemName;

    @BindView(R.id.etItemDescription)
    EditText etItemDescription;

    @BindView(R.id.etItemPrice)
    EditText etItemPrice;

    @BindView(R.id.etItemSize)
    EditText etItemSize;

    @BindView(R.id.etItemQuantity)
    EditText etItemQuantity;

    @BindView(R.id.etItemDelivery)
    EditText etItemDelivery;

    @BindView(R.id.etItemPhone)
    EditText etItemPhone;

    @BindView(R.id.etItemAddress)
    EditText etItemAddress;



    private itemController itemController;

    StorageReference storageReference;

    Uri imgUri;

    private int PICK_IMAGE_REQUEST = 1;


    public static SaleFragment newInstance() {
        SaleFragment fragment = new SaleFragment();
        return fragment;
    }

    public SaleFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sale, container, false);

        ButterKnife.bind(this, rootView);

        itemController = new itemController();

        storageReference = FirebaseStorage.getInstance().getReference();

        ivItemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
// Show only images, no videos or anything else
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
// Always show the chooser (if there are multiple options available)
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });

        Button upload = rootView.findViewById(R.id.btn_upload_sale);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ivItemImage == null) {
                    Toast.makeText(getContext(), "Photo is required.", Toast.LENGTH_LONG).show();
                }
                if (etItemName.getText().toString().equals("")) {
                    etItemName.setError("Please fill Name!");
                } else if (etItemDescription.getText().toString().equals("")) {
                    etItemDescription.setError("Please fill Description!");
                } else if (etItemPrice.getText().toString().equals("")) {
                    etItemPrice.setError("Please fill Price!");
                } else if (etItemSize.getText().toString().equals("")) {
                    etItemSize.setError("Please fill Size!");
                } else if (etItemQuantity.getText().toString().equals("")) {
                    etItemQuantity.setError("Please fill Quantity!");
                } else if (etItemDelivery.getText().toString().equals("")) {
                    etItemDelivery.setError("Please fill Delivery!");
                } else if (etItemPhone.getText().toString().equals("")) {
                    etItemPhone.setError("Please fill Phone!");
                } else if (etItemAddress.getText().toString().equals("")) {
                    etItemAddress.setError("Please fill Address!");
                } else /* if(imgUri !=null &&!TextUtils.isEmpty(etItemName.getText()) &&
                        !TextUtils.isEmpty(etItemDescription.getText()) &&
                        !TextUtils.isEmpty(etItemPrice.getText()) &&
                        !TextUtils.isEmpty(etItemSize.getText()) &&
                        !TextUtils.isEmpty(etItemQuantity.getText()) &&
                        !TextUtils.isEmpty(etItemDelivery.getText()) &&
                        !TextUtils.isEmpty(etItemPhone.getText()) &&
                        !TextUtils.isEmpty(etItemMail.getText()) &&
                        !TextUtils.isEmpty(etItemAddress.getText())
                        ) */ {

                    final ProgressDialog progressDialog = new ProgressDialog(getContext());
                    progressDialog.setTitle("Uploading.......");
                    progressDialog.show();

                    final StorageReference ref = storageReference.child("images/" + UUID.randomUUID().toString());

                    final String itemName = etItemName.getText().toString();
                            final String itemDes= etItemDescription.getText().toString();
                            final String itemPri= etItemPrice.getText().toString();
                            final String itemSiz= etItemSize.getText().toString();
                            final String itemQty= etItemQuantity.getText().toString();
                            final String itemDel= etItemDelivery.getText().toString();
                            final String itemPh= etItemPhone.getText().toString();
                            final String itemAddress= etItemAddress.getText().toString();


                    ref.putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String message = "";

                                    Log.d("NNNN","NNNN : " + itemSiz);

                                    boolean isSave = itemController.save(
                                            new itemModel.Item(
                                                    uri.toString(),
                                                    itemName,
                                                    itemDes,
                                                    itemPri,
                                                    itemSiz,
                                                    itemQty,
                                                    itemDel,
                                                    itemPh,
                                                    itemAddress
                                            )

                                    );
                                    if (!isSave) {
                                        message = "UnSuccessful";
                                    }

                                    if (isSave) {
                                        message = "Successful";
                                        ClearData();
                                    }

                                    Toast.makeText(getContext(), message, Snackbar.LENGTH_LONG).show();

                                }

                                private void ClearData() {

                                    {
                                        ivItemImage.setImageResource(R.drawable.camera_icon);
                                        etItemName.setText("");
                                        etItemDescription.setText("");
                                        etItemPrice.setText("");
                                        etItemSize.setText("");
                                        etItemQuantity.setText("");
                                        etItemDelivery.setText("");
                                        etItemPhone.setText("");
                                        etItemAddress.setText("");
                                    }
                                }
                            });
                        }
                    });
                }

            }
        });
return rootView;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            imgUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imgUri);
                // Log.d(TAG, String.valueOf(bitmap));
                Glide.with(this)
                        .load(bitmap)
                        .into(ivItemImage);
                // imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
