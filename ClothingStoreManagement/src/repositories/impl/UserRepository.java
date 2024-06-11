package repositories.impl;

import models.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private Map<String, User> users = new HashMap<>();

    public UserRepository() {
        users.put("admin",new User("admin","1811"));
    }
    public User findByUsername (String username){
        return users.get(username);
    }

}
