/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlet;

import Bean.UserBean;
import CustomerQueries.getStocksByKeyword;
import DataBase.CapitaBay;
import Tables.Person;
import Tables.Stock;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.joda.time.DateTime;

/**
 *
 * @author Patrick
 */
@WebServlet(name = "loadSpecificStockPage", urlPatterns = {"/loadSpecificStockPage"})
public class loadSpecificStockPage extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        
        /*Stock stockBean = new Stock();
        stockBean.setStockSymbol(request.getParameter("val"));
        request.setAttribute("s", stockBean);
        request.getRequestDispatcher("/jsp/specificStock.jsp").forward(request, response);*/
        
        
        HttpSession session = request.getSession();
        
        UserBean userBean = (UserBean) session.getAttribute("userBean");
        if(userBean == null)
        {
            userBean = new UserBean();
            session.setAttribute("userBean", userBean);
        }
                
        try {
            String ss = request.getParameter("val");
            /*String query = "call getStockByKeyword('" +ss+"');";
            ResultSet res = CapitaBay.ExecuteQuery(query);
            LinkedList<Stock> result = new LinkedList<Stock>();
            while(res.next()){
                Stock current = new Stock();
                current = new Stock();
                current.set(res);
                result.add(current);
            };*/
            
            String query = "SELECT * FROM StockTable s WHERE s.StockSymbol = '" +ss+"';";
            ResultSet res = CapitaBay.ExecuteQuery(query);
            LinkedList<Stock> result = new LinkedList<Stock>();
            while(res.next()){
                Stock current = new Stock();
                current = new Stock();
                current.set(res);
                result.add(current);
            };
                       
            
            request.setAttribute("s", result.get(0));
            
            DateTime cal = new DateTime();
            
            String month = request.getParameter("month");
            if(month == null) {
                month = "6";
            }
            int iMonth = 6;
            try {
                iMonth = Integer.parseInt(month);
            }catch(NumberFormatException e) {
                iMonth = 6;
            }   
            cal = cal.minusMonths(iMonth);
           String date = cal.getYear() + "-" + cal.getMonthOfYear() + "-" + cal.getDayOfMonth();
            
            query = "call getStockHistory('"+ date +"','"+ss+"');";
            res = CapitaBay.ExecuteQuery(query);
            result = new LinkedList<Stock>();
            int priceIndex   = res.findColumn("SharePrice");
            int dateIndex    = res.findColumn("StockDate");
            
            while(res.next()){
                Stock current = new Stock();
                current.setSharePrice(res.getDouble("SharePrice"));
                current.setStockDate(res.getDate("StockDate"));
                result.add(current);                      
            }          
            request.setAttribute("h", result);
            request.setAttribute("m", month);
            
            query = "SELECT p.FirstName, p.LastName, p.SocialSecurityNumber FROM Person p, Employee e WHERE p.SocialSecurityNumber = e.SocialSecurityNumber;";
            res = CapitaBay.ExecuteQuery(query);
            LinkedList<Person> pResult = new LinkedList<Person>();
            
            while(res.next()){
                Person current = new Person();
                current.setFirstName(res.getString("FirstName"));
                current.setLastName(res.getString("LastName"));
                current.setSocialSecurityNumber(res.getInt("SocialSecurityNumber"));
                pResult.add(current);                      
            }
            
            request.setAttribute("p", pResult);
            
            request.setAttribute("a", userBean.getAccountNumbers());
            request.setAttribute("pos", userBean.getPosition());
          
            
            
        } catch (ClassNotFoundException |SQLException ex) {
            Logger.getLogger(getStocksByKeyword.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println();
        } 
  
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/specificStock.jsp");
        dispatcher.forward(request, response);
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
