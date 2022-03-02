package repository;

import model.Administrator;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Handles the database operations of table admin.
 */
//@Repository
public interface AdminRepository extends JpaRepository<Administrator, Integer> {

    /**
     * Search for admin record with give username and password in the database.
     * @param username
     * @param password
     * @return
     */
    Administrator findByUsernameAndPassword(String username, String password);
}
