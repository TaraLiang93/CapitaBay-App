/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginWebsite;

import Bean.UserBean;
import Bean.customerRevenue;
import DataBase.CapitaBay;
import helper.checkExist;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author root
 */
@WebServlet(name = "loginValidation", urlPatterns = {"/loginValidation"})
public class loginValidation extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws IOException, ServletException {
        HttpSession session = req.getSession();
        
        try {
            String username = req.getParameter("username"); 
            String password = req.getParameter("password");
            String status = req.getParameter("status");

            String query = "call validUser('" + username + "','" + password + "');";
            ResultSet res = CapitaBay.ExecuteQuery(query);
            if (res.next()) {
                long ssn = res.getLong("SocialSecurityNumber");
                String userName = res.getString("Username");

                UserBean userBean = new UserBean();
                userBean.setSocialSecurityNumber(ssn);
                userBean.setUsername(userName);

                if (status.equalsIgnoreCase("customer")) {
                    boolean isCustomer = checkExist.checkIsCustomer(ssn);
                    if (isCustomer == false) {
                        req.setAttribute("error", "**Incorrect Username and Password**");
                        req.setAttribute("errorStatus", true);
                        System.out.println(req.getHeader("Referer"));
                        String ref = req.getHeader("Referer");
                        ref = ref.substring(ref.indexOf("/jsp"), ref.length());
                        System.out.println("ref = " + ref);
                        RequestDispatcher dispatcher = req.getRequestDispatcher(ref);
                        dispatcher.forward(req, resp);
                    }
                    else{
                        userBean.setStatus("customer");
                    }
                } else {//employee
                    String employeeStatus = checkExist.checkEmployee(ssn);
                    if(employeeStatus.equalsIgnoreCase("Manager")){
                        userBean.setStatus("Manager");
                    }
                    else if (employeeStatus.equalsIgnoreCase("CustomerRep")){
                        userBean.setStatus("CustomerRep");
                    }
                    else{
                       req.setAttribute("error", "**Incorrect Username and Password**");
                        req.setAttribute("errorStatus", true);
                        System.out.println(req.getHeader("Referer"));
                        String ref = req.getHeader("Referer");
                        ref = ref.substring(ref.indexOf("/jsp"), ref.length());
                        System.out.println("ref = " + ref);
                        RequestDispatcher dispatcher = req.getRequestDispatcher(ref);
                        dispatcher.forward(req, resp); 
                    }
                }

                session.setAttribute("userBean", userBean);
                req.setAttribute("errorStatus", false);
             
                RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/homepage.jsp");
                dispatcher.forward(req, resp);
            } else {
                req.setAttribute("error", "**Incorrect Username and Password**");
                req.setAttribute("errorStatus", true);
                System.out.println(req.getHeader("Referer"));
                String ref = req.getHeader("Referer");
                ref = ref.substring(ref.indexOf("/jsp"), ref.length());
                System.out.println("ref = " + ref);
                RequestDispatcher dispatcher = req.getRequestDispatcher(ref);
                dispatcher.forward(req, resp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(loginValidation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(loginValidation.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    
}
