/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomerQueries;

import Bean.StockOrder;
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
@WebServlet(name="mostRecentStockAvailByType", urlPatterns={"/mostRecentStockAvailByType"})
public class orderByStockType extends HttpServlet{
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException{
        HttpSession session = req.getSession();
          UserBean userBean = (UserBean) session.getAttribute("userBean");
        if(userBean == null){
            userBean = new UserBean();
            session.setAttribute("userBean", userBean);
        }
        try{
            Long c_ssn = userBean.getSocialSecurityNumber();
            String stockType = req.getParameter("stockType");
            String query = "call mostRecentStockAvailByType("+c_ssn+","+stockType+");";
            ResultSet res = CapitaBay.ExecuteQuery(query);
            LinkedList<StockOrder> result = new LinkedList<StockOrder>();
            while(res.next()){
                StockOrder current = new StockOrder();
                current.set(res);
                result.add(current);
            }
            req.setAttribute("orderByStockType", result);
        } catch (SQLException ex) {
            Logger.getLogger(orderByStockType.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(orderByStockType.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
}


