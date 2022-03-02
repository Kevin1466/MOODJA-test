package controller;

import exception.AttachFailException;
import model.Document;
import model.Draft;
import model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import service.AttachmentService;
import service.EmployeeService;
import service.ManagerService;
import util.PDFUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static controller.AttachmentController.getUploadPath;
import static controller.LoginController.EMPLOYEE;

/**
 * Handle the requests from employees operation at the front end.
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    /**
     * Login Tool.
     */
    private static Logger logger = LogManager.getLogger(EmployeeController.class);

    /**
     * General constants.
     */
    public static final String DOCUMENTS = "documents";
    public static final String DRAFT = "draft";

    /**
     * Search type.
     */
    public static final int DATE = 1;
    public static final int TITLE = 0;
    public static final String ATTACHMENTS = " attachments";
    /**
     * Functions for employee.
     */
    @Autowired
    EmployeeService employeeService;

    /**
     * Functions for Manager.
     */
    @Autowired
	ManagerService managerService;

    /**
     * Functions for Attachment.
     */
    @Autowired
    AttachmentService attachmentService;

    /**
     * Redirect to home page for employee.
     * @return
     */
    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public String homePage() {
        logger.info("employee: home");
        return "/employee/home";
    }

    /**
     * Show info of employee.
     * @return
     */
    @RequestMapping(value = {"/account"}, method = RequestMethod.GET)
    public String getEmployeeProfile() {
        logger.info("Employee: profile");
        return "employee/profile";
    }

    /**
     * Redirect to edit/update account page of employee.
     * @param error
     * @return
     */
    @RequestMapping(value = {"/account/edit"}, method = RequestMethod.GET)
    public String editEmployeeAccount(@RequestParam(required = false, name = "error") String error) {
        logger.info("Employee: edit account.");
        return "employee/editAccount";
    }

    /**
     * Save all change after update employee account.
     * @param id
     * @param email
     * @param bankInfo
     * @param mobile
     * @param department
     * @param position
     * @param session
     * @return
     */
    @RequestMapping(value = {"/account/edit"}, method = RequestMethod.POST)
    public String saveEmployeeChanges(@RequestParam("id") Integer id,
                                      @RequestParam("email") String email,
                                      @RequestParam("bankInfo") String bankInfo,
                                      @RequestParam("mobile") String mobile,
                                      @RequestParam("department") String department,
                                      @RequestParam("position") String position,
                                      HttpSession session) {
        Employee employee = employeeService.updateEmployee(id, email, bankInfo, mobile, position, department);
        session.setAttribute(EMPLOYEE, employee);
        //return "redirect:employee/profile";
        return "employee/profile";
    }

    /**
     * Reset password for employee account.
     * @param error
     * @return
     */
    @RequestMapping(value = {"/account/resetPassword"}, method = RequestMethod.GET)
    public String resetPassword(@RequestParam(required = false, name = "error") String error) {
        return "employee/resetPassword";
    }

    /**
     * Reset password for employee account.
     * @param id
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = {"/account/resetPassword"}, method = RequestMethod.POST)
    public String resetPassword(@RequestParam("id") Integer id,
                                @RequestParam("password") String password,
                                HttpSession session) {
        Employee employee = employeeService.resetPassword(id, password);
        session.setAttribute(EMPLOYEE, employee);
        logger.info("Employee: password updated.");
        //return "redirect:employee/profile";
        return "employee/profile";
    }

    /**
     * Search for employees document records within employees.
     * @param session
     * @param modelMap
     * @return
     */
    @RequestMapping(value = {"/documents"}, method = RequestMethod.GET)
    public String documents(HttpSession session, ModelMap modelMap) {
        Employee employee = (Employee) session.getAttribute(EMPLOYEE);
        Set<Document> documents = employeeService.getUnfinishedDocument(employee);
        modelMap.addAttribute(DOCUMENTS, documents);
        logger.info("Employee Documents.");
        return "employee/documents";
    }

    /**
     * Return documents history.
     * Prepare documents history data.
     * @param session
     * @param modelMap
     * @return
     */
    @RequestMapping(value = {"/history"}, method = RequestMethod.GET)
    public String history(HttpSession session, ModelMap modelMap) {
        Employee employee = (Employee) session.getAttribute(EMPLOYEE);
        modelMap.addAttribute("documents", employeeService.getHistoryDocument(employee));
        logger.info("Employee history.");
        return "employee/history";
    }

    /**
     * Search for documents history by date/title.
     * @param session
     * @param modelMap
     * @param type
     * @param title
     * @param date
     * @return
     */
    @RequestMapping(value = {"/history"}, method = RequestMethod.POST)
    public String history(HttpSession session,
                          ModelMap modelMap,
                          @RequestParam("type") Integer type,
                          @RequestParam("title") String title,
                          @RequestParam("date") String date) {
        Employee employee = (Employee) session.getAttribute(EMPLOYEE);
        Set<Document> documents;
        if (type == DATE) {
            documents = employeeService.searchByDate(employee, date);
        } else {
            documents = employeeService.searchByTitle(employee, title);
        }
        logger.info("Document history search finished.");
        modelMap.addAttribute(DOCUMENTS, documents);
        return "employee/history";
    }

    /**
     * Redirect to homepage of adding new documents.
     * @param draftId
     * @param error
     * @param modelMap
     * @return
     */
    @RequestMapping(value = {"/documents/add"}, method = RequestMethod.GET)
    public String newDoc(@RequestParam(name = "draftId", required = false) Integer draftId,
                         @RequestParam(name = "error", required = false) String error,
                         ModelMap modelMap) {
        logger.info("Request for creating new document.");
        if (draftId != null) {
            Draft draft = employeeService.getDraft(draftId);
            modelMap.addAttribute(DRAFT, draft);
            modelMap.addAttribute(ATTACHMENTS, attachmentService.getAttachments(draft));
            if (error != null) modelMap.addAttribute("error", error);
            logger.info("Load draft: " + draftId);
        }
        return "employee/newDoc";
    }


    /*
    @RequestMapping(value = {"/documents/add"}, method = RequestMethod.POST)
    public String submitDoc(//@RequestBody Document_extend documentExtend,
                            //@RequestBody Document doc,
                            @RequestParam(name = "title", required = false) String title,
                            @RequestParam(name = "content", required = false) String content,
                            @RequestParam(name = "draft_id", required = false) Integer draftId,
                            @RequestParam(name = "number", required = false) Double number,
                            @RequestParam(name = "summary",required = false) Double summary,
                            @RequestParam(name = "delId", required = false) Integer[] delIds,
                            @RequestParam(name = "attachments", required = false)MultipartFile[] attachments,
                           HttpSession session, ModelMap modelMap) {
        Employee employee = (Employee) session.getAttribute(EMPLOYEE);
        Document document = new Document(title, content, new Date(), Document.SUBMITTED, employee, summary, number);
        //Document document = new Document(doc.getTitle(), doc.getContent(), new Date(), Document.SUBMITTED, employee, doc.getSummary(), doc.getNumber());
        Document saveDoc = null;
        try {
            saveDoc = employeeService.submitDocument(document, attachments, getUploadPath(session)); //creat document in the database.
            //Integer draftId = documentExtend.getDraftId();
            if (draftId != null) {
                Draft draft = employeeService.getDraft(draftId);
                attachmentService.deleteAttachment(delIds, getUploadPath(session)); // delete the deleted attachments.
                attachmentService.moveAttachment(draft, saveDoc); // move attachments from draft to documents.
                employeeService.deleteDraft(draft);
                employee.setDraft(null);
                session.setAttribute(EMPLOYEE, employee);
            }
        } catch (AttachFailException attachFailException) {
            modelMap.addAttribute("error", attachFailException.getMessage());
            logger.error("Employee: " + attachFailException.getMessage());
            //Integer draftId = documentExtend.getDraftId();
            if (draftId != null) modelMap.addAttribute("draftId", draftId);
            return "/employee/documents";
        }
        //assert saveDoc != null;
        managerService.AssignDocument(saveDoc);
        logger.info("Document submitted.");
        modelMap.addAttribute("filetype", "document");
        return "success";
    }

     */

    @RequestMapping(value = {"/document/add"}, method = RequestMethod.POST)
    public String submitDoc(HttpServletRequest request, HttpSession session, ModelMap modelMap) {
        MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        MultipartHttpServletRequest multipartHttpServletRequest = resolver.resolveMultipart(request);

        List<MultipartFile> attachments = multipartHttpServletRequest.getFiles("attachments");
        String title = multipartHttpServletRequest.getParameter("title");
        String content = multipartHttpServletRequest.getParameter("content");
        String draftIdStr = multipartHttpServletRequest.getParameter("draftId");
        String numberStr = multipartHttpServletRequest.getParameter("number");
        String summaryStr = multipartHttpServletRequest.getParameter("summary");
        String delIdsStr = multipartHttpServletRequest.getParameter("delIds");

        if (attachments == null) {
            attachments = new ArrayList<>();
        }
        Double summary = null;
        if (summaryStr != null && summaryStr.length() > 0) {
            summary = Double.parseDouble(summaryStr);
        }
        Double number = null;
        if (numberStr != null && numberStr.length() > 0) {
            number = Double.parseDouble(numberStr);
        }
        Integer[] delIds = new Integer[]{};
        if (delIdsStr != null) {
            String[] split = delIdsStr.split(",");
            delIds = new Integer[split.length];
            for (int i = 0; i < split.length; i ++) {
                delIds[i] = Integer.valueOf(split[i]);
            }
        }
        MultipartFile file = null;
        BufferedOutputStream outputStream = null;
        for (int i = 0; i < attachments.size(); i ++) {
            file = attachments.get(i);
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    outputStream = new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename())));
                    outputStream.write(bytes);
                    outputStream.close();
                } catch (Exception exception) {
                    outputStream = null;
                    return "File upload failed " + i + ": " + exception.getMessage();
                }
            } else {
                return "File upload failed " + i + "file is empty";
            }
        }

        Employee employee = (Employee) session.getAttribute(EMPLOYEE);
        Document document = new Document(title, content, new Date(), Document.SUBMITTED, employee, summary, number);
        Document saveDoc = null;
        try {
            saveDoc = employeeService.submitDocument(document, attachments.toArray(new MultipartFile[attachments.size()]), getUploadPath(session));
            Integer draftId = Integer.valueOf(draftIdStr);
            Draft draft = employeeService.getDraft(draftId);
            attachmentService.deleteAttachment(delIds, getUploadPath(session));
            attachmentService.moveAttachment(draft, saveDoc);
            employeeService.deleteDraft(draft);
            employee.setDraft(null);
            session.setAttribute(EMPLOYEE, employee);
        } catch (AttachFailException exception) {
            logger.error("Employee: " + exception.getMessage());
        }
        managerService.AssignDocument(saveDoc);
        logger.info("Document submitted");
        modelMap.addAttribute("filetype", "document");
        return "success";
    }
    /**
     * Save draft for employee
     * @param title
     * @param draftId
     * @param number
     * @param summary
     * @param content
     * @param attachments
     * @param delIds
     * @param session
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/draft/save", method = RequestMethod.POST)
    public String saveDraft(@RequestParam("title") String title,
                            @RequestParam("draft_id") Integer draftId,
                            @RequestParam("number") Double number,
                            @RequestParam("summary") Double summary,
                            @RequestParam("content") String content,
                            @RequestParam(name = "attachments", required = false) MultipartFile[] attachments,
                            @RequestParam(name = "delId", required = false) Integer[] delIds,
                            HttpSession session, ModelMap modelMap) {
        Employee employee = (Employee)session.getAttribute(EMPLOYEE);
        Draft draft = new Draft(title, content, employee, number, summary);
        if (draftId != null) draft.setId(draftId);
        String path = getUploadPath(session);
        draft = employeeService.saveDraft(employee, draft, attachments, path);
        attachmentService.deleteAttachment(delIds, path);
        employee.setDraft(draft);
        session.setAttribute(EMPLOYEE, employee);
        modelMap.addAttribute("filetype", "draft");
        logger.info("Save draft");
        return "success";
    }

    /**
     * Delete draft for employee.
     * @param session
     * @return
     */
    @RequestMapping(value = "/draft/delete", method = RequestMethod.GET)
    public String deleteDraft(HttpSession session) {
        Employee employee = (Employee)session.getAttribute(EMPLOYEE);
        Draft draft = employee.getDraft();
        attachmentService.deleteAttachmentByDraft(draft, getUploadPath(session));
        employeeService.deleteDraft(draft);
        employee.setDraft(null);
        session.setAttribute(EMPLOYEE, employee);
        logger.info("Delete draft");
        return "redirect: employee/documents";
    }

    /**
     * Redirect to document detail page.
     * @param id
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/documents/detail", method = RequestMethod.GET)
    public String detail(@RequestParam(name = "id") Integer id, ModelMap modelMap) {
        logger.info("Document detail");
        Document document = employeeService.getDocument(id);
        modelMap.addAttribute("document", document);
        modelMap.addAttribute(ATTACHMENTS, attachmentService.getAttachments(document));
        return "employee/docDetail";
    }

    /**
     * Redirect to response document page.
     * @param id
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/documents/response", method = RequestMethod.GET)
    public String responseDoc(@RequestParam(name = "id") Integer id, ModelMap modelMap) {
        logger.info("Request for response document");
        modelMap.addAttribute("document", employeeService.getDocument(id));
        return "employee/responseDoc";
    }

    /**
     * Handle response storage of document, storing new attachments.
     * @param id
     * @param result
     * @param content
     * @param attachments
     * @param session
     * @return
     */
    @RequestMapping(value = "/documents/response", method = RequestMethod.POST)
    public String responseDoc(@RequestParam(name = "id") Integer id,
                              @RequestParam(name = "result") String result,
                              @RequestParam(name = "content") String content,
                              @RequestParam(name = "attachments", required = false) MultipartFile[] attachments,
                              HttpSession session) {
        logger.info("Request for response document");
        String uploadPath = session.getServletContext().getRealPath("/").split("target")[0] + "upload/";
        try {
            employeeService.responseDocument(id, result, content, attachments, uploadPath);
        } catch (AttachFailException attachFailException) {
            logger.error(attachFailException.getMessage());
        }
        return "redirect:/employee/documents";
    }

    /**
     * Prepare the document for downlaod.
     * @param id
     * @param session
     * @return
     */
    @RequestMapping("/documents/content")
    public ResponseEntity<byte[]> download(@RequestParam("id") Integer id, HttpSession session) {
        Employee employee = (Employee) session.getAttribute(EMPLOYEE);
        HttpHeaders headers = new HttpHeaders();
        String fileName = "Document" + id + " - " + employee.getId() + ".pdf";
        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        logger.info("Employee: download document No. " + id);
        return new ResponseEntity<>(
                PDFUtil.content(employeeService.getDocument(id), employee), headers, HttpStatus.OK);
    }
}
