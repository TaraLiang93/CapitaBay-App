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
        try {
            HttpSession session = request.getSession();
            UserBean userBean = (UserBean) session.getAttribute("userBean");
            if(userBean == null)
            {
                userBean = new UserBean();
                session.setAttribute("userBean", userBean);
            }
            
            LinkedList<Orders> results = new LinkedList<>();
            
            Integer ssn = userBean.getSocialSecurityNumber();
            
            //should set the thingy
            Integer customerSSN = Integer.parseInt(request.getParameter("customerSSN"));
            //should set the thingy
            String stockSymbol = request.getParameter("stockSymbol");
            
            
            String query = "call listOrders("+ssn+","+customerSSN+","+stockSymbol+")";
            
            ResultSet res = CapitaBay.ExecuteQuery(query);
            
            while(res.next())
            {
                Orders order = new Orders();
                order.set(res);
                results.add(order);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ListOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
