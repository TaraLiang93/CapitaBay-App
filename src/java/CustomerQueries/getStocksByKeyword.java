/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomerQueries;

import Bean.UserBean;
import Bean.keywordOrder;
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
@WebServlet(name="getStockByKeyword", urlPatterns={"/getStockByKeyword"})
public class getStocksByKeyword extends HttpServlet{
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws
            IOException, ServletException{
        HttpSession session = req.getSession();
        
        UserBean userBean = (UserBean) session.getAttribute("userBean");
        if(userBean == null)
        {
            userBean = new UserBean();
            session.setAttribute("userBean", userBean);
        }
                
        try {
            int c_ssn = userBean.getSocialSecurityNumber();
            String keyword = req.getParameter("keyword");
            String query = "call getStocksByKeyword("+keyword+","+c_ssn+");";
            ResultSet res = CapitaBay.ExecuteQuery(query);
            LinkedList<keywordOrder> result = new LinkedList<keywordOrder>();
            while(res.next()){
                keywordOrder current = new keywordOrder();
                current.set(res);
                result.add(current);
            }
            req.setAttribute("keywordResult", result);
            
        } catch (SQLException ex) {
            Logger.getLogger(getStocksByKeyword.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(getStocksByKeyword.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
