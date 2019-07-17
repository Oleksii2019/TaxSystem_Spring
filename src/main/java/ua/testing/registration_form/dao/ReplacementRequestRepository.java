package ua.testing.registration_form.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.testing.registration_form.entity.ReplacementRequest;

public interface ReplacementRequestRepository extends JpaRepository<ReplacementRequest, Long> {

}
