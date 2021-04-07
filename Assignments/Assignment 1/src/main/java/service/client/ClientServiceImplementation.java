package service.client;

import DTO.ClientDTO;
import DTO.UserDTO;
import DTO.fxml.ClientFXML;
import model.Client;
import model.User;
import repository.client.ClientRepository;
import repository.user.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class ClientServiceImplementation implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImplementation(ClientRepository clientRepository){this.clientRepository=clientRepository;}

    @Override
    public ArrayList<ClientDTO> getAllClients() {
        ArrayList<ClientDTO> clientsDTO = new ArrayList<>();
        List<Client> clients = clientRepository.findAll();

        for(Client c : clients){
            clientsDTO.add(new ClientDTO(c));
        }

        return clientsDTO;
    }

    @Override
    public boolean saveClient(Client client){
        return clientRepository.save(client);
    }

    @Override
    public boolean updateClient(Client client){
        return clientRepository.update(client);
    }
}
