package com.team02.handicraftmyanmar.controller;

import com.team02.handicraftmyanmar.model.accountModel;

public class accountController {
    accountModel modelAccount=new accountModel();
    public Boolean save(accountModel.Account Account){
        return modelAccount.save(Account);
    }
}
