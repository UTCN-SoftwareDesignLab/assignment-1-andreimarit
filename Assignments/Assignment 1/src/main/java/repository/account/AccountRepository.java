package repository.account;

import model.Account;
import repository.EntityNotFoundException;

import java.util.List;

public interface AccountRepository {

    List<Account> findAll();

    Account findById(int id) throws EntityNotFoundException;

    Account findByClientId(Long clientId) throws EntityNotFoundException;

    boolean save(Account account);

    void removeAll();

    boolean remove(int id);

    boolean update(Account account);

    void transferMoney(Account account1, Account account2, Integer amount) throws EntityNotFoundException;
}
