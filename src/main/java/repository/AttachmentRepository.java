package repository;

import model.Attachment;
import model.Document;
import model.Draft;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

/**
 * Handle the database operations for attachments.
 */
public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {

    /**
     * Find the attachments of a document.
     * @param document
     * @return
     */
    Set<Attachment> findAllByDocument(Document document);

    /**
     * Find attachments of a draft.
     * @return
     * @param draft
     */
    Set<Attachment> findAllByDraft(Draft draft);
}
