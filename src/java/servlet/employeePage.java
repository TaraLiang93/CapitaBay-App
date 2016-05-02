/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import Bean.AllStocks;
import Bean.UserBean;
import Bean.customerRevenue;
import DataBase.CapitaBay;
import ManagerQueries.RichestRep;
import Tables.Customer;
import Tables.Employee;
import Tables.Stock;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author Jason
 */
@WebServlet(name = "employeePage", urlPatterns = {"/employeePage"})
public class employeePage extends HttpServlet {


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
        
         HttpSession session = request.getSession();
        
        UserBean userBean = (UserBean) session.getAttribute("userBean");
        if(userBean == null)
        {
            userBean = new UserBean();
            session.setAttribute("userBean", userBean);
        }
                
        try {
            Long ssn = userBean.getSocialSecurityNumber();
            String query = "SELECT P.*, E.* FROM Employee E INNER JOIN Person P ON P.SocialSecurityNumber = E.SocialSecurityNumber;";
            ResultSet res = CapitaBay.ExecuteQuery(query);
            LinkedList<Employee> result = new LinkedList<Employee>();
            while(res.next()){
                Employee current = new Employee();
                current.set(res);
                result.add(current);
            };
            request.setAttribute("employees", result);
            request.setAttribute("searchResultsName", userBean.getFirstName());
            
            LinkedList<AllStocks> results = new LinkedList<>();
            
            query = "call listAllStocks("+userBean.getSocialSecurityNumber()+")";
            
            res = CapitaBay.ExecuteQuery(query);
            
            while(res.next()){
                AllStocks stocks = new AllStocks();
                stocks.set(res);
                results.add(stocks);
            }
            request.setAttribute("listAllStocks", results);
            
            query = "call mostPopularStocks("+userBean.getSocialSecurityNumber()+");";
            res = CapitaBay.ExecuteQuery(query);
            LinkedList<Stock> popStocks = new LinkedList<Stock>();
            while(res.next()){
                Stock current = new Stock();
                current.setStockName(res.getString("StockName"));
                current.setStockSymbol(res.getString("StockSymbol"));
                current.setNumberOfSharesAvaliable(res.getInt("NumberOfSharesAvaliable"));
                current.setSharePrice(res.getDouble("SharePrice"));
                popStocks.add(current);
            } 
            request.setAttribute("popularStocks", popStocks);
            
            
            
            query = "call richestCustomer("+userBean.getSocialSecurityNumber()+");";
            res = CapitaBay.ExecuteQuery(query);
            
                res.next();
                customerRevenue current = new customerRevenue();
                current.set(res);
            
            request.setAttribute("richestCustomer", current);
            
            
            query = "SELECT P.*, C.* FROM Customer C INNER JOIN Person P ON P.SocialSecurityNumber = C.SocialSecurityNumber;";
            
            LinkedList<Customer> customers = new LinkedList<>();
           
            res = CapitaBay.ExecuteQuery(query);
            
            while(res.next()){
                Customer customer = new Customer();
                customer.set(res);
                customers.add(customer);
            }
            
            request.setAttribute("customers", customers);
            
            

            
        } catch (SQLException ex) {
            Logger.getLogger(employeePage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(employeePage.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/employee.jsp");
        dispatcher.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
//        doGet(req,resp);
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
