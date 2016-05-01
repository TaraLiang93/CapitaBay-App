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
public class getNameWithSSN {
      public static String getName(Long SSN) throws SQLException, ClassNotFoundException{
        String queryM = "select FirstName, LastName From Person Where SocialSecurityNumber="+SSN+";";
        ResultSet res = CapitaBay.ExecuteQuery(queryM);
        String name = new String();
        if(res.next()){
            name = res.getString("FirstName")+" "+res.getString("LastName");
        }
        return name;
    }
    
}
