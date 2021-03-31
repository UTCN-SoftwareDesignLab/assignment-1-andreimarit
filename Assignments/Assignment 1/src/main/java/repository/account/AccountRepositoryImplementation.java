package repository.account;

import model.Account;
import model.builder.AccountBuilder;
import repository.EntityNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountRepositoryImplementation implements AccountRepository{

    private final Connection connection;

    public AccountRepositoryImplementation(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Account> findAll() {
        List<Account> accounts = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from account";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                accounts.add(getAccountFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accounts;
    }

    private Account getAccountFromResultSet(ResultSet rs) throws SQLException {
        return new AccountBuilder()
                .setId(rs.getInt("id"))
                .setIdentificationNumber(rs.getString("identificationNumber"))
                .setType(rs.getString("type"))
                .setAmount(rs.getInt("amount"))
                .setClientId(rs.getInt("client_id"))
                .setCreationDate(rs.getString("creationDate"))
                .build();
    }

    @Override
    public Account findById(int id) throws EntityNotFoundException {
        Account account = new Account();

        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from account where id = " + id;
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                account = getAccountFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotFoundException((long) id, Account.class.getSimpleName());
        }

        return account;
    }

    @Override
    public Account findByClientId(Long clientId) throws EntityNotFoundException {
        return null;
    }

    @Override
    public boolean save(Account account) {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("INSERT INTO account values (null, ?, ?, ?, ?, ?)");
            insertStatement.setString(1, account.getIdentificationNumber());
            insertStatement.setString(2, account.getType());
            insertStatement.setInt(3, account.getAmount());
            insertStatement.setInt(4, account.getClient_id());
            insertStatement.setString(5, account.getCreationDate());
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void removeAll() {

    }

    @Override
    public boolean remove(int id) {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from account where id = " + id;
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Account account) {
        try {
            PreparedStatement updateStatement = connection
                    .prepareStatement("UPDATE account SET identificationNumber = ?, type = ?, amount = ?, client_id = ?, creationDate = ? WHERE id = " + account.getId());
            updateStatement.setString(1, account.getIdentificationNumber());
            updateStatement.setString(2, account.getType());
            updateStatement.setInt(3, account.getAmount());
            updateStatement.setInt(4, account.getClient_id());
            updateStatement.setString(5, account.getCreationDate());
            updateStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void transferMoney(Account donorAccount, Account recieveAccount, Integer amount) throws EntityNotFoundException {
            donorAccount.setAmount(donorAccount.getAmount()-amount);
            recieveAccount.setAmount((recieveAccount.getAmount()+amount));

            update(donorAccount); update(recieveAccount);

    }
}
