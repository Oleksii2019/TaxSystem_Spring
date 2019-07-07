package ua.testing.registration_form.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.testing.registration_form.entity.Taxpayer;

/**
 *  Создвн для работы с БД
 */
public interface TaxpayerRepository extends JpaRepository<Taxpayer, Long> {

}
