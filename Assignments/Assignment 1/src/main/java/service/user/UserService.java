package service.user;

import DTO.UserDTO;
import model.User;
import repository.EntityNotFoundException;
import repository.user.UserRepository;

import java.util.ArrayList;

public interface UserService {


    public ArrayList<UserDTO> getAllUsers();

    public boolean deleteUserById(int id) throws EntityNotFoundException;

    public boolean saveUser(User user);

    public boolean updateUser(User user);
}
