package service;

import exception.HasUnhandledWorkException;
import exception.LoginFailException;
import model.Administrator;
import model.Document;
import model.Manager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.DocumentRepository;
import repository.ManagerRepository;
import exception.EmailExistsException;

import java.util.Date;
import java.util.List;
import java.util.Set;

import static util.EncryptUtil.encrypt;

@Transactional
@Service
public class ManagerService {

    private static Logger logger = LogManager.getLogger(ManagerService.class);

    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    DocumentRepository documentRepository;

    EmailService emailService;

    /**
     * Login method for manager.
     * @param email
     * @param password
     * @return
     */
    public Manager login(String email, String password) throws LoginFailException {
        Manager manager = managerRepository.findByEmailAndPasswordAndDeleteFalse(email, password);
        if (manager == null) throw new LoginFailException();
        return manager;

        //return managerRepository.findByEmailAndPasswordAndDeleteFalse(email, encrypt(email + password));
    }

    public Manager getEmail(String email) {
        return managerRepository.findByEmailAndDeleteFalse(email);
    }

    /**
     * Get manager by ID.
     * @param id
     * @return
     */
    public Manager get(Integer id) {
        return managerRepository.findById(id).orElse(null);
    }

    public Manager update(Integer id, String email, String mobile, String firstname, String lastname) throws EmailExistsException {
        Manager duplicate = managerRepository.findByEmailAndDeleteFalse(email);

        if (duplicate.getId() != id) throw new EmailExistsException("Update", email);

        Manager manager = get(id);

        if (!manager.getEmail().equals(email)) {
            manager.setEmail(email);
            manager.setPassword(encrypt(email + manager.getPassword()));
        }

        manager.setMobile(mobile);
        manager.setFirstName(firstname);
        manager.setLastName(lastname);

        managerRepository.saveAndFlush(manager);

        logger.info("Updated Manager No. " + id);

        return manager;
    }

    /**
     * Get all exist manager.
     * @return
     */
    public List<Manager> getAll() {
        return managerRepository.findAllByDeleteFalse();
    }

    /**
     * Reset manager's password.
     * @param id
     * @param password
     * @return
     */
    public Manager resetPassword(Integer id, String password) {
        Manager manager = get(id);
        manager.setPassword(manager.getEmail() +  password);
        managerRepository.save(manager);
        return manager;
    }

    /**
     * Add new manager.
     */
    public void addManager(String email, String password, String firstname, String lastname, String mobile, Administrator admin) throws EmailExistsException {
        if (managerRepository.findByEmailAndDeleteFalse(email) != null) throw new EmailExistsException("Add", email);

        Manager manager = new Manager(firstname, lastname, email, mobile, encrypt(email + password));

        manager.setAdminByAdminId(admin);

        manager = managerRepository.save(manager);

        logger.info("Added manager No.1 " + manager.getId() + " by administrator No. " + admin.getId());
    }

    /**
     * Update Manager Info.
     * @param id
     * @param password
     * @param email
     * @param mobile
     * @param firstname
     * @param lastname
     * @throws EmailExistsException
     */
    public void updateManager(Integer id, String password, String email, String mobile, String firstname, String lastname) throws EmailExistsException {
        Manager duplicate = managerRepository.findByEmailAndDeleteFalse(email);

        if (duplicate.getId() != id) throw new EmailExistsException("Update", email);
        Manager manager = get(id);
        manager.setId(id);
        manager.setEmail(email);

        if (! manager.getPassword().equals(password)) {
            manager.setPassword(encrypt(email + password));
        }

        manager.setMobile(mobile);
        manager.setFirstName(firstname);
        manager.setLastName(lastname);
        managerRepository.saveAndFlush(manager);
        logger.info("Update manager No. " + id + " by administrator");
    }

    /**
     * Delete Manager
     */
    public void deleteManager(Integer managerId) throws HasUnhandledWorkException {
        Manager manager = get(managerId);
        manager.setDelete(true);
        logger.info("Deleted manager No. " + manager.getId());
        managerRepository.save(manager);
    }

    /**
     * Get a manager for the document.
     * @return
     */
    private Manager getManager() {
        List<Manager> managers = managerRepository.findAllByDeleteFalse();
        int index = (int)(Math.random()*managers.size());
        return managers.get(index);
    }

    /**
     * Assign a document to a manager.
     * @param document
     * @return
     */
    public Manager AssignDocument(Document document) {
        if (document == null) {
            return null;
        }
        Manager manager = getManager();
        document.setManager(manager);
        document = documentRepository.save(document);
        logger.info("Document No. " + document.getId() + " assigned to: " + manager.toString());
        emailService.notifyManager(document);
        return manager;
    }

    /**
     * Get all unhandled documents for manager.
     * @param manager
     * @return
     */
    public Set<Document> getUnhandledDoc(Manager manager) {
        logger.info("Get unhandled document for manager: " + manager.getId());
        return documentRepository.getDocumentByManagerIdAndViewDateIsNull(manager.getId());
    }

    /**
     * Find a specific document.
     * @param id
     * @return
     */
    public Document getDoc(Integer id) {
        return documentRepository.findById(id).orElse(null);
    }

    /**
     * The document is processing.
     * @param document
     * @param result
     */
    public void manageDoc(Document document, String result) {
        document.setStatus(result);
        documentRepository.save(document);
        logger.info("Document No. " + document.getId() + " . " + result);
    }

    /**
     * Approve or Dennie the document.
     * @param docId
     * @param result
     * @param date
     * @param comment
     */
    public void manageDoc(Integer docId, String result, Date date, String comment) {
        Document document = getDoc(docId);
        document.setViewDate(date);
        document.addComment(comment);
        manageDoc(document, result);
        logger.info("Document No. " + document.getId() + " . " + result);
    }
}
