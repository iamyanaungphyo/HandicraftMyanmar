package com.team02.handicraftmyanmar.fragment;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import com.team02.handicraftmyanmar.R;
import com.team02.handicraftmyanmar.adapter.showitemAdapter;
import com.team02.handicraftmyanmar.model.FirebaseDB;
import com.team02.handicraftmyanmar.model.itemModel;

import java.util.ArrayList;
import java.util.List;

import com.team02.handicraftmyanmar.util.SharedPreferencesUtil;



public class HomeFragment extends Fragment {
    private showitemAdapter mFirebaseAdapter;
    RecyclerView recyclerView;
    private List<itemModel.Item> itemList= new ArrayList<>();
    Context context;

    public HomeFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view= inflater.inflate(R.layout.fragment_home,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_view);

        mFirebaseAdapter = new showitemAdapter(itemList, getActivity());

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mFirebaseAdapter);


        String id = SharedPreferencesUtil.getINSTANCE(getActivity()).getValue("itemId", "item");
        Query query = FirebaseDB.getFirebaseDB().child("Item");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                itemList.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    itemModel.Item tmp = ds.getValue(itemModel.Item.class);
                    tmp.setItemId(ds.getKey().toString());
                    itemList.add(tmp);
                }

                Log.d("AAAAA", "AAAAAA : " + itemList.size());
                mFirebaseAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}

