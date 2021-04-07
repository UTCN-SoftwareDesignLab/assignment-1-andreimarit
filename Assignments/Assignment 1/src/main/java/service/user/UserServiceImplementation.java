package service.user;

import DTO.UserDTO;
import model.User;
import repository.EntityNotFoundException;
import repository.user.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImplementation implements UserService{

    private final UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository){this.userRepository=userRepository;}


    @Override
    public ArrayList<UserDTO> getAllUsers() {
        ArrayList<UserDTO> usersDTO = new ArrayList<>();
        List<User> users = userRepository.findAll();
        System.out.println("SERVICEE           " + users);

        for(User u : users){
            System.out.println("for in service: "+ u.getId()+ "  "+ u.getUsername()+"  "+ u.getRole());
            usersDTO.add(new UserDTO(u));
        }

        return usersDTO;
    }

    public boolean deleteUserById(int id) throws EntityNotFoundException {
        return userRepository.delete(id);
    }

    public boolean saveUser(User user){
        return userRepository.save(user);
    }

    public boolean updateUser(User user){
        return userRepository.update(user);

    }
}
