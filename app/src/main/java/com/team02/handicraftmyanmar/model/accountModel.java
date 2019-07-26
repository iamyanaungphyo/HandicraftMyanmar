package com.team02.handicraftmyanmar.model;

import android.accounts.Account;

import com.google.firebase.database.DatabaseException;

import java.util.ArrayList;

public class accountModel {
    Boolean saved=null;
    ArrayList<Account> Accounts = new ArrayList<>();

    public static class Account{
        String accountId;
        String accountName;
        String accountPhone;
        String accountPassword;
        String accountRegion;
        String accountCity;


        public Account(){}

        public Account(String _accountName, String _accountPhone, String _accountPassword,String _accountRegion, String _accountCity){
            accountName=_accountName;
            accountPhone=_accountPhone;
            accountPassword=_accountPassword;
            accountRegion=_accountRegion;
            accountCity=_accountCity;
        }
        public String getAccountId() {
            return accountId;
        }

        public void setAccountId(String accountId) {
            this.accountId = accountId;
        }

        public String getAccountName() {
            return accountName;
        }

        public void setAccountName(String accountName) {
            this.accountName = accountName;
        }

        public String getAccountPhone() {
            return accountPhone;
        }

        public void setAccountPhone(String accountPhone) {
            this.accountPhone = accountPhone;
        }

        public String getAccountPassword() {
            return accountPassword;
        }

        public void setAccountPassword(String accountPassword) {
            this.accountPassword = accountPassword;
        }

        public String getAccountRegion() {
            return accountRegion;
        }

        public void setAccountRegion(String accountRegion) {
            this.accountRegion = accountRegion;
        }

        public String getAccountCity() {
            return accountCity;
        }

        public void setAccountCity(String accountCity) {
            this.accountCity = accountCity;
        }

    }

    public Boolean save(Account Account)
    {
        if(Account==null)
        {
            saved=false;
        }else
        {
            try
            {
                FirebaseDB.getFirebaseDB().child("Account").push().setValue(Account);
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
