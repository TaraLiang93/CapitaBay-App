/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author root
 */
public class customerRevenue implements Serializable{
    private String FName;
    private String LName;
    private int ssn;
    private double revenue; 

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getLName() {
        return LName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public int getSsn() {
        return ssn;
    }

    public void setSsn(int ssn) {
        this.ssn = ssn;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }
    public void set(ResultSet res){
        try{
            this.FName = res.getString("FirstName");
            this.LName = res.getString("LastName");
            this.ssn = res.getInt("SocialSecurityNumber");
            this.revenue = res.getDouble("Revenue");
        }  catch (SQLException ex) {
            Logger.getLogger(customerRevenue.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public JSONObject getJson() {
       JSONObject json = new JSONObject();
       
       json.put("firstname", this.FName);
       json.put("lastname", this.LName);
       json.put("ssn", this.ssn);
       json.put("revenue", this.revenue);
       
       return json;
        
    }
    
}
