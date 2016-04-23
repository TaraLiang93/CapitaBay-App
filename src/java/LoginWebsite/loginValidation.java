/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginWebsite;

import Bean.customerRevenue;
import DataBase.CapitaBay;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author root
 */
@WebServlet(name="loginValidation", urlPatterns={"/loginValidation"})
public class loginValidation extends HttpServlet{
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws IOException, ServletException{
        HttpSession session = req.getSession();
        
        try{
            String username = req.getParameter("username"); 
            String password = req.getParameter("password");

            String query = "call validUser("+username+","+password+");";
            ResultSet res = CapitaBay.ExecuteQuery(query);
            int check = res.getInt("validUserReturn");
            if(check >0){
                req.setAttribute("userExist", "true");
            }
            else{
                req.setAttribute("userExist", "false");
            }
        } catch (SQLException ex) {
            Logger.getLogger(loginValidation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(loginValidation.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        
    }
    
}
