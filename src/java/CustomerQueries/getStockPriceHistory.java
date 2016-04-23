/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomerQueries;

import DataBase.CapitaBay;
import Tables.Stock;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.joda.time.DateTime;

/**
 *
 * @author root
 */
@WebServlet(name="getStockHisory", urlPatterns={"/getStockHistory"})
public class getStockPriceHistory extends HttpServlet{
    
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws IOException, ServletException{
        try{
            DateTime cal = new DateTime();
            cal = cal.minusMonths(Integer.parseInt(req.getParameter("time")));
            String ss = req.getParameter("StockSymbol");
            String query = "call getStockHistory("+cal+","+ss+");";
            ResultSet res = CapitaBay.ExecuteQuery(query);
            LinkedList<Stock> result = new LinkedList<Stock>();
            int priceIndex   = res.findColumn("SharePrice");
            int dateIndex    = res.findColumn("StockDate");
            
            while(res.next()){
                Stock current = new Stock();
                current.setSharePrice(res.getDouble("SharePrice"));
                current.setStockDate(res.getDate("SharePrice"));
                result.add(current);                      
            }          
            req.setAttribute("stockHistory", result);

        } catch (SQLException ex) {
            Logger.getLogger(getStockPriceHistory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(getStockPriceHistory.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
}
