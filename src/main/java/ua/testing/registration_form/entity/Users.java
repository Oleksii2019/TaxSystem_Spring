package ua.testing.registration_form.entity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.testing.registration_form.DAO.UserRepository;

import java.util.ArrayList;

@Slf4j
@Repository
public class Users extends User {
    private static ArrayList<User> users;

    @Autowired
    UserRepository ur;


    public Users () {
        users = new ArrayList<>();
    }


    public ArrayList<User> getUsers() {
        return users;
    }
    
    public boolean checkUser(User user) {
        for (int i = 0; i < users.size(); i++) {
            if (user.getName().equals(users.get(i).getName())
                    && user.getLogin().equals(users.get(i).getLogin())) {
                return false;
            }
        }
        return true;
    }


}
