package controller;

import com.mysql.jdbc.Driver;
import model.Administrator;
import model.Employee;
import model.Manager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import service.AdminService;
import service.EmployeeService;
import service.ManagerService;

import javax.servlet.http.HttpSession;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * This controller deals with the login and logout request for all types of users.
 */
@Controller
public class LoginController {
    public static final String EMPLOYEE = "employee";
    public static final String MANAGER = "manager";
    public static final String SYSTEM_ADMIN = "admin";
    public static final String USER_TYPE = "userType";

    /**
     * Log tools.
     */
    private static Logger logger = LogManager.getLogger(LoginController.class);

    /**
     * Service for administrator.
     */
    @Autowired
    AdminService adminService;

    /**
     * Service for managers.
     */
    @Autowired
	ManagerService managerService;

    /**
     * Service for employees.
     */
    @Autowired
    EmployeeService employeeService;

    /**
     * Handle the login actions for different users.
     * After login, each user type only sees their own home page.
     */

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        //@RequestParam("userType") String userType,
                        HttpSession session, ModelMap modelMap) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement statement = DriverManager.getConnection("jdbc:mysql://db-mysql-fra1-07133-do-user-9073766-0.b.db.ondigitalocean.com:25060/MOODJA", "doadmin", "QbLFvoB1t8KWSZ44").createStatement();
            String employee_id = "SELECT id FROM employee";
            //if(userType.equals(EMPLOYEE)) {
            if (username.equals(statement.executeQuery(employee_id))) {
            //Employee employee = null;
                Employee employee = employeeService.login(Integer.valueOf(username), password);
                //Employee employee = employeeService.login(Integer.valueOf(username), password);
                if (employee != null) {
                    //session.setAttribute(userType, employee);
                    //session.setAttribute(USER_TYPE, userType);
                    session.setAttribute("employee", employee);
                    //logger.info("Logged in as: " + userType + " " + username);
                    logger.info("Logged in as Employee: " + username);
                    return "redirect:/employee/";
                }
            }

            //if (userType.equals(MANAGER)) {
            if (username.equals("eric.wang@moodja.com")) {
            //Manager manager = null;
                Manager manager = managerService.login(username, password);
                //Manager manager = managerService.login(username, password);
                if (manager != null) {
                    //session.setAttribute(userType, manager);
                    //session.setAttribute(USER_TYPE, userType);
                    //modelMap.addAttribute("manager", manager);
                    session.setAttribute("manager", manager);
                    //logger.info("Logged in as: " + userType + " " + username);
                    logger.info("Logged in as Manager: " + username);
                    return "redirect:/manager/";
                }
            }
        //}

        //if (userType.equals(SYSTEM_ADMIN)) {
            if (username.equals("admin")) {
            //Administrator administrator = null;
                Administrator administrator = adminService.login(username, password);
                //Administrator administrator = adminService.login(username, password);
                if (administrator != null) {
                    //session.setAttribute(userType, administrator);
                    //session.setAttribute(USER_TYPE, userType);
                    session.setAttribute("admin", administrator);
                    //logger.info("Logged in as: " + userType + " " + username);
                    logger.info("Logged in as Administrator: " + username);
                    return "redirect:/admin/";
                }
            }

            modelMap.addAttribute("error", "Incorrect user or password");
            logger.error("Login failed: " + " " + username);
        } catch (Exception exception) {
            modelMap.addAttribute("error", "Incorrect user or password");
            exception.printStackTrace();
            logger.error("Login failed: " + " " + username);
        }

        return "login";
    }

    /**
     * Return the login page.
     * @return
     */
    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    /**
     * Deal with the logout operation.
     * @param session
     * @return index page.
     */

    /*
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {

        String userType = (String) session.getAttribute(USER_TYPE);

        String username = "";
        switch (userType) {
            case EMPLOYEE:
                Employee employee = (Employee) session.getAttribute(EMPLOYEE);
                username = employee.getId().toString();
                break;
            case MANAGER:
                Manager manager = (Manager) session.getAttribute(MANAGER);
                username = manager.getEmail();
                break;
            case SYSTEM_ADMIN:
                Administrator administrator = (Administrator) session.getAttribute(SYSTEM_ADMIN);
                username = administrator.getUsername();
                break;
        }
        session.removeAttribute(userType);
        logger.info("Logout " + userType + " " + username);
        session.removeAttribute(USER_TYPE);
        session.invalidate();
        return "index";
    }

     */
}
