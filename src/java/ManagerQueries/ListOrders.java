/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagerQueries;

import Bean.UserBean;
import DataBase.CapitaBay;
import Tables.Orders;
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
 * @author Jason
 */
@WebServlet(name = "ListOrders", urlPatterns = {"/ListOrders"})
public class ListOrders extends HttpServlet {


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
        try {
            HttpSession session = request.getSession();
            UserBean userBean = (UserBean) session.getAttribute("userBean");
            if(userBean == null)
            {
                userBean = new UserBean();
                session.setAttribute("userBean", userBean);
            }
            
            
            Long ssn = userBean.getSocialSecurityNumber();
            
            //should set the thingy
            String customerName = (request.getParameter("customerName"));
            //should set the thingy
            String stockSymbol = request.getParameter("stockSymbol");
            
            
            String query = "call listOrders("+ssn+",'"+customerName+"','"+stockSymbol+"')";
            
            ResultSet res = CapitaBay.ExecuteQuery(query);
            
            JSONObject json = new JSONObject();
            JSONArray jarr = new JSONArray();
            
            while(res.next())
            {
                Orders order = new Orders();
                order.set(res);
                jarr.put(order.getJson());
            }
            
           json.put("orders", jarr);
            response.getWriter().print(json);
            response.getWriter().flush();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ListOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
