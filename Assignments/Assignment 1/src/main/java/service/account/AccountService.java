package service.account;

import DTO.AccountDTO;
import model.Account;
import repository.EntityNotFoundException;

import java.util.ArrayList;

public interface AccountService {

    public ArrayList<AccountDTO> getAllAccounts();

    public boolean saveAccount(Account account);

    public boolean removeAccount(int id);

    public Account findAccount(int id) throws EntityNotFoundException;

    public boolean updateAccount(Account account);
}
