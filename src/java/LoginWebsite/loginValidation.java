/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginWebsite;

import Bean.UserBean;
import Bean.customerRevenue;
import DataBase.CapitaBay;
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
 * @author root
 */
@WebServlet(name="loginValidation", urlPatterns={"/loginValidation"})
public class loginValidation extends HttpServlet{
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws IOException, ServletException{
        HttpSession session = req.getSession();
        
        try{
            String username = req.getParameter("username"); 
            String password = req.getParameter("password");

            String query = "call validUser('"+username+"','"+password+"');";
            ResultSet res = CapitaBay.ExecuteQuery(query);
            if(res.next()){
                System.out.println("booo    "+res.getInt("SocialSecurityNumber"));
                int ssn = res.getInt("SocialSecurityNumber");
                String userName= res.getString("Username");
                UserBean userBean = new UserBean();
                userBean.setSocialSecurityNumber(ssn);
                userBean.setUsername(userName);
                session.setAttribute("userBean", userBean);
                req.setAttribute("errorStatus", false);
             
                RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/homepage.jsp");
                dispatcher.forward(req, resp);
            }
            else{
//                PrintWriter out = resp.getWriter();
//                out.print("Incorrect Username and Password.");
                req.setAttribute("error", "**Incorrect Username and Password**");
                req.setAttribute("errorStatus", true);
                System.out.println(req.getHeader("Referer"));
                String ref = req.getHeader("Referer");
                ref = ref.substring(ref.indexOf("/jsp"), ref.length());
                System.out.println("ref = " + ref);
                RequestDispatcher dispatcher = req.getRequestDispatcher(ref);
                dispatcher.forward(req, resp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(loginValidation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(loginValidation.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        
    }
    
}
