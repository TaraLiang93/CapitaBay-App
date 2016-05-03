/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import Bean.CurrentStockHoldings;
import Bean.Transaction;
import Bean.UserBean;
import CustomerQueries.GetCurrentStockHoldings;
import DataBase.CapitaBay;
import EmployeeQuery.CustomerMailingList;
import Tables.Orders;
import Tables.Stock;
import helper.getNameWithSSN;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "customer", urlPatterns = {"/customer"})
public class customerServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserBean userBean = (UserBean) session.getAttribute("userBean");
        if(userBean == null) {
            userBean = new UserBean();
            session.setAttribute("userBean", userBean);
        }
        
        
        
        try {
            //        current Stock Holding
            LinkedList<CurrentStockHoldings> results = new LinkedList<CurrentStockHoldings>();
            String query = "call getCurrentStockHoldings(" + userBean.getSocialSecurityNumber() + ");";    
            System.out.println(query);
            ResultSet res = CapitaBay.ExecuteQuery(query);            
            while(res.next()) {
                CurrentStockHoldings currentStockHoldings = new CurrentStockHoldings();
                currentStockHoldings.set(res);
                String ss = res.getString("stockSymbol");
                String price = "select StockTable.SharePrice from StockTable where StockTable.StockSymbol=\""+ss+"\";";
                System.out.println(price);
                ResultSet resu = CapitaBay.ExecuteQuery(price);
                if(resu.next()){
                    currentStockHoldings.setCurrentPrice(resu.getDouble("SharePrice"));
                }
                results.add(currentStockHoldings);               
            }
            request.setAttribute("currentStockHoldings", results);
         
            
            //get Order History
            Long c_ssn = userBean.getSocialSecurityNumber();
            query = "call OrderHistory("+c_ssn+");";
            res = CapitaBay.ExecuteQuery(query);
            LinkedList<Transaction> result = new LinkedList<Transaction>();
            while(res.next()){
                Transaction current = new Transaction();
                current.set(res);
                query = "select Orders.NumberOfShares from Orders where OrderID ="+current.getTransId()+";";
                ResultSet res1 = CapitaBay.ExecuteQuery(query);
                if(res1.next()){
                    current.setNos(res1.getInt("NumberOfShares"));
                }
                String name = getNameWithSSN.getName(c_ssn);
                result.add(current);
            }
            request.setAttribute("orderHistory", result);
        
        
            //get stock suggestions
            query = "call getStockSuggestionList("+c_ssn+");";
            res= CapitaBay.ExecuteQuery(query);
            LinkedList<Stock> resultS = new LinkedList<Stock>();
            while(res.next()){
                Stock currentStock = new Stock();
                currentStock.set(res);
                resultS.add(currentStock);                
            }
            request.setAttribute("stockSuggestion", resultS);

        
        }
        
        
        
        
        
        
        
        
        catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(GetCurrentStockHoldings.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/customer.jsp");
        dispatcher.forward(request, response);
    }

}
