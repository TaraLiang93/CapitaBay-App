/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmployeeQuery;

import helper.calendar;
import Bean.UserBean;
import helper.calendar;
import DataBase.CapitaBay;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "RecordOrder", urlPatterns = {"/RecordOrder"})
public class RecordOrder extends HttpServlet {
    
    @Override 
    protected void doPost(HttpServletRequest req, HttpServletResponse res) 
            throws ServletException, IOException{
        
        HttpSession session = req.getSession();
        UserBean userBean = (UserBean) session.getAttribute("userBean");
        
        if(userBean == null){
            userBean = new UserBean();
            session.setAttribute("userBean", userBean);
        }
        
        try{
            Integer e_ssn = userBean.getSocialSecurityNumber();
            Integer nos = Integer.parseInt(req.getParameter("NumberOfShare"));
            Integer c_ssn = Integer.parseInt(req.getParameter("customerSSN"));
            calendar cal = new calendar();
            String dat = cal.getDate();
            String o_time = cal.getTime();
            Integer an = Integer.parseInt(req.getParameter("accountNumber"));
            String stockSym = req.getParameter("stockSymbol");
            String orderType = req.getParameter("orderType");
            
            String query = "call recordOrder("+c_ssn+","+nos+","+o_time+","+e_ssn+
                    ","+an+","+stockSym+","+dat+","+orderType+")";

            CapitaBay.ExecuteQuery(query);

        }  
        catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(RecordOrder.class.getName()).log(Level.SEVERE, null, ex);
        } 
    
    }
    
    
}
