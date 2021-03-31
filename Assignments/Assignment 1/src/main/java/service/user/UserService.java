package service.user;

import DTO.UserDTO;
import repository.user.UserRepository;

import java.util.ArrayList;

public interface UserService {


    public ArrayList<UserDTO> getAllUsers();


}
