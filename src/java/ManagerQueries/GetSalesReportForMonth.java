/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagerQueries;

import Bean.SalesReportForMonth;
import Bean.UserBean;
import DataBase.CapitaBay;
import java.io.IOException;
import java.io.PrintWriter;
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
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Jason
 */
@WebServlet(name = "GetSalesReportForMonth", urlPatterns = {"/GetSalesReportForMonth"})
public class GetSalesReportForMonth extends HttpServlet {

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
            
            LinkedList<SalesReportForMonth> results = new LinkedList<>();
                        
            Long employeeSSN = userBean.getSocialSecurityNumber();
            Integer monthNumber = Integer.parseInt(request.getParameter("month"));
            
            String query = "call getSalesReportForMonth("+employeeSSN+","+monthNumber+")";
            
            ResultSet res = CapitaBay.ExecuteQuery(query);
            
            JSONObject json = new JSONObject();
            JSONArray jarr = new JSONArray();
            
            while(res.next())
            {
                SalesReportForMonth salesReportForMonth = new SalesReportForMonth();
                salesReportForMonth.set(res);
                jarr.put(salesReportForMonth.getJson());
                
            }
            json.put("salesReport",jarr);
        } catch (ClassNotFoundException| SQLException ex) {
            Logger.getLogger(GetSalesReportForMonth.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
