package ua.testing.registration_form.DAO;

import org.springframework.data.repository.CrudRepository;
import ua.testing.registration_form.entity.Taxpayer;

/**
 *  Создвн для работы с БД
 */
public interface TaxpayerRepository extends CrudRepository <Taxpayer, Long> {

}
