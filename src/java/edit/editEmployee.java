/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edit;

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

/**
 *
 * @author Jason
 */
@WebServlet(name = "editEmployee", urlPatterns = {"/editEmployee"})
public class editEmployee extends HttpServlet {

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
        
        
            Integer employeeSSN = Integer.parseInt(request.getParameter("employeeSSN"));
            String employeePos = request.getParameter("employeePos");
            Double hourRate = Double.parseDouble(request.getParameter("hourRate"));
            
            String query = "call editEmployee("+employeeSSN+",'" +employeePos+"',"+hourRate+")";
            
        try {
            ResultSet results = CapitaBay.ExecuteQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(editEmployee.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(editEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//        response.setStatus(NC_OK);
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
