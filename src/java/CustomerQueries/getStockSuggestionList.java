/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomerQueries;

import Bean.CurrentStockHoldings;
import Bean.UserBean;
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
@WebServlet(name="getStockSuggestionList", urlPatterns ={"/getStockSuggestionList"})
public class getStockSuggestionList extends HttpServlet{
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException{
        HttpSession session = req.getSession();
        UserBean userBean = (UserBean) session.getAttribute("userBean");
        if(userBean == null){
            userBean = new UserBean();
            session.setAttribute("userBean", userBean);
        }
        try{
            String query = "call getStockSuggestionList("+ 
                    Long.parseLong(req.getParameter("customerSSN"))+");";
            ResultSet res= CapitaBay.ExecuteQuery(query);
            LinkedList<Stock> result = new LinkedList<Stock>();
            while(res.next()){
                Stock currentStock = new Stock();
                currentStock.set(res);
                result.add(currentStock);                
            }
            req.setAttribute("stockSuggestion", result);
        } catch (SQLException | ClassNotFoundException ex){
            Logger.getLogger(GetCurrentStockHoldings.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
   }