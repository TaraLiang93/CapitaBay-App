/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package add;

import Bean.UserBean;
import DataBase.CapitaBay;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;


/**
 *
 * @author root
 */
@WebServlet(name="newCustomer", urlPatterns={"/newCustomer"})
public class newCustomer extends HttpServlet{
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws IOException, ServletException{
        
        HttpSession session = req.getSession();
        
        try{          
            String email= req.getParameter("email");
            String username = req.getParameter("username"); 
            String password = req.getParameter("password");
            String password2 = req.getParameter("password2");
            
            String firstName = req.getParameter("firstName");
            String lastName = req.getParameter("lastName");
            
            Double phoneNum = Double.parseDouble(req.getParameter("phoneNum"));
            Integer SSN = Integer.parseInt(req.getParameter("ssn"));
            Double creditCard = Double.parseDouble(req.getParameter("creditCard"));
        
            String address = req.getParameter("address");
            String city = req.getParameter("city");
            String state = req.getParameter("state");
            Integer zip = Integer.parseInt(req.getParameter("zip"));
            
                String ref = req.getHeader("Referer");
                ref = ref.substring(ref.indexOf("/jsp"), ref.length());
                
            if(password.equals(password2) == false){
            //check if the re-enter password and password is the same if not send back to the orginial page with error message
                req.setAttribute("error", "Password and re-entry of password are not the same.");
                req.setAttribute("errorStatus", true);
                RequestDispatcher dispatcher = req.getRequestDispatcher(ref);
                dispatcher.forward(req, resp);
            }
            
            int countC = 1;
            int countL = 0;
            int countP = -1;
            String queryLocation = "select count(*) From Location Where ZipCode=\""+zip+"\" AND City=\""+city+"\" AND State=\""+state+"\";";
            ResultSet resLocation = CapitaBay.ExecuteQuery(queryLocation);
            if(resLocation.next()){
                countL = resLocation.getInt("count(*)");
            }
            
            String queryC = "select count(*) From Customer Where SocialSecurityNumber=\""+SSN+"\";";
            ResultSet resC = CapitaBay.ExecuteQuery(queryC);
            if(resC.next()){
                countC = resC.getInt("count(*)");
            }
            
            String queryP = "select count(*) From Person Where SocialSecurityNumber=\""+SSN+"\";";
            ResultSet resP = CapitaBay.ExecuteQuery(queryP);
            if(resP.next()){
                countP = resP.getInt("count(*)");
            }
            
            if((countC>0)){
                req.setAttribute("error", "The User existed.");
                req.setAttribute("errorStatus", true);
                RequestDispatcher dispatcher = req.getRequestDispatcher(ref);
                dispatcher.forward(req, resp);
            }
            
            if(countL == 0){// add location 
                String addLocation = "call addLocation("+zip+",\""+city+"\",\""+state+"\")";
                ResultSet res = CapitaBay.ExecuteQuery(addLocation);

            }
            
            if(countP == 0){//add Person
                String addPerson = "call addPerson(\""+firstName+"\",\""+lastName+"\",\""+address+"\","+phoneNum+","+zip+","+SSN+"\",\""+username+"\",\""+password+"\")";
                ResultSet res = CapitaBay.ExecuteQuery(addPerson);
            }
            
            String addCustomer = "call addCustomer("+SSN+",1"+"\",\""+creditCard+"\",\""+email+"\")";
            ResultSet res = CapitaBay.ExecuteQuery(addCustomer);

            UserBean userBean = new UserBean();
            userBean.setSocialSecurityNumber(SSN);
            userBean.setUsername(username);
            session.setAttribute("userBean", userBean);
            req.setAttribute("errorStatus", false);
             
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/homepage.jsp");
            dispatcher.forward(req, resp);           
            
        }catch (SQLException ex) {
            Logger.getLogger(newCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(newCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    
}
