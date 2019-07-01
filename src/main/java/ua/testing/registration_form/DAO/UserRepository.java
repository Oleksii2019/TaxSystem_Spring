package ua.testing.registration_form.DAO;

import org.springframework.data.repository.CrudRepository;
import ua.testing.registration_form.entity.User;

/**
 *  Создвн для работы с БД
 */
public interface UserRepository extends CrudRepository <User, Long> {

}
