/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagerQueries;

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
import org.json.JSONObject;

/**
 *
 * @author Jason
 */
@WebServlet(name = "RichestRep", urlPatterns = {"/RichestRep"})
public class RichestRep extends HttpServlet {


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
        response.setContentType("application/json");
                try {
            HttpSession session = request.getSession();
            UserBean userBean = (UserBean) session.getAttribute("userBean");
            if(userBean == null)
            {
                userBean = new UserBean();
                session.setAttribute("userBean", userBean);
            }
            
            Long ssn = userBean.getSocialSecurityNumber();
            
            //should set the thingy
            Long customerSSN = Long.parseLong(request.getParameter("customerSSN"));
            //should set the thingy
            String stockSymbol = request.getParameter("stockSymbol");
            
            
            String query = "call richestRep("+123456789+")";
            
            ResultSet res = CapitaBay.ExecuteQuery(query);
            
            String firstName = res.getString("Firstname");
            String lastName = res.getString("Lastname");
            Integer repSsn = res.getInt("SocialSecurityNumber");
            Double revenue =res.getDouble("Revenue");
            
                    System.out.println("");
            JSONObject richestRep =  new JSONObject();
                    richestRep.put("firstName",firstName);
                    richestRep.put("lastName",lastName);
                    richestRep.put("ssn",repSsn);
                    richestRep.put("revenue",revenue);
                    System.out.println(richestRep);
            response.getWriter().print(richestRep);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ListOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
