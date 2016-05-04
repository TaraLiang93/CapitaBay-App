/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomerRep;

import Bean.UserBean;
import DataBase.CapitaBay;
import Tables.Stock;
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
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Jason
 */
@WebServlet(name = "MakeStockSuggestionList", urlPatterns = {"/MakeStockSuggestionList"})
public class MakeStockSuggestionList extends HttpServlet {

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
        {

        HttpSession session = request.getSession();
       
        UserBean userBean  = (UserBean) session.getAttribute("userBean");
        if(userBean == null)
        {
            userBean = new UserBean();
            session.setAttribute("userBean", userBean);
        }
        
        Long c_ssn = Long.parseLong(request.getParameter("suggestionSSN"));
        
        String query = "call getStockSuggestionList("+c_ssn+");";
        
        try {
            ResultSet res= CapitaBay.ExecuteQuery(query);
            JSONObject json = new JSONObject();
            JSONArray jarr = new JSONArray();
            while(res.next()){
                Stock currentStock = new Stock();
                currentStock.set(res);
                jarr.put(currentStock.getJson());
            }
            response.setContentType("application/json");
            json.put("stockSugesst", jarr);
            response.getWriter().print(json);
            response.getWriter().flush();
        } catch (SQLException ex) {
            Logger.getLogger(MakeStockSuggestionList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MakeStockSuggestionList.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    }

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
