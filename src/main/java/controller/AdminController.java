package controller;

import exception.EmailExistsException;
import exception.HasUnhandledWorkException;
import model.Administrator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.AdminService;
import service.AttachmentService;
import service.ManagerService;

import javax.servlet.http.HttpSession;

import static controller.EmployeeController.DOCUMENTS;
import static controller.LoginController.SYSTEM_ADMIN;

/**
 * Control functions for administrator.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    private static Logger logger = LogManager.getLogger(AdminController.class);

    @Autowired
    AdminService adminService;

    @Autowired
	ManagerService managerService;

    @Autowired
    AttachmentService attachmentService;

    /**
     * Redirect to home page for administrator.
     * @return
     */
    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String homePage() {
        logger.info("Admin: home");
        return "admin/home";
    }

    /**
     * Redirect to profile page for administrator.
     * @return
     */
    @RequestMapping(value = {"/profile"}, method = RequestMethod.GET)
    public String getProfile() {
        logger.info("Admin: profile");
        return "admin/profile";
    }

    /**
     * Redirect to reset password page for administrator.
     * @return
     */
    @RequestMapping(value = {"/resetPassword"}, method = RequestMethod.GET)
    public String resetPassword() {
        logger.info("Admin: reset password page.");
        return "admin/resetPassword";
    }

    /**
     * Redirect to home page after reset password for administrator.
     * @param newPassword
     * @param id
     * @param httpSession
     * @return
     */
    @RequestMapping(value = {"/resetPassword"}, method = RequestMethod.POST)
    public String resetPassword(@RequestParam("newPassword") String newPassword,
                                @RequestParam("id") Integer id,
                                @NotNull HttpSession httpSession) {
        Administrator administrator = adminService.resetPassword(newPassword, id);
        httpSession.setAttribute(SYSTEM_ADMIN, administrator);
        logger.info("Admin: reset password.");
        return "admin/profile";
    }

    /**
     * Redirect to home page for managers.
     * @param modelMap
     * @param error
     * @return
     */
    @RequestMapping(value = {"/managers"}, method = RequestMethod.GET)
    public String managers(ModelMap modelMap, @RequestParam(name = "error", required = false) String error) {
        modelMap.addAttribute("manager", adminService.getManager());
        if (error != null) modelMap.addAttribute("error", error);
        logger.info("Admin: managers");
        return "admin/managers";
    }

    /**
     * Redirect to add new manager page for administrator.
     * @param error
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/managers/add", method = RequestMethod.GET)
    public String addManager(@RequestParam(name = "error", required = false) String error, ModelMap modelMap) {
        if (error != null) modelMap.addAttribute("error", error);
        return "/admin/newManager";
    }

    @RequestMapping(value = "/managers/edit", method = RequestMethod.GET)
    public String editManager(ModelMap modelMap,
                              @RequestParam("managerId") Integer managerId,
                              @RequestParam(name = "error", required = false) String error) {
        if (error != null) modelMap.addAttribute("error", error);
        modelMap.addAttribute("manager", managerService.get(managerId));
        return "/admin/newManager";
    }

    /**
     * Add manager if there is no valid ID, or update manager info when manager ID is provided.
     * @param id
     * @param adminId
     * @param firstName
     * @param lastName
     * @param password
     * @param mobile
     * @param email
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/managers/edit", method = RequestMethod.POST)
    public String editManager(@RequestParam(name = "id", required = false) Integer id,
                              @RequestParam(name = "adminId", required = false) Integer adminId,
                              @RequestParam("firstName") String firstName,
                              @RequestParam("lastName") String lastName,
                              @RequestParam("password") String password,
                              @RequestParam("mobile") String mobile,
                              @RequestParam("email") String email,
                              ModelMap modelMap) {
        if (id == null) {
            try {
                managerService.addManager(email, password, firstName, lastName, mobile, adminService.get(adminId));
            } catch (EmailExistsException emailExistsException) {
                modelMap.addAttribute("error", emailExistsException.getMessage());
                return "redirect:/admin/managers/add";
            }
        } else {
            try {
                managerService.updateManager(id, password, email, mobile, firstName, lastName);
            } catch (EmailExistsException emailExistsException) {
                modelMap.addAttribute("error", emailExistsException.getMessage());
                modelMap.addAttribute("managerId", id);
                return "redirect:/admin/manager/edit";
            }
        }
        return "redirect:/admin/managers";
    }

    /**
     * Delete manager by ID.
     * @param id
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/managers/delete", method = RequestMethod.POST)
    public String deleteManager(@RequestParam("managerId") Integer id, ModelMap modelMap) {
        try {
            managerService.deleteManager(id);
        } catch (HasUnhandledWorkException hasUnhandledWorkException) {
            modelMap.addAttribute("error", hasUnhandledWorkException.getMessage());
        }
        return "redirect:/admin/managers";
    }

    @RequestMapping(value = "documents", method = RequestMethod.GET)
    public String documents(ModelMap modelMap) {
        modelMap.addAttribute(DOCUMENTS, adminService.getDocuments());
        return "admin/documents";
    }

    @RequestMapping(value = "documents/delete", method = RequestMethod.POST)
    public String deleteDocument(@RequestParam("id") Integer id, ModelMap modelMap, HttpSession session) {
        try {
            String path = session.getServletContext().getRealPath("/").split("target")[0] + "upload/";
            attachmentService.deleteAttachmentsByDocument(id, path);
            adminService.deleteDocument(id);
        } catch (Exception exception) {
            modelMap.addAttribute("error", exception.getMessage());
        }
        return "redirect:/admin/documents";
    }
}
