package service.client;

import DTO.ClientDTO;
import DTO.UserDTO;
import model.Client;
import model.User;

import java.util.ArrayList;

public interface ClientService {

    public ArrayList<ClientDTO> getAllClients();

    public boolean saveClient(Client client);

    public boolean updateClient(Client client);

}
