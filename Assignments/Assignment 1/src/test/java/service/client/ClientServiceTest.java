package service.client;

import DTO.AccountDTO;
import DTO.ClientDTO;
import launcher.ComponentFactory;
import model.Account;
import model.Client;
import model.builder.AccountBuilder;
import model.builder.ClientBuilder;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.EntityNotFoundException;

import java.util.List;

public class ClientServiceTest {


    private static ClientService clientService;

    @BeforeClass
    public static void setUp() {
        ComponentFactory componentFactory = ComponentFactory.instance(true);
        clientService = componentFactory.getClientService();
    }

    @Test
    public void testGetAllClients() {
        List<ClientDTO> all = clientService.getAllClients();
        Assert.assertFalse(all.isEmpty());
    }

    @Test
    public void testSaveClient(){
        boolean saved = clientService.saveClient(new ClientBuilder().setName("and").setIdentityNumber("12312312").setCnp("1234568751").setAddress("alba").build());
        Assert.assertTrue(saved);
    }

    @Test
    public void testUpdate() {
        Client client = new ClientBuilder().setName("asdfasdf").setIdentityNumber("345231").setCnp("12343525234568751").setAddress("alba").build();
        client.setName("changed");
        boolean updated = clientService.updateClient(client);
        Assert.assertFalse(updated);
    }
}