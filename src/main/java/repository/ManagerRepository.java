package repository;

import model.Manager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This interface handles the data operation of Manager entity.
 */
//@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer> {

    /**
     * Find the corresponding manager records with give email and password.
     * @param email
     * @param password
     * @return
     */
    Manager findByEmailAndPasswordAndDeleteFalse(String email, String password);

    /**
     * Get all exist manager
     * @return
     */
    List<Manager> findAllByDeleteFalse();

    /**
     * Find the manager that has the email.
     * @param email
     * @return
     */
    Manager findByEmailAndDeleteFalse(String email);
}
