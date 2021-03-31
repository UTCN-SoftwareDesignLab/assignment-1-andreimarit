package repository.client;

import model.Client;
import model.builder.ClientBuilder;
import repository.EntityNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientRepositoryImplementation implements ClientRepository{

    private final Connection connection;
    
    public ClientRepositoryImplementation(Connection connection){ this.connection=connection;}
    
    
    @Override
    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM client";
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                clients.add(getClientFromResultSet(rs));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return clients;
    }

    private Client getClientFromResultSet(ResultSet rs) throws SQLException {
        return new ClientBuilder()
                .setName(rs.getString("name"))
                .setIdentityNumber(rs.getString("identityNumber"))
                .setAddress(rs.getString("address"))
                .setCnp(rs.getString("cnp"))
                .build();
    }

    @Override
    public Client findById(Long id) throws EntityNotFoundException {
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM client WHERE id=" + id;
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                return getClientFromResultSet(rs);
            } else {
                throw new EntityNotFoundException(id, Client.class.getSimpleName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotFoundException(id, Client.class.getSimpleName());
        }
    }

    @Override
    public boolean save(Client client) {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("INSERT INTO client values (null, ?, ?, ?, ?)");
            insertStatement.setString(1, client.getName());
            insertStatement.setString(2, client.getIdentityNumber());
            insertStatement.setString(3, client.getCnp());
            insertStatement.setString(4, client.getAddress());

            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Client client) {
        try {
            PreparedStatement updateClientStatement = connection
                    .prepareStatement("UPDATE client SET name = ?, identityNumber = ?, cnp = ?, address = ? WHERE id = " + client.getId());
            updateClientStatement.setString(1, client.getName());
            updateClientStatement.setString(2, client.getIdentityNumber());
            updateClientStatement.setString(3, client.getCnp());
            updateClientStatement.setString(4, client.getAddress());
            updateClientStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
