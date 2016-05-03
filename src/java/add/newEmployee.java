/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package add;

import Bean.UserBean;
import DataBase.CapitaBay;
import helper.checkExist;
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

/**
 *
 * @author Jason
 */
@WebServlet(name = "newEmployee", urlPatterns = {"/newEmployee"})
public class newEmployee extends HttpServlet {
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
        HttpSession session = request.getSession();

        
        try{
            String email= request.getParameter("email");
            String username = request.getParameter("username"); 
            String password = request.getParameter("password");
            String password2 = request.getParameter("password2");
        
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            
            Long phoneNum = Long.parseLong(request.getParameter("phoneNum"));
            Long SSN = Long.parseLong(request.getParameter("ssn"));
            String position = (request.getParameter("position"));
            Double hourlyRate = Double.parseDouble(request.getParameter("newHourlyRate"));
           
            String address = request.getParameter("address");
            String city = request.getParameter("city");
            String state = request.getParameter("state");
            Integer zip = Integer.parseInt(request.getParameter("zip"));
        
        //a list of result from checking if there exists a tuple with duplicate info in DB
            String checkEmployee = checkExist.checkEmployee(SSN);
            boolean checkLocation = checkExist.checkLocation(zip,city, state);
            boolean checkPerson = checkExist.checkPerson(SSN);
            boolean checkUsername = checkExist.checkUserName(username);
            boolean checkEmail = checkExist.checkEmail(email);
        
            //a list of return error or insertion call to DB or to the front
            if((password.equals(""))||(password==null)){
                throw new Exception();
            }
            
            if(checkEmployee != null){//there already exist such a user
                throw new Exception();
            }
            
            if((checkEmail== true) || (checkUsername==true)){
                throw new Exception();
            }
            
            if((checkLocation == false)){// add location 
                String addLocation = "call addLocation("+zip+",\""+city+"\",\""+state+"\")";
                ResultSet res = CapitaBay.ExecuteQuery(addLocation);
            }
            
            if((checkPerson == false)){//add Person
                String addPerson = "call addPerson(\""+firstName+"\",\""+lastName+"\",\""+address+"\","+phoneNum+","+zip+","+SSN+",\""+username+"\",\""+password+"\")";
                System.out.println(addPerson);
                ResultSet res = CapitaBay.ExecuteQuery(addPerson);
            }
            
                
                LocalDate date = new LocalDate();
                String addCustomer = "call addEmployee("+SSN+",\""+position+"\",\""+date+"\","+hourlyRate+");";
                System.out.println(addCustomer);
                ResultSet res = CapitaBay.ExecuteQuery(addCustomer);
    
        } catch (ClassNotFoundException | SQLException  ex) {
            Logger.getLogger(newEmployee.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            
        } catch (Exception ex){
             Logger.getLogger(newEmployee.class.getName()).log(Level.SEVERE, null, ex);
             response.sendError(HttpServletResponse.SC_NOT_FOUND);
        } 
        
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
