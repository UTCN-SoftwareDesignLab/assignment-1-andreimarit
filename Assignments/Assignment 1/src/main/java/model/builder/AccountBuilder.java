package model.builder;

import model.Account;

public class AccountBuilder {
    private Account account;

    public AccountBuilder(){account=new Account();}

    public AccountBuilder setId(int id){
        account.setId(id);
        return this;
    }

    public AccountBuilder setIdentificationNumber(String identificationNumber){
        account.setIdentificationNumber(identificationNumber);
        return this;
    }

    public AccountBuilder setType(String type){
        account.setType(type);
        return this;
    }

    public AccountBuilder setAmount(int amount){
        account.setAmount(amount);
        return this;
    }

    public AccountBuilder setCreationDate(String creationDate){
        account.setCreationDate(creationDate);
        return this;
    }

    public AccountBuilder setClientId(int clientId){
        account.setClient_id(clientId);
        return this;
    }

    public Account build(){return account;}
}
