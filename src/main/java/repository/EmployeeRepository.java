package repository;

import model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * This interface deal with the database operations in the employee table.
 */
//@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    /**
     * Find the employee with given id and password in the database.
     * @param id
     * @param password
     * @return
     */
    Employee findByIdAndPassword(Integer id, String password);

    /**
     * Find all exist employee.
     * @return
     */
    //List<Employee> findAllByDeleteFalse();
}
