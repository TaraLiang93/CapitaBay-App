/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagerQueries;

import Bean.Revenue;
import Bean.UserBean;
import Bean.customerRevenue;
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
@WebServlet(name="RevenueByCustomer", urlPatterns={"/RevenueByCustomer"})
public class RevenueByCustomer extends HttpServlet{
    
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
            Long e_ssn = userBean.getSocialSecurityNumber();
            Long c_ssn = Long.parseLong(req.getParameter("val"));
            
            String query = "call listRevenueCustomer("+e_ssn+","+c_ssn+");";
            ResultSet res = CapitaBay.ExecuteQuery(query);
//            LinkedList<customerRevenue> result = new LinkedList<customerRevenue>();
            
            JSONObject json = new JSONObject();
            JSONArray jarr = new JSONArray();
            
            while(res.next()){
                customerRevenue current = new customerRevenue();
                current.set(res);
                jarr.put(current.getJson());
            }
            
            json.put("table", "revCustomerTable");
            json.put("customer", jarr);
//            req.setAttribute("revenueByCustomer", result);
        } catch (SQLException ex) {
            Logger.getLogger(RevenueByCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RevenueByCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
//    CREATE PROCEDURE listRevenueCustomer(IN e_ssn INTEGER,IN c_ssn INTEGER)
//BEGIN
//	-- IF(SELECT E.SocialSecurityNumber FROM Employee E WHERE ) 
//	DECLARE currentEmployeePosition VARCHAR(12);
//
//	SELECT E.Position INTO currentEmployeePosition
//	FROM Employee E
//	WHERE E.SocialSecurityNumber = e_ssn;
//
//	IF currentEmployeePosition = 'Manager' THEN
//		SELECT P.FirstName,P.LastName,O.SocialSecurityNumber,SUM(O.NumberOfShares*O.SharePrice) AS Revenue		FROM Orders O
//		INNER JOIN Person P
//		ON P.SocialSecurityNumber = O.SocialSecurityNumber
//		WHERE O.SocialSecurityNumber = c_ssn AND O.OrderType = 'sell';
//	END IF;
//
//END $$
//    
}
