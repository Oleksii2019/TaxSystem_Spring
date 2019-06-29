package ua.testing.registration_form.entity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Slf4j
@Repository
public class Users extends User {
    private static ArrayList<User> users;

    public Users () {
        users = new ArrayList<>();
    }


    public ArrayList<User> getUsers() {
        return users;
    }
    
    public boolean checkUser(User user) {
        return !users.contains(user);
    }


}
