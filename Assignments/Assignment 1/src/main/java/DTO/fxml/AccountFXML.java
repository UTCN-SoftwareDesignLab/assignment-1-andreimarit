package DTO.fxml;


import javafx.beans.property.SimpleStringProperty;

public class AccountFXML {

    private final SimpleStringProperty id = new SimpleStringProperty("");
    private final SimpleStringProperty identificationNumber = new SimpleStringProperty("");
    private final SimpleStringProperty type = new SimpleStringProperty("");
    private final SimpleStringProperty amount = new SimpleStringProperty("");
    private final SimpleStringProperty client_id = new SimpleStringProperty("");
    private final SimpleStringProperty creationDate = new SimpleStringProperty("");

    public  AccountFXML(int id, String identificationNumber, String type, int amount, int client_id, String creationDate){
        setId(Integer.toString(id));
        setIdentificationNumber(identificationNumber);
        setType(type);
        setAmount(Integer.toString(amount));
        setClient_id(Integer.toString(client_id));
        setCreationDate(creationDate);
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber.set(identificationNumber);
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public String getIdentificationNumber() {
        return identificationNumber.get();
    }

    public SimpleStringProperty identificationNumberProperty() {
        return identificationNumber;
    }

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getAmount() {
        return amount.get();
    }

    public SimpleStringProperty amountProperty() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount.set(amount);
    }

    public String getClient_id() {
        return client_id.get();
    }

    public SimpleStringProperty client_idProperty() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id.set(client_id);
    }

    public String getCreationDate() {
        return creationDate.get();
    }

    public SimpleStringProperty creationDateProperty() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate.set(creationDate);
    }

}