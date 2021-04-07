package repository.user;

import model.User;
import model.validator.Notification;
import repository.EntityNotFoundException;

import java.util.List;

/**
 * Created by Alex on 11/03/2017.
 */
public interface UserRepository {

    List<User> findAll();

    public User findById(int id) throws EntityNotFoundException;

    Notification<User> findByUsernameAndPassword(String username, String password);

    boolean save(User user);

    void removeAll();

    public boolean delete(int id) throws EntityNotFoundException;

    public boolean update(User user);
}
