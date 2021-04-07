package service.user;

import DTO.AccountDTO;
import DTO.UserDTO;
import launcher.ComponentFactory;
import model.Account;
import model.User;
import model.builder.AccountBuilder;
import model.builder.UserBuilder;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.EntityNotFoundException;

import java.util.List;

public class UserServiceTest {

    public static final String TEST_USERNAME= "username@bank.com";
    public static final String TEST_PASSWORD= "password123!";

    private static UserService userService;

    @BeforeClass
    public static void setUp() {
        ComponentFactory componentFactory = ComponentFactory.instance(true);
        userService = componentFactory.getUserService();
    }

    @Test
    public void testGetAllUsers() {
        List<UserDTO> all = userService.getAllUsers();
        Assert.assertFalse(all.isEmpty());
    }

    @Test
    public void testSaveUser(){
        boolean saved = userService.saveUser(new UserBuilder().setUsername(TEST_USERNAME).setPassword(TEST_PASSWORD).setRole("Employee").build());
        Assert.assertTrue(saved);
    }

    @Test
    public void testUpdate() {
        User user = new UserBuilder().setUsername(TEST_USERNAME).setPassword(TEST_PASSWORD).setRole("Employee").build();
        user.setRole("Admin");
        boolean updated = userService.updateUser(user);
        Assert.assertFalse(updated);
    }


    @Test
    public void testDeleteUserById() throws EntityNotFoundException {
        User user = new UserBuilder().setUsername(TEST_USERNAME).setPassword(TEST_PASSWORD).setRole("Employee").build();
        userService.saveUser(user);
        boolean deleted = userService.deleteUserById(user.getId());
        Assert.assertTrue(deleted);
    }
}
