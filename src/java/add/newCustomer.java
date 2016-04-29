/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package add;

import Bean.UserBean;
import DataBase.CapitaBay;
import helper.checkExist;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;


/**
 *
 * @author root
 */
@WebServlet(name="newCustomer", urlPatterns={"/newCustomer"})
public class newCustomer extends HttpServlet{
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws IOException, ServletException{
                
        HttpSession session = req.getSession();
        boolean haveError=false;
        
        try{  
            String email= req.getParameter("email");
            String username = req.getParameter("username"); 
            String password = req.getParameter("password");
            String password2 = req.getParameter("password2");
            
            String firstName = req.getParameter("firstName");
            String lastName = req.getParameter("lastName");
            
            Long phoneNum = Long.parseLong(req.getParameter("phoneNum"));
            Long SSN = Long.parseLong(req.getParameter("ssn"));
            Long creditCard = Long.parseLong(req.getParameter("creditCard"));
        
            String address = req.getParameter("address");
            String city = req.getParameter("city");
            String state = req.getParameter("state");
            Integer zip = Integer.parseInt(req.getParameter("zip"));
          
            //a list of result from checking if there exists a tuple with duplicate info in DB
            boolean checkCustomer = checkExist.checkCustomer(SSN);
            boolean checkLocation = checkExist.checkLocation(zip,city, state);
            boolean checkPerson = checkExist.checkPerson(SSN);
            boolean checkUsername = checkExist.checkUserName(username);
            boolean checkEmail = checkExist.checkEmail(email);
           
            //a list of return error or insertion call to DB or to the front
            if((password.equals(""))||(password==null)){
                req.setAttribute("error", "Cannot have an empty password");
                req.setAttribute("errorStatus", true);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/signupPage.jsp");
                dispatcher.forward(req, resp);
                haveError= true;
            }    
                
            if(password.equals(password2) == false){
            //check if the re-enter password and password is the same if not send back to the orginial page with error message
                req.setAttribute("error", "Password and re-entry of password are not the same.");
                req.setAttribute("errorStatus", true);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/signupPage.jsp");
                dispatcher.forward(req, resp);
                haveError= true;
            }
            
            if(checkCustomer == true){//there already exist such a user
                req.setAttribute("error", "The User existed.");
                req.setAttribute("errorStatus", true);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/signupPage.jsp");
                dispatcher.forward(req, resp);
                haveError= true;
            }
            
            if((checkEmail== true) || (checkUsername==true)){
                req.setAttribute("error", "The Username/Email is taken");
                req.setAttribute("errorStatus", true);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/signupPage.jsp");
                dispatcher.forward(req, resp);
                haveError= true;
            }
            
            if((checkLocation == false)&& (haveError ==false)){// add location 
                String addLocation = "call addLocation("+zip+",\""+city+"\",\""+state+"\")";
                ResultSet res = CapitaBay.ExecuteQuery(addLocation);
            }
            
            if((checkPerson == false)&& (haveError ==false)){//add Person
                String addPerson = "call addPerson(\""+firstName+"\",\""+lastName+"\",\""+address+"\","+phoneNum+","+zip+","+SSN+",\""+username+"\",\""+password+"\")";
                System.out.println(addPerson);
                String test = req.getParameter("phoneNum");
                System.out.println("phone num is "+req.getParameter("phoneNum"));
                ResultSet res = CapitaBay.ExecuteQuery(addPerson);
            }
            
            if((haveError == false)){
                String addCustomer = "call addCustomer("+SSN+",1,\""+creditCard+"\",\""+email+"\")";
                ResultSet res = CapitaBay.ExecuteQuery(addCustomer);

                //add the default stock account num 1 for all new users
                LocalDate date = new LocalDate();
                String addCustomerAccount= "call addStockAccount("+SSN+",1,\""+date+"\");";
                res = CapitaBay.ExecuteQuery(addCustomerAccount);

                //set the username and ssn into the bean
                UserBean userBean = new UserBean();
                userBean.setSocialSecurityNumber(SSN);
                userBean.setUsername(username);
                session.setAttribute("userBean", userBean);
                req.setAttribute("errorStatus", false);

                //return to homepage TEMPORARY!!!, it should redirect to user's profile page
                RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/homepage.jsp");
                dispatcher.forward(req, resp);      
            }
            
        }catch (Exception ex) {
            Logger.getLogger(newCustomer.class.getName()).log(Level.SEVERE, null, ex);
                req.setAttribute("error", "Cannot Create User");
                req.setAttribute("errorStatus", true);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/signupPage.jsp");
                dispatcher.forward(req, resp);
        }
        
    }
    
}
