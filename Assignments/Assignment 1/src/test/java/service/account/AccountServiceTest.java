package service.account;

import DTO.AccountDTO;
import database.DBConnectionFactory;
import database.JDBConnectionWrapper;
import launcher.ComponentFactory;
import model.Account;
import model.builder.AccountBuilder;
import model.validator.Notification;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.EntityNotFoundException;
import repository.account.AccountRepositoryImplementation;

import java.util.List;

public class AccountServiceTest {

    public static final String TEST_USERNAME= "username@bank.com";
    public static final String TEST_PASSWORD= "password123!";

    private static AccountService accountService;

    @BeforeClass
    public static void setup() {
        ComponentFactory componentFactory = ComponentFactory.instance(true);
        accountService = componentFactory.getAccountService();

    }

    @Test
    public void testGetAllAccounts() {
        List<AccountDTO> all = accountService.getAllAccounts();
        Assert.assertFalse(all.isEmpty());
    }

    @Test
    public void testFindById() throws EntityNotFoundException {
        List<AccountDTO> accounts = accountService.getAllAccounts();
        int currentIndex = accounts.get(accounts.size() - 1).getId();

        Account account = new AccountBuilder().setIdentificationNumber("1234").setClientId(1).setType("Visa").setAmount(1000).setCreationDate("2021-04-04").build();
        accountService.saveAccount(account);

        Assert.assertNotNull(accountService.findAccount(currentIndex));
    }

    @Test
    public void testSave() {
        boolean saved = accountService.saveAccount(new AccountBuilder().setIdentificationNumber("2345").setClientId(1).setType("Visa").setAmount(1000).setCreationDate("2021-04-04").build());
        Assert.assertTrue(saved);
    }

    @Test
    public void testDelete() {
        Account account = new AccountBuilder().setIdentificationNumber("3456").setClientId(1).setType("Visa").setAmount(1000).setCreationDate("2021-04-04").build();
        accountService.saveAccount(account);
        boolean deleted = accountService.removeAccount(account.getId());
        Assert.assertTrue(deleted);
    }

    @Test
    public void testUpdate() {
        Account account = new AccountBuilder().setIdentificationNumber("1212").setClientId(1).setType("Visa").setAmount(1000).setCreationDate("2021-04-04").build();
        account.setType("Mastercard");
        boolean updated = accountService.updateAccount(account);
        Assert.assertTrue(updated);

    }


}
