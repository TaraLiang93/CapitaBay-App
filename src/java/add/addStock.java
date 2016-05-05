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
@WebServlet(name = "addStock", urlPatterns = {"/addStock"})
public class addStock extends HttpServlet {

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
        UserBean userBean = (UserBean) session.getAttribute("userBean");
        if (userBean == null) {
            userBean = new UserBean();
            session.setAttribute("userBean", userBean);
        }
        LocalDate date = new LocalDate();
        String ss = request.getParameter("stockSymbol");
        String st = request.getParameter("stockType");
        String sn = request.getParameter("stockName");
        double price = Double.parseDouble(request.getParameter("price"));
        LocalTime s_time = new LocalTime();
        int nos = Integer.parseInt(request.getParameter("nos"));

        try {
            String query = "call addStockTable(\"" + ss + "\",\"" + st + "\",\"" + sn + "\"," + price + ",\"" + date + "\",+\"" + s_time + "\"," + nos + ");";
            System.out.println(query);
            CapitaBay.ExecuteQuery(query);
            session.setAttribute("userBean", userBean);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/loadStockListingsPage");
            dispatcher.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(addStock.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(addStock.class.getName()).log(Level.SEVERE, null, ex);
        }
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
