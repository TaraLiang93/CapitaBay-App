/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomerQueries;

import Bean.ConditionalOrderHistory;
import Bean.UserBean;
import DataBase.CapitaBay;
import java.io.IOException;
import java.io.PrintWriter;
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
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author root
 */
@WebServlet(name = "ListConditionalOrderHistory", urlPatterns = {"/ListConditionalOrderHistory"})
public class ListConditionalOrderHistory extends HttpServlet {

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
        response.setContentType("application/json");
        HttpSession session = request.getSession();
        UserBean userBean = (UserBean) session.getAttribute("userBean");
        if (userBean == null) {
            userBean = new UserBean();
            session.setAttribute("userBean", userBean);
        }
        try {
            long ssn = userBean.getSocialSecurityNumber();
//            LinkedList<ConditionalOrderHistory> results = new LinkedList<ConditionalOrderHistory>();
            String check = "select count(*) from Orders where Orders.OrderID=" + request.getParameter("orderID") + " AND Orders.SocialSecurityNumber = " + ssn + ";";
//            System.out.println(check);
            ResultSet res = CapitaBay.ExecuteQuery(check);
            int checkSSN = -1;
            if (res.next()) {
                checkSSN = res.getInt("count(*)");
            }
            if (checkSSN > 0) {
                String query = "call getConditionalOrderHistory(" + request.getParameter("orderID") + ");";
                res = CapitaBay.ExecuteQuery(query);
                
                JSONObject json = new JSONObject();
                JSONArray jarr = new JSONArray();
                
                while (res.next()) {
                    ConditionalOrderHistory conditionalOrderHistory = new ConditionalOrderHistory();
                    conditionalOrderHistory.set(res);
                    jarr.put(conditionalOrderHistory.getJson());
                }
                response.setContentType("application/json");
//                response.setCharacterEncoding("UTF-8");
                json.put("history", jarr);
                response.getWriter().print(jarr.toString());
                response.getWriter().flush();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListConditionalOrderHistory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ListConditionalOrderHistory.class.getName()).log(Level.SEVERE, null, ex);
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
