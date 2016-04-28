/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagerQueries;

import Bean.Revenue;
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
 * @author root
 */
@WebServlet(name="revenueByStockType", urlPatterns={"/revenueByStockType"})
public class revenueByStockType extends HttpServlet{
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException{
        HttpSession session = req.getSession();
        
        UserBean userBean = (UserBean) session.getAttribute("userBean");
        if(userBean == null)
        {
            userBean = new UserBean();
            session.setAttribute("userBean", userBean);
        }
                
        try {
            long e_ssn = userBean.getSocialSecurityNumber();
            String stockTyp = req.getParameter("StockType");
            
            String query = "call listRevenueByStockType("+e_ssn+","+stockTyp+");";
            ResultSet res = CapitaBay.ExecuteQuery(query);
            LinkedList<Revenue> result = new LinkedList<Revenue>();
            while(res.next()){
                Revenue current = new Revenue();
                current.set(res, "StockType");
                result.add(current);
            }
            req.setAttribute("revenueByStockType", result);
        } catch (SQLException ex) {
            Logger.getLogger(revenueByStockType.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(revenueByStockType.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
