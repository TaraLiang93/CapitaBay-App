/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import helper.calendar;
import java.io.Serializable;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONObject;

/**
 *
 * @author Patrick
 */
public class ConditionalOrderHistory implements Serializable {

    private double sharePrice;
    private int orderID;
    private double percentage;

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
    
        
    
    public double getSharePrice() {
        return sharePrice;
    }

    public void setSharePrice(double sharePrice) {
        this.sharePrice = sharePrice;
    }

    
    public void set(ResultSet res) throws SQLException {
        this.sharePrice = res.getDouble("sharePrice");
    }

    public JSONObject getJson() {
        JSONObject json = new JSONObject();

        json.put("sharePrice", this.sharePrice);
        json.put("orderID", this.orderID);
        json.put("percentage",this.percentage);
        
        return json;
    }

}
