/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagerQueries;

import Bean.UserBean;
import Bean.customerRevenue;
import DataBase.CapitaBay;
import Tables.Stock;
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
@WebServlet(name="popularStock", urlPatterns={"/popularStock"})
public class popularStock extends HttpServlet{
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws IOException, ServletException{    
        HttpSession session = req.getSession();
        UserBean userBean = (UserBean) session.getAttribute("userBean");
        if(userBean == null){
            userBean = new UserBean();
            session.setAttribute("userBean", userBean);
        }
        
        try{
            long e_ssn = userBean.getSocialSecurityNumber();
            
            String query = "call mostPopularStocks("+e_ssn+");";
            ResultSet res = CapitaBay.ExecuteQuery(query);
            LinkedList<Stock> result = new LinkedList<Stock>();
            while(res.next()){
                Stock current = new Stock();
                current.setStockName(res.getString("StockName"));
                current.setStockSymbol(res.getString("StockSymbol"));
                current.setNumberOfSharesAvaliable(res.getInt("NumberOfSharesAvaliable"));
                current.setSharePrice(res.getDouble("SharePrice"));
                result.add(current);
            }
            req.setAttribute("popularStocks", result);
            
        } catch (SQLException ex) {
            Logger.getLogger(popularStock.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(popularStock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//    this.StockSymbol = res.getString("StockSymbol");
//            this.NumberOfSharesAvaliable = res.getInt("NumberOfSharesAvaliable");
//            this.StockType = res.getString("StockType");
//            this.StockName = res.getString("StockName");
//            this.StockTime = res.getTime("StockTime");
//            this.StockDate = res.getDate("StockDate");
//            this.SharePrice = res.getDouble("SharePrice");  
//    
//    CREATE PROCEDURE mostPopularStocks(IN e_ssn INTEGER)
//BEGIN
//	-- IF(SELECT E.SocialSecurityNumber FROM Employee E WHERE ) 
//	DECLARE currentEmployeePosition VARCHAR(12);
//
//	SELECT E.Position INTO currentEmployeePosition
//	FROM Employee E
//	WHERE E.SocialSecurityNumber = e_ssn;
//
//	IF currentEmployeePosition = 'Manager' THEN
//		SELECT O.StockSymbol,S.StockName, S.NumberOfSharesAvaliable,S.SharePrice
//		FROM Orders O
//		INNER JOIN StockTable S
//		ON S.StockSymbol = O.StockSymbol
//		GROUP BY O.StockSymbol
//		ORDER BY COUNT(*) DESC;
//	END IF;
//
//END $$
}
