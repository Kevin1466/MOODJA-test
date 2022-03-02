package controller;

import exception.EmailExistsException;
import model.Document;
import model.Manager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.AttachmentService;
import service.ManagerService;

import javax.servlet.http.HttpSession;

import java.util.Date;

import static controller.LoginController.MANAGER;
import static model.Document.*;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    /**
     * Login tool
     */
    private static Logger logger = LogManager.getLogger(ManagerController.class);

    public static final String DOCUMENT = "documents";
    public static final String ATTACHMENT = "attachment";

    /**
     * Function for manager operations.
     */
    @Autowired
    ManagerService managerService;

    /**
     * Function for attachments.
     */
    @Autowired
    AttachmentService attachmentService;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String homePage() {
        logger.info("Manager: home");
        return "/manager/home";
    }

    @RequestMapping(value = {"documents"}, method = RequestMethod.GET)
    public String getDocument(HttpSession session, ModelMap modelMap) {
        Manager manager = (Manager) session.getAttribute(MANAGER);
        modelMap.addAttribute(DOCUMENT, managerService.getUnhandledDoc(manager));
        logger.info("Manager Document: ");
        return "/manager/documents";
    }

    /**
     * Redirect to account info page for manager.
     * @return
     */
    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String account() {
        return "/manager/profile";
    }

    /**
     * Redirect to update account page for manager.
     * @param error
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/account/edit", method = RequestMethod.GET)
    public String editAccount(@RequestParam(required = false, name = "error") String error, ModelMap modelMap) {
        if (error != null) modelMap.addAttribute("error", error);
        return "/manager/editAccount";
    }

    /**
     * Save all changes after update account for manager.
     * @param id
     * @param firstName
     * @param lastName
     * @param email
     * @param mobile
     * @param session
     * @param modelMap
     * @return
     */
    @RequestMapping(value = {"/account/edit"}, method = RequestMethod.POST)
    public String saveChanges(@RequestParam("id") Integer id,
                              @RequestParam("firstName") String firstName,
                              @RequestParam("lastName") String lastName,
                              @RequestParam("email") String email,
                              @RequestParam("mobile") String mobile,
                              HttpSession session, ModelMap modelMap) {
        try {
            Manager manager = managerService.update(id, email, mobile, firstName, lastName);
            session.setAttribute(MANAGER, manager);
        } catch (EmailExistsException emailExistsException) {
            modelMap.addAttribute("error", emailExistsException.getMessage());
            logger.error(emailExistsException.getMessage());
            return "redirect:/manager/editAccount";
        }
        return "redirect:/manager/account";
    }

    /**
     * Redirect to reset password page for manager.
     * @param error
     * @return
     */
    @RequestMapping(value = {"/account/resetPassword"}, method = RequestMethod.GET)
    public String resetPassword(@RequestParam(required = false, name = "error") String error) {
        return "/manager/resetPassword";
    }

    /**
     * Reset password for manager.
     * @param id
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = {"/account/resetPassword"}, method = RequestMethod.POST)
    public String resetPassword(@RequestParam("id") Integer id,
                                @RequestParam("password") String password,
                                HttpSession session) {
        Manager manager = managerService.resetPassword(id, password);
        session.setAttribute(MANAGER, manager);
        logger.info("Manager: reset password.");
        return "redirect:/manager/account";
    }

    /**
     * Approve the document and save it to the database, redirect to document page.
     * @param id
     * @param result
     * @return
     */
    @RequestMapping(value = "/documents/manage", method = RequestMethod.GET)
    public String approve(@RequestParam("id") Integer id,
                          @RequestParam("result") String result) {
        managerService.manageDoc(id, result, new Date(), null);
        logger.info("Document: manage");
        return "redirect:/manager/document";
    }

    /**
     * Approve or dinnie a document and redirect to document page.
     * @param id
     * @param result
     * @param comment
     * @return
     */
    @RequestMapping(value = "/documents/manage", method = RequestMethod.POST)
    public String process(@RequestParam("id") Integer id,
                          @RequestParam("result") String result,
                          @RequestParam(name = "comment", required = false) String comment) {
        if (result.equals(REPLIED)) managerService.manageDoc(id, result, null, comment);
        else managerService.manageDoc(id, result, new Date(), comment);
        return "redirect:/manager/documents";
    }

    /**
     * Redirect to dennie or response page of document.
     * @param id
     * @param result
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/documents/dennie", method = RequestMethod.GET)
    public String dennie(@RequestParam("id") Integer id,
                         @RequestParam("result") String result,
                         ModelMap modelMap) {
        modelMap.addAttribute(DOCUMENT, managerService.getDoc(id));
        modelMap.addAttribute("result", result);
        logger.info("Document: manage.");
        return "/manager/dinnieDoc";
    }

    /**
     * View document detail.
     * @param id
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/documents/detail", method = RequestMethod.GET)
    public String detail(@RequestParam("id") Integer id, ModelMap modelMap) {
        Document document = managerService.getDoc(id);
        if (document.getStatus().equals(SUBMITTED)) managerService.manageDoc(document, PROCESSING);
        modelMap.addAttribute(ATTACHMENT, attachmentService.getAttachments(document));
        modelMap.addAttribute(DOCUMENT, document);
        logger.info("Document detail: " + document.toString());
        return "manager/docDetail";
    }
}
