/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomerQueries;

import DataBase.CapitaBay;
import EmployeeQuery.CustomerMailingList;
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

/**
 *
 * @author root
 */
@WebServlet(name="bestSellingStock", urlPatterns={"/bestSellingStock"})
public class bestSellingStock extends HttpServlet{
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException{
        try{
            String query = "call listBestSellingStock();";
            ResultSet res = CapitaBay.ExecuteQuery(query);
            LinkedList<Stock> result = new LinkedList<Stock>();
             while(res.next()){
                Stock current = new Stock();
                current.setStockSymbol(res.getString("StockSymbol"));
                result.add(current);
            }
            req.setAttribute("bestSellerList", result);
        }catch (SQLException ex) {
            Logger.getLogger(CustomerMailingList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CustomerMailingList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
