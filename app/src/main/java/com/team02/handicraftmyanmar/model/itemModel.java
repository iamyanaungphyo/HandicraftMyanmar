package com.team02.handicraftmyanmar.model;

import android.util.Log;

import com.google.firebase.database.DatabaseException;

public class itemModel {
    Boolean saved = null;

    public itemModel() {
    }

    public static class Item {
        String itemId;
        String accountId;
        String itemName;
        String itemImage;
        String itemDescription;
        String itemPrice;
        String itemSize;
        String itemQuantity;
        String itemDelivery;
        String itemPhone;
        String itemAddress;

        public Item() {
        }

        public Item(String _itemImage,String _itemName, String _itemDescription, String _itemPrice, String _itemSize, String _itemQuantity, String _itemDelivery,
                    String _itemPhone, String _itemAddress) {
            itemImage=_itemImage;
            itemName = _itemName;
            itemDescription = _itemDescription;
            itemPrice = _itemPrice;
            itemSize = _itemSize;
            itemQuantity = _itemQuantity;
            itemDelivery = _itemDelivery;
            itemPhone = _itemPhone;

            itemAddress = _itemAddress;
        }

        public String getAccountId() {
            return accountId;
        }

        public void setAccountId(String accountId) {
            this.accountId = accountId;
        }
        public String getItemId() {
            return itemId;
        }

        public void setItemId(String id) {
            this.itemId = id;
        }

        public String getItemImage() {
            return itemImage;
        }

        public void setItemImage(String itemImage) {
            this.itemImage = itemImage;
        }

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public String getItemDescription() {
            return itemDescription;
        }

        public void setItemDescription(String itemDescription) {
            this.itemDescription = itemDescription;
        }

        public String getItemPrice() {
            return itemPrice;
        }

        public void setItemPrice(String itemPrice) {
            this.itemPrice = itemPrice;

        }

        public String getItemSize() {
            return itemSize;
        }

        public void setItemSize(String itemSize) {
            this.itemSize = itemSize;
        }

        public String getItemQuantity() {
            return itemQuantity;
        }

        public void setItemQuantity(String itemQuantity) {
            this.itemQuantity = itemQuantity;
        }

        public String getItemDelivery() {
            return itemDelivery;
        }

        public void setItemDelivery(String itemDelivery) {
            this.itemDelivery = itemDelivery;
        }

        public String getItemPhone() {
            return itemPhone;
        }

        public void setItemPhone(String itemPhone) {
            this.itemPhone = itemPhone;
        }

        public String getItemAddress() {
            return itemAddress;
        }

        public void setItemAddress(String itemAddress) {
            this.itemAddress = itemAddress;
        }


    }

    public Boolean save(Item item)
    {
        if(item==null)
        {
            saved=false;
        }else
        {
            try
            {
                Log.d("NNNNNN","NNNNN : "+item.itemSize);
                FirebaseDB.getFirebaseDB().child("Item").push().setValue(item);
                saved=true;

            }catch (DatabaseException e)
            {
                e.printStackTrace();
                saved=false;
            }
        }

        return saved;
    }

}
