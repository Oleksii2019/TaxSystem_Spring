package ua.testing.registration_form.DAO;

import ua.testing.registration_form.entity.User;

public interface UserRepository {
    void save(User user);
}
