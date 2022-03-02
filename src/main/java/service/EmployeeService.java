package service;

import exception.AttachFailException;
import exception.LoginFailException;
import model.Attachment;
import model.Document;
import model.Draft;
import model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import repository.AttachmentRepository;
import repository.DocumentRepository;
import repository.DraftRepository;
import repository.EmployeeRepository;
import util.EncryptUtil;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static model.Document.SUBMITTED;

/**
 * This service deal with the operations that an employee might conduct.
 */
//@Transactional
@Service("employeeService")
public class EmployeeService {
    private static Logger logger = LogManager.getLogger(EmployeeService.class);

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DocumentRepository documentRepository;

    @Autowired
    DraftRepository draftRepository;

    @Autowired
    AttachmentRepository attachmentRepository;

    EmailService emailService;

    /**
     * Search in the database for the employee authentication details.
     * Weather there is an employee match the employee ID and password.
     * @param employeeId
     * @param password
     * @return
     */
    public Employee login(Integer employeeId, String password) throws LoginFailException {
        Employee employee = employeeRepository.findByIdAndPassword(employeeId, password);
        if (employee == null) throw new LoginFailException();
        return employee;
        //return employeeRepository.findByIdAndPassword(employeeId, EncryptUtil.encrypt(employeeId + password));
    }

    /*
    public Employee login(String employeeId, String password) {
        Integer id = Integer.parseInt(employeeId);
        return login(id, password);
    }

     */

    /**
     * Get document for the specific employee.
     * @param id
     * @return
     */
    public Document getDocument(Integer id) {
        return documentRepository.findById(id).orElse(null);
    }

    /**
     * Get documents for the specific employee.
     * @param employee
     * @return
     */
    public Set<Document> getDocument(Employee employee) {
        return documentRepository.getDocumentByEmployeeId(employee.getId());
    }

    /**
     * Get unfinished documents.
     * @param employee
     * @return
     */
    public Set<Document> getUnfinishedDocument(Employee employee) {
        return documentRepository.getDocumentByEmployeeIdAndViewDateIsNull(employee.getId());
    }

    /**
     * Get finished documents.
     * @param employee
     * @return
     */
    public Set<Document> getHistoryDocument(Employee employee) {
        return documentRepository.getDocumentByEmployeeIdAndViewDateIsNotNull(employee.getId());
    }

    /**
     * Get the employee by ID
     * @param id
     * @return
     */
    public Employee get(Integer id) {
        return  employeeRepository.findById(id).get();
    }

    /**
     * Update employee account info.
     * @param id
     * @param email
     * @param bankInfo
     * @param mobile
     * @param position
     * @param department
     * @return
     */
    public Employee updateEmployee(Integer id, String email, String bankInfo, String mobile, String position, String department) {
        Employee employee = get(id);
        employee.setEmail(email);
        employee.setBankInfo(bankInfo);
        employee.setMobile(mobile);
        employee.setPosition(position);
        employee.setDepartment(department);
        employeeRepository.saveAndFlush(employee);
        return employee;
    }

    /**
     * Reset employee's password.
     * @param id
     * @param password
     * @return
     */
    public Employee resetPassword(Integer id, String password) {
        Employee employee = get(id);
        employee.setPassword(id + password);
        employeeRepository.save(employee);
        return employee;
    }

    /**
     * Get employee's document by status.
     * @param employee
     * @param status
     * @return
     */
    public Set<Document> getDocumentByStatus(Employee employee, String status) {
        return documentRepository.getAllByEmployeeIdAndStatus(employee.getId(), status);
    }

    /**
     * Get documents history by date.
     * @param employee
     * @param timeStr
     * @return
     */
    public Set<Document> searchByDate(Employee employee, String timeStr) {
        Set<Document> documents = documentRepository.getDocumentByEmployeeIdAndViewDateIsNotNullAndSubmitDate(employee.getId(), timeStr);
        logger.info("Search document by date: " + timeStr + ", result: " + documents.size());
        return documents;
    }

    /**
     * Get documents history by title.
     * @param employee
     * @param title
     * @return
     */
    public Set<Document> searchByTitle(Employee employee, String title) {
        Set<Document> documents = documentRepository.getDocumentByEmployeeIdAndViewDateIsNotNullAndTitleContaining(employee.getId(), title);
        logger.info("Search document by title: " + title + ", result: " + documents.size());
        return documents;
    }

    /**
     * Function for save draft.
     * @param employee
     * @param draft
     * @param attachments
     * @param path
     * @return
     */
    public Draft saveDraft(Employee employee, Draft draft, MultipartFile[] attachments, String path) {
        if (draft.getId() == null && employee.getDraft() != null) draft.setId(employee.getDraft().getId());
        Draft saveDraft = draftRepository.save(draft);
        employee.setDraft(saveDraft);
        employeeRepository.save(employee);
        try {
            addAttachments(attachments, path, null, draft, employee);
        } catch (AttachFailException attachFailException) {
            attachFailException.printStackTrace();
        }
        logger.info("Draft saved: " + draft.toString());
        return saveDraft;
    }

    /**
     * Function for delete draft.
     * @param draft
     */
    public void deleteDraft(Draft draft) {
        Employee employee = draft.getEmployee();
        employee.setDraft(null);
        employeeRepository.save(employee);
        logger.info("Draft deleted: " + draft.toString());
        draftRepository.delete(draft.getEmployee().getDraft());
    }

    /**
     * Get draft by draft ID.
     * @param draftId
     * @return
     */
    public Draft getDraft(Integer draftId) {
        return draftRepository.findById(draftId).orElse(null);
    }

    /**
     * Delete draft by draft ID.
     * @param id
     */
    public void deleteDraft(Integer id) {
        deleteDraft(getDraft(id));
        logger.info("Draft deleted: " + id);
    }

    public void responseDocument(Integer id, String result, String content, MultipartFile[] attachments, String uploadPath) throws AttachFailException {
        Document document = getDocument(id);
        document.setContent(content);
        document.setStatus(result);
        document = documentRepository.save(document);
        addAttachments(attachments, uploadPath, document, null, document.getEmployee());
    }

    /**
     * Add attachments to the database and save the file.
     * @param attachments
     * @param uploadPath
     * @param document
     * @param draft
     * @param employee
     * @throws AttachFailException
     */
    private void addAttachments(MultipartFile[] attachments, String uploadPath, Document document, Draft draft, Employee employee) throws AttachFailException{
        if (attachments != null && attachments.length != 0) {
            for (MultipartFile file : attachments) {
                if (file.isEmpty()) continue;
                try {
                    String name = employee.getId() + new Date().toString() + file.getOriginalFilename();
                    file.transferTo(new File(uploadPath + name));
                    Attachment attachment = new Attachment(uploadPath, document, draft, new Date(), name);
                    attachmentRepository.save(attachment);
                } catch (IOException exception) {
                    throw new AttachFailException();
                }
            }
        }
    }

    /**
     * Submit new document to the database.
     * @param document
     * @param attachments
     * @param uploadPath
     * @return
     * @throws AttachFailException
     */
    public Document submitDocument(Document document, MultipartFile[] attachments, String uploadPath) throws AttachFailException {
        getDocumentByStatus(document.getEmployee(), SUBMITTED);
        document = documentRepository.save(document);
        addAttachments(attachments, uploadPath, document, null, document.getEmployee());
        emailService.notifyEmployee(document);
        return document;
    }

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }
}
