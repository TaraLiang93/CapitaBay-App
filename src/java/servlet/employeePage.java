/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import Bean.UserBean;
import DataBase.CapitaBay;
import Tables.Employee;
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
 * @author Jason
 */
@WebServlet(name = "employeePage", urlPatterns = {"/employeePage"})
public class employeePage extends HttpServlet {


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         HttpSession session = request.getSession();
        
        UserBean userBean = (UserBean) session.getAttribute("userBean");
        if(userBean == null)
        {
            userBean = new UserBean();
            session.setAttribute("userBean", userBean);
        }
                
        try {
            Long ssn = userBean.getSocialSecurityNumber();
            String query = "SELECT P.*,E.* FROM Employee E"
                         + "Inner JOIN Person P"
                         + "ON P.SocialSecurityNumber = E.SocialSecurityNumber;";
            ResultSet res = CapitaBay.ExecuteQuery(query);
            LinkedList<Employee> result = new LinkedList<Employee>();
            while(res.next()){
                Employee current = new Employee();
                current.set(res);
                result.add(current);
            };
            request.setAttribute("employees", result);
            request.setAttribute("searchResultsName", userBean.getFirstName());

            
        } catch (SQLException ex) {
            Logger.getLogger(employeePage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(employeePage.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/employee.jsp");
        dispatcher.forward(request, response);
    }


    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
