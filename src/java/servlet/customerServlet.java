/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import Bean.CurrentStockHoldings;
import Bean.UserBean;
import CustomerQueries.GetCurrentStockHoldings;
import DataBase.CapitaBay;
import java.io.IOException;
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
 * @author root
 */
@WebServlet(name = "customer", urlPatterns = {"/customer"})
public class customerServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
            System.out.println(query);
            ResultSet res = CapitaBay.ExecuteQuery(query);            
            while(res.next()) {
                CurrentStockHoldings currentStockHoldings = new CurrentStockHoldings();
                currentStockHoldings.set(res);
                results.add(currentStockHoldings);               
            }
            request.setAttribute("currentStockHoldings", results);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(GetCurrentStockHoldings.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/customer.jsp");
        dispatcher.forward(request, response);
    }

}
