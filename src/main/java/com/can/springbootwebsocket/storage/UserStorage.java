package com.can.springbootwebsocket.storage;

import java.util.HashSet;
import java.util.Set;

public class UserStorage {

    private static UserStorage instance;
    private Set<String> users;

    private UserStorage() {
        users = new HashSet<>();
    }

    public static synchronized UserStorage getInstance() {
        if (instance == null) {
            instance = new UserStorage();
        }
        return instance;
    }

    public Set<String> getUsers() {
        return users;
    }

    public void setUser(String userName) throws Exception {
        if (users.contains(userName)) {
            throw new Exception("Bu isimde bir kullanıcı zaten var : " + userName);
        }
        users.add(userName);
    }
}
