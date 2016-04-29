/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagerQueries;

import Bean.UserBean;
import DataBase.CapitaBay;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

/**
 *
 * @author Jason
 */
@WebServlet(name = "UpdateStockPrice", urlPatterns = {"/UpdateStockPrice"})
public class UpdateStockPrice extends HttpServlet {
    

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        
        HttpSession session = req.getSession();
        
        UserBean userBean = (UserBean) session.getAttribute("userBean");
        if(userBean == null)
        {
            userBean = new UserBean();
            session.setAttribute("userBean", userBean);
        }
        
        try {
            Long ssn = userBean.getSocialSecurityNumber();
            String stockSymbol = req.getParameter("StockSymbol");
            Double sharePrice = new Double(req.getParameter("SharePrice"));
            
            LocalDate date = new LocalDate();
//            Date time = new Date();
            
            String query = "call updateStock('"+stockSymbol+"',"+sharePrice+",'"+date+"'"
                    + ",'"+date+"')";
            
            ResultSet results = CapitaBay.ExecuteQuery(query);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UpdateStockPrice.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        
    }


}
