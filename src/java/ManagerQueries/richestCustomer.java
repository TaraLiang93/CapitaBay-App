/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagerQueries;

import Bean.UserBean;
import Bean.customerRevenue;
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
@WebServlet(name="richestCustomer", urlPatterns={"/richestCustomer"})
public class richestCustomer extends HttpServlet{
    
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws IOException, ServletException{    
        HttpSession session = req.getSession();
        UserBean userBean = (UserBean) session.getAttribute("userBean");
        if(userBean == null){
            userBean = new UserBean();
            session.setAttribute("userBean", userBean);
        }
        
        try{
            int e_ssn = userBean.getSocialSecurityNumber();
            
            String query = "call richestCustomer("+e_ssn+");";
            ResultSet res = CapitaBay.ExecuteQuery(query);
            LinkedList<customerRevenue> result = new LinkedList<customerRevenue>();
            while(res.next()){
                customerRevenue current = new customerRevenue();
                current.set(res);
                result.add(current);
            }
            req.setAttribute("richestCustomer", result);
            
        } catch (SQLException ex) {
            Logger.getLogger(RevenueByCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RevenueByCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//    CREATE PROCEDURE richestCustomer(IN e_ssn INTEGER)
//BEGIN
//	-- IF(SELECT E.SocialSecurityNumber FROM Employee E WHERE ) 
//	DECLARE currentEmployeePosition VARCHAR(12);
//
//	SELECT E.Position INTO currentEmployeePosition
//	FROM Employee E
//	WHERE E.SocialSecurityNumber = e_ssn;
//
//	IF currentEmployeePosition = 'Manager' THEN
//		SELECT O.SocialSecurityNumber,P.FirstName,P.LastName,SUM(O.NumberOfShares*O.SharePrice) AS Revenue
//		FROM Orders O 
//		INNER JOIN Person P
//		ON P.SocialSecurityNumber = O.SocialSecurityNumber
//		WHERE O.OrderType = 'sell'
//		GROUP BY O.SocialSecurityNumber
//		ORDER BY Revenue DESC
//		LIMIT 1;
//	END IF;
//
//END $$
}
