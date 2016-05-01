/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlet;

import CustomerQueries.getStocksByKeyword;
import DataBase.CapitaBay;
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

/**
 *
 * @author Patrick
 */
@WebServlet(name = "loadStockListingsPage", urlPatterns = {"/loadStockListingsPage"})
public class loadStockListingsPage extends HttpServlet {

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
//            response.setContentType("text/html;charset=UTF-8");
            
        try {    
            String query = "SELECT DISTINCT S.StockSymbol, S.StockType, S.StockName, S.SharePrice,S.NumberOfSharesAvaliable FROM StockTable S;";
            ResultSet res = CapitaBay.ExecuteQuery(query);
            LinkedList<Stock> result = new LinkedList<Stock>();
            while(res.next()){
                Stock current = new Stock();
                current = new Stock();
                current.set(res);
                current.setSharePrice(res.getDouble("SharePrice"));
                result.add(current);
            };
            request.setAttribute("s", result);
        } catch (ClassNotFoundException |SQLException ex) {
            Logger.getLogger(getStocksByKeyword.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println();
        }
            
        request.getRequestDispatcher("/jsp/stockListings.jsp").forward(request, response);
        
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
