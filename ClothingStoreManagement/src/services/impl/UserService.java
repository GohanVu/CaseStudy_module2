package services.impl;

import models.User;
import repositories.impl.UserRepository;

public class UserService {
    private UserRepository userRepository = new UserRepository();
    public boolean login (String username, String password){
        User user = userRepository.findByUsername(username);
        if(user == null){
            return false;
        }
        return user.getPassword().equals(password);
    }
}
