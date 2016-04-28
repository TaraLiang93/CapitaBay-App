/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagerQueries;

import Bean.UserBean;
import DataBase.CapitaBay;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jason
 */
@WebServlet(name = "manageEmployees", urlPatterns = {"/manageEmployees"})
public class ManageEmployees extends HttpServlet {


    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        
        HttpSession session = req.getSession();
        
        UserBean userBean = (UserBean) session.getAttribute("userBean");
        if(userBean == null)
        {
            userBean = new UserBean();
            session.setAttribute("userBean", userBean);
        }
        
        
        try {
            String editEmployee = req.getParameter("editEmployee");
            Long managerSSN = userBean.getSocialSecurityNumber();
            Long employeeSSN = Long.parseLong(req.getParameter("employeeSSN"));
            String employeePos = req.getParameter("employeePos");
            Double hourRate = Double.parseDouble(req.getParameter("hourRate"));
            
            String query = "call manageEmployees("
                    +editEmployee+","+managerSSN+","+employeeSSN+","
                    +employeePos+","+hourRate+")";
            
            ResultSet results = CapitaBay.ExecuteQuery(query);
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ManageEmployees.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
