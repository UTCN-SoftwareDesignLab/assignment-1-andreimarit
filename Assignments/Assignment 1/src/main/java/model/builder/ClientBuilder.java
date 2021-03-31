package model.builder;

import model.Client;
import model.User;

public class ClientBuilder {

    private Client client;

    public ClientBuilder(){ client = new Client();}

    public ClientBuilder setId(int id){
        client.setId(id);
        return this;
    }

    public ClientBuilder setName(String name){
        client.setName(name);
        return this;
    }

    public ClientBuilder setIdentityNumber(String identityNumber){
        client.setIdentityNumber(identityNumber);
        return this;
    }

    public ClientBuilder setCnp(String cnp){
        client.setCnp(cnp);
        return this;
    }

    public ClientBuilder setAddress(String address){
        client.setAddress(address);
        return this;
    }

    public Client build() {return client;}
}
