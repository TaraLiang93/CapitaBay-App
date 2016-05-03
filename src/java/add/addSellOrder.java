/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package add;

import Bean.UserBean;
import DataBase.CapitaBay;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

/**
 *
 * @author root
 */
@WebServlet(name = "addSellOrder", urlPatterns = {"/addSellOrder"})
public class addSellOrder extends HttpServlet {

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
        
        HttpSession session = request.getSession();
        try{
            UserBean userBean = (UserBean) session.getAttribute("userBean");
            if(userBean == null){
                userBean = new UserBean();
                session.setAttribute("userBean", userBean);
            }
            long ssn = userBean.getSocialSecurityNumber();
            int nos = Integer.parseInt(request.getParameter("number-shares"));
            LocalTime o_time = new LocalTime();
            int an = Integer.parseInt(request.getParameter("an"));
            String ss = request.getParameter("ss");
            LocalDate dat = new LocalDate();
            String ot = "sell";
            long e_ssn = 0;
            
            
            String getEmployee = "select EmployeeSSN from Orders where SocialSecurityNumber = "+ssn+" AND StockSymbol=\""+ss+"\" LIMIT 1;";
            ResultSet res = CapitaBay.ExecuteQuery(getEmployee);
            if(res.next()){
                e_ssn = res.getLong("EmployeeSSN");
            }
            
            

            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(addSellOrder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(addSellOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//        CREATE PROCEDURE addMarketOnClose(IN ssn INTEGER, IN nos INTEGER, IN o_time TIME, 
//		IN e_ssn INTEGER,IN an INTEGER, IN ss VARCHAR(10), IN dat DATE, IN m_ot VARCHAR(32))
//        
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/customer");
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
