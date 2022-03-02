package repository;

import model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

/**
 * Handle the database operations for documents.
 */
public interface DocumentRepository extends JpaRepository<Document, Integer> {

    /**
     * Get documents accoridng to employee id.
     * @param employeeId
     * @return
     */
    Set<Document> getDocumentByEmployeeId(Integer employeeId);

    /**
     * Get unfinished documents for employee.
     * @param employeeId
     * @return
     */
    Set<Document> getDocumentByEmployeeIdAndViewDateIsNull(Integer employeeId);

    /**
     * Get finished documents for employee.
     * @param employeeId
     * @return
     */
    Set<Document> getDocumentByEmployeeIdAndViewDateIsNotNull(Integer employeeId);

    /**
     * Get finished documents with title keyword for employee.
     * @param employeeId
     * @param title
     * @return
     */
    Set<Document> getDocumentByEmployeeIdAndViewDateIsNotNullAndTitleContaining(Integer employeeId, String title);

    /**
     * Get documents which has given manager ID.
     * @param managerId
     * @return
     */
    Set<Document> getDocumentByManagerIdAndViewDateIsNull(Integer managerId);

    /**
     * Get the documents according to employee ID and status.
     * @param employeeId
     * @param status
     * @return
     */
    Set<Document> getAllByEmployeeIdAndStatus(Integer employeeId, String status);

    /**
     * Search for documents according to employee ID and date range.
     * @param employeeId
     * @param timeStr
     * @return
     */

    @Query(value = "SELECT * FROM MOODJA.document a where a.employee_id =?1 AND a.view_date IS NOT NULL AND a.submit_date IS NOT NULL", nativeQuery = true)
    Set<Document> getDocumentByEmployeeIdAndViewDateIsNotNullAndSubmitDate(Integer employeeId, String timeStr);
}
