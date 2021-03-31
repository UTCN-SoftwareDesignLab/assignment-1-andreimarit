package DTO.fxml;

import javafx.beans.property.SimpleStringProperty;

public class ClientFXML {

    private final SimpleStringProperty id = new SimpleStringProperty("");
    private final SimpleStringProperty name = new SimpleStringProperty("");
    private final SimpleStringProperty identityNumber = new SimpleStringProperty("");
    private final SimpleStringProperty cnp = new SimpleStringProperty("");
    private final SimpleStringProperty address = new SimpleStringProperty("");

    public  ClientFXML(int id, String name, String identityNumber, String cnp, String address){
        setId(Integer.toString(id));
        setName(name);
        setIdentityNumber(identityNumber);
        setCnp(cnp);
        setAddress(address);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber.set(identityNumber);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getIdentityNumber() {
        return identityNumber.get();
    }

    public SimpleStringProperty identityNumberProperty() {
        return identityNumber;
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

    public String getCnp() {
        return cnp.get();
    }

    public SimpleStringProperty cnpProperty() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp.set(cnp);
    }

    public String getAddress() {
        return address.get();
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

}