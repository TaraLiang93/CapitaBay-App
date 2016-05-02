/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import DataBase.CapitaBay;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author root
 */
public class checkExist {
    
    public static boolean checkPerson(long SSN) throws SQLException, ClassNotFoundException{
        String queryP = "select count(*) From Person Where SocialSecurityNumber="+SSN+";";
            ResultSet resP = CapitaBay.ExecuteQuery(queryP);
            if(resP.next()){
                int count = resP.getInt("count(*)");
                if(count > 0){
                    return true;
                }
                else{
                    return false;
                }
            }
            return true;
    }
    
    public static boolean checkLocation(int zip, String city, String state) throws SQLException, ClassNotFoundException {
          String queryLocation = "select count(*) From Location Where ZipCode=\""+zip+"\" AND City=\""+city+"\" AND State=\""+state+"\";";
            ResultSet resLocation = CapitaBay.ExecuteQuery(queryLocation);
            if(resLocation.next()){
                int count = resLocation.getInt("count(*)");
                if(count > 0){
                    return true;
                }
                else{
                    return false;
                }
            }
            return true;
    }
    
    public static boolean checkCustomer(long SSN) throws SQLException, ClassNotFoundException{
        String queryC = "select count(*) From Customer Where SocialSecurityNumber=\""+SSN+"\";";
        ResultSet resC = CapitaBay.ExecuteQuery(queryC);
        if(resC.next()){
            int count = resC.getInt("count(*)");
            if(count > 0){
                return true;
            }
            else{
                return false;
            }
        }
        return true;
    }
    
    public static boolean checkUserName(String username) throws SQLException, ClassNotFoundException{
        String query = "select count(*) From Person Where Username=\""+username+"\";";
        ResultSet res = CapitaBay.ExecuteQuery(query);
        if(res.next()){
            int count = res.getInt("count(*)");
            if(count > 0){
                return true;
            }
            else{
                return false;
            }
        }
        return true;
    }
    
    public static boolean checkEmail(String email) throws SQLException, ClassNotFoundException{
        String query = "select count(*) From Customer Where Email=\""+email+"\";";
        ResultSet res = CapitaBay.ExecuteQuery(query);
        if(res.next()){
            int count = res.getInt("count(*)");
            if(count > 0){
                return true;
            }
            else{
                return false;
            }
        }
        return true;
    }
    
    public static boolean checkIsCustomer(Long SSN) throws SQLException, ClassNotFoundException{
        String query = "select count(*) From Customer Where SocialSecurityNumber="+SSN+";";
        ResultSet res = CapitaBay.ExecuteQuery(query);
        if(res.next()){
            int count = res.getInt("count(*)");
            if(count > 0){
                return true;
            }
            else{
                return false;
            }
        }
        return true;
    }
    
      
    public static String checkEmployee(Long SSN) throws SQLException, ClassNotFoundException{
        int countM=-1;
        String queryM = "select count(*) From Employee Where SocialSecurityNumber="+SSN+" AND Position=\"Manager\";";
        ResultSet res = CapitaBay.ExecuteQuery(queryM);
        if(res.next()){
            countM = res.getInt("count(*)");
        }
        
        int countCR=-1;
        String queryCR = "select count(*) from Employee where SocialSecurityNumber="+SSN+" AND Position=\"CustomerRep\";";
        ResultSet resCR = CapitaBay.ExecuteQuery(queryCR);
        if(resCR.next()){
            countCR = resCR.getInt("count(*)");
        }
        
        if((countCR>0)){
            return "CustomerRep";
        }
        else if((countM>0)){
            return "Manager";
        }
        
        return null;
    }
    
}
