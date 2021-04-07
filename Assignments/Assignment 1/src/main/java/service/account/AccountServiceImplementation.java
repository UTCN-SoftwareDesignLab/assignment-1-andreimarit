package service.account;

import DTO.AccountDTO;
import DTO.ClientDTO;
import model.Account;
import model.Client;
import repository.EntityNotFoundException;
import repository.account.AccountRepository;
import repository.client.ClientRepository;

import java.util.ArrayList;
import java.util.List;

public class AccountServiceImplementation implements AccountService{

    private final AccountRepository accountRepository;

    public AccountServiceImplementation(AccountRepository accountRepository){this.accountRepository=accountRepository;}


    @Override
    public ArrayList<AccountDTO> getAllAccounts() {
        ArrayList<AccountDTO> accountsDTO = new ArrayList<>();
        List<Account> accounts = accountRepository.findAll();

        for(Account a : accounts){
            accountsDTO.add(new AccountDTO(a));
        }

        return accountsDTO;
    }

    @Override
    public boolean saveAccount(Account account){
        return accountRepository.save(account);
    }

    @Override
    public boolean removeAccount(int id){ return accountRepository.remove(id);}

    @Override
    public Account findAccount(int id) throws EntityNotFoundException { return accountRepository.findById(id);}

    @Override
    public boolean updateAccount(Account account){ return accountRepository.update(account);}
}
