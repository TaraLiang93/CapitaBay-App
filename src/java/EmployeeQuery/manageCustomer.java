/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmployeeQuery;

import Bean.UserBean;
import DataBase.CapitaBay;
import ManagerQueries.UpdateStockPrice;
import java.io.IOException;
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

/**
 *
 * @author root
 */
@WebServlet(name = "manageCustomer", urlPatterns = {"/manageCustomer"})
public class manageCustomer extends HttpServlet{
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)throws
            ServletException, IOException{
        
        HttpSession session = req.getSession();
        
        UserBean userBean = (UserBean) session.getAttribute("userBean");
        if(userBean == null){
            userBean = new UserBean();
            session.setAttribute("userBean", userBean);
        }
        
        try{
            Long e_ssn = userBean.getSocialSecurityNumber();
            String request = req.getParameter("request");
            Long c_ssn = Long.parseLong(req.getParameter("customerSSN"));
            Double rate = Double.parseDouble(req.getParameter("customerRate"));
            String ccs = req.getParameter("creditCardNum");
            String email = req.getParameter("email");
            
            String query = "call manageCustomers("+request+","+e_ssn+","+c_ssn+
                    ","+rate+","+ccs+","+email+")";
            
            ResultSet results = CapitaBay.ExecuteQuery(query);
        }catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UpdateStockPrice.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }

        
    
}
