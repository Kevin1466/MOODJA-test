package service;

import exception.LoginFailException;
import model.Administrator;
import model.Document;
import model.Manager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.AdminRepository;
import repository.AttachmentRepository;
import repository.DocumentRepository;
import repository.ManagerRepository;

import java.util.List;

import static util.EncryptUtil.encrypt;

/**
 * Service for Administrator
 */
@Transactional
@Service
public class AdminService {
    private static Logger logger = LogManager.getLogger(AdminService.class);

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    DocumentRepository documentRepository;

    @Autowired
    AttachmentRepository attachmentRepository;

    /**
     * The login function of Administrator.
     * @param username
     * @param password
     * @return
     */
    public Administrator login(String username, String password) throws LoginFailException {
        //logger.info("Try to login(Admin): " + username);
        Administrator administrator = adminRepository.findByUsernameAndPassword(username, password);
        if (administrator == null) throw new LoginFailException();
        //return adminRepository.findByUsernameAndPassword(username, encrypt(username + password));
        return administrator;
    }

    public List<Manager> getManager() {
        return managerRepository.findAllByDeleteFalse();
    }

    /**
     * Get administrator by ID.
     * @param id
     * @return
     */
    public Administrator get(Integer id) {
        return adminRepository.findById(id).orElse(null);
    }

    /**
     * Reset the password of administrator.
     * @param password
     * @param id
     * @return
     */
    public Administrator resetPassword(String password, Integer id) {
        Administrator admin = get(id);
        admin.setPassword(admin.getUsername() + password);
        adminRepository.save(admin);
        return admin;
    }

    /**
     * Delete document by ID.
     * @param id
     */
    public void deleteDocument(Integer id) {
        Document document = documentRepository.getById(id);
        logger.info("Document No. " + id + " is deleted");
        documentRepository.deleteById(id);
    }

    /**
     * Get all documents
     * @return
     */
    public List<Document> getDocuments() {
        return documentRepository.findAll();
    }
}
