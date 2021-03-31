package DTO;

import model.Account;

public class AccountDTO {

    private int id;
    private String identificationNumber;
    private String type;
    private int amount;
    private String creationDate;
    private int client_id;

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public AccountDTO(Account account) {
        this.id = account.getId();
        this.identificationNumber = account.getIdentificationNumber();
        this.type = account.getType();
        this.amount = account.getAmount();
        this.creationDate = account.getCreationDate();
        this.client_id = account.getClient_id();
    }
}
