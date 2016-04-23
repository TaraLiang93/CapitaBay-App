/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CustomerQueries;

import Bean.CurrentStockHoldings;
import Bean.UserBean;
import DataBase.CapitaBay;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
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
 * @author Patrick
 */
@WebServlet(name = "GetCurrentStockHoldings", urlPatterns = {"/GetCurrentStockHoldings"})
public class GetCurrentStockHoldings extends HttpServlet {
   
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
        if(userBean == null) {
            userBean = new UserBean();
            session.setAttribute("userBean", userBean);
        }
        try {
            LinkedList<CurrentStockHoldings> results = new LinkedList<CurrentStockHoldings>();
            String query = "call getCurrentStockHoldings(" + userBean.getSocialSecurityNumber() + ");";              
            ResultSet res = CapitaBay.ExecuteQuery(query);            
            while(res.next()) {
                CurrentStockHoldings currentStockHoldings = new CurrentStockHoldings();
                currentStockHoldings.set(res);
                results.add(currentStockHoldings);               
            }
            session.setAttribute("currentStockHoldings", results);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(GetCurrentStockHoldings.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
        
        
    }

}