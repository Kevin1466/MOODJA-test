package repository;

import model.Draft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Handle database operations for draft.
 */
public interface DraftRepository extends JpaRepository<Draft, Integer> {


}
