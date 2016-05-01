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
 * @author Patrick
 */
@WebServlet(name = "GetConditionalOrderHistory", urlPatterns = {"/GetConditionalOrderHistory"})
public class GetConditionalOrderHistory extends HttpServlet {

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
        if (userBean == null) {
            userBean = new UserBean();
            session.setAttribute("userBean", userBean);
        }
        try {
            long ssn = userBean.getSocialSecurityNumber();
            LinkedList<ConditionalOrderHistory> results = new LinkedList<ConditionalOrderHistory>();
            String check = "select count(*) from Orders where Orders.OrderID=" + session.getAttribute("orderID") + " AND Orders.SocialSecurityNumber = " + ssn + ");";
            ResultSet res = CapitaBay.ExecuteQuery(check);
            int checkSSN = -1;
            if (res.next()) {
                checkSSN = res.getInt("count(*)");
            }
            if (checkSSN > 0) {
                String query = "call getConditionalOrderHistory(" + session.getAttribute("orderID") + ");";
                res = CapitaBay.ExecuteQuery(query);
                
                JSONObject json = new JSONObject();
                JSONArray jarr = new JSONArray();
                
                while (res.next()) {
                    ConditionalOrderHistory conditionalOrderHistory = new ConditionalOrderHistory();
                    conditionalOrderHistory.set(res);
                    jarr.put(conditionalOrderHistory.getJson());
                }
                
                json.put("history", jarr);
                response.getWriter().print(json);
                response.getWriter().flush();
            }
            session.setAttribute("currentStockHoldings", results);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(GetConditionalOrderHistory.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
