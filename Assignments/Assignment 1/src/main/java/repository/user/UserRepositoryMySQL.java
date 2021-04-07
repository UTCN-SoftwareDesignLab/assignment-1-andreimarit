package repository.user;

import model.User;
import model.builder.UserBuilder;
import model.validator.Notification;
import repository.EntityNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static database.Constants.Tables.USER;

/**
 * Created by Alex on 11/03/2017.
 */
public class UserRepositoryMySQL implements UserRepository {

    private final Connection connection;


    public UserRepositoryMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> findAll() {

        List<User> users = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user");
            while(resultSet.next()){
                users.add(getUserFromResultSet(resultSet));
                System.out.println("REPOSITORY:        "+getUserFromResultSet(resultSet).getRole() + "     "+ getUserFromResultSet(resultSet).getUsername());
            }
        } catch (SQLException e){

        }
        return users;

    }

    @Override
    public User findById(int id) throws EntityNotFoundException{
        try{
            Statement statement = connection.createStatement();
            String fetchUserSql = "SELECT * FROM user WHERE id = " + id;
            ResultSet userResultSet = statement.executeQuery(fetchUserSql);
            if(userResultSet.next()){
                return new UserBuilder()
                        .setId(userResultSet.getInt("id"))
                        .setUsername(userResultSet.getString("username"))
                        .setPassword(userResultSet.getString("password"))
                        .build();
            }else {
                throw new EntityNotFoundException((long) id, User.class.getSimpleName());
            }
        }catch(SQLException | EntityNotFoundException e){
            e.printStackTrace();
            throw new EntityNotFoundException((long) id, User.class.getSimpleName());
        }
    }


    @Override
    public Notification<User> findByUsernameAndPassword(String username, String password) {
        Notification<User> findByUsernameAndPasswordNotification = new Notification<>();
        try {
            Statement statement = connection.createStatement();
            String fetchUserSql = "Select * from `" + USER + "` where `username`=\'" + username + "\' and `password`=\'" + password + "\'";
            ResultSet userResultSet = statement.executeQuery(fetchUserSql);
            if (userResultSet.next()) {
                User user = new UserBuilder()
                        .setId(userResultSet.getInt("id"))
                        .setUsername(userResultSet.getString("username"))
                        .setPassword(userResultSet.getString("password"))
                        .setRole(userResultSet.getString("role"))
                        .build();
                findByUsernameAndPasswordNotification.setResult(user);
                return findByUsernameAndPasswordNotification;
            } else {
                findByUsernameAndPasswordNotification.addError("Invalid email or password!");
                return findByUsernameAndPasswordNotification;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            findByUsernameAndPasswordNotification.addError("Something is wrong with the Database");
        }
        return findByUsernameAndPasswordNotification;
    }

    @Override
    public boolean save(User user) {
        try {

            PreparedStatement insertUserStatement = connection
                    .prepareStatement("INSERT INTO user values (null, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            insertUserStatement.setString(1, user.getUsername());
            insertUserStatement.setString(2, user.getPassword());
            insertUserStatement.setString(3, user.getRole());

            insertUserStatement.executeUpdate();

            ResultSet rs = insertUserStatement.getGeneratedKeys();
            rs.next();
            int userId = rs.getInt(1);
            user.setId(userId);

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public void removeAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from user where id >= 0";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private User getUserFromResultSet(ResultSet rs) throws SQLException{
        return new UserBuilder()
                .setId(rs.getInt("id"))
                .setUsername(rs.getString("username"))
                .setPassword(rs.getString("password"))
                .setRole(rs.getString("role"))
                .build();
    }

    public boolean delete(int id) throws EntityNotFoundException {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from user where id = " + id;
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(User user){
        try {
            PreparedStatement insertUserStatement = connection
                    .prepareStatement("UPDATE user SET username = ?, password = ?, role = ? WHERE id = " + user.getId());

            insertUserStatement.setString(1, user.getUsername());
            insertUserStatement.setString(2, user.getPassword());
            insertUserStatement.setString(3, user.getPassword());
            insertUserStatement.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
