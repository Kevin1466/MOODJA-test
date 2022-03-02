package service;

import model.Attachment;
import model.Document;
import model.Draft;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.AttachmentRepository;
import repository.DocumentRepository;

import java.io.File;
import java.util.Set;

/**
 * Service for operations about attachments.
 */
@Service
public class AttachmentService {

    private static Logger logger = LogManager.getLogger(AttachmentService.class);

    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    DocumentRepository documentRepository;

    /**
     * Get attachments for a specific document.
     * @param document
     * @return
     */
    public Set<Attachment> getAttachments(Document document) {
        return attachmentRepository.findAllByDocument(document);
    }

    /**
     * Get attachments for a specific draft.
     * @param draft
     * @return
     */
    public Set<Attachment> getAttachments(Draft draft) {
        return attachmentRepository.findAllByDraft(draft);
    }

    /**
     * Delete all attachments for a document.
     * @param id
     * @param attachmentPath
     */
    public void deleteAttachmentsByDocument(Integer id, String attachmentPath) {
        Set<Attachment> attachmentSet = attachmentRepository.findAllByDocument(documentRepository.findById(id).orElse(null));
        if (attachmentSet != null && attachmentSet.size() != 0) {
            for (Attachment attachment : attachmentSet) {
                delete(attachment, attachmentPath);
            }
        }
    }

    /**
     * Delete an attachment file and delete the record in the database.
     * @param attachment
     * @param path
     */
    private void delete(Attachment attachment, String path) {
        if (attachment == null) return;
        File file = new File(path + attachment.getName());
        if (file.delete()) {
            logger.info("Deleted draft No: " + attachment.getId());
            attachmentRepository.delete(attachment);
        }
    }

    /**
     * Delete all attachments for a draft.
     * @param draft
     * @param attachmentPath
     */
    public void deleteAttachmentByDraft(Draft draft, String attachmentPath) {
        Set<Attachment> attachmentSet = attachmentRepository.findAllByDraft(draft);
        if (attachmentSet != null && attachmentSet.size() != 0) {
            for (Attachment attachment : attachmentSet) {
                delete(attachment, attachmentPath);
            }
        }
    }

    /**
     * Delete attachments by ID.
     * @param ids
     * @param path
     */
    public void deleteAttachment(Integer[] ids, String path) {
        if (ids == null) return;
        for (Integer id : ids) {
            if (id != null) {
                Attachment attachment = get(id);
                delete(attachment, path);
            }
        }
    }

    /**
     * Get attachment by ID.
     * @param id
     * @return
     */
    private Attachment get(Integer id) {
        return attachmentRepository.findById(id).orElse(null);
    }

    /**
     * Remove the draft attachment from documents.
     * @param draft
     * @param document
     */
    public void moveAttachment(Draft draft, Document document) {
        Set<Attachment> attachments = getAttachments(draft);
        for (Attachment attachment : attachments) {
            attachment.setDraft(null);
            attachment.setDocument(document);
        }
        attachmentRepository.saveAll(attachments);
    }
}
