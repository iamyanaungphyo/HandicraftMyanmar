package com.team02.handicraftmyanmar.controller;

import android.util.Log;

import com.team02.handicraftmyanmar.model.itemModel;

public class itemController {
    itemModel modelItem=new itemModel();
    public Boolean save(itemModel.Item item){
        Log.d("NNNN","NNNN : " + item.getItemSize());
        return modelItem.save(item);
    }
}
