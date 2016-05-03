/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edit;

import DataBase.CapitaBay;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jason
 */
@WebServlet(name = "editCustomer", urlPatterns = {"/editCustomer"})
public class editCustomer extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer ssn = Integer.parseInt(request.getParameter("customerID"));
        Float rating = Float.parseFloat(request.getParameter("rating"));
        String ccn = request.getParameter("creditNum");
        String email = request.getParameter("email");
        
        String query = "call editCustomer("+ ssn+","+rating+",'"+ccn+"','"+email+"');";
        
        try {
            CapitaBay.ExecuteQuery(query);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(editCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
