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
    private Date stockDate;

    public double getSharePrice() {
        return sharePrice;
    }

    public void setSharePrice(double sharePrice) {
        this.sharePrice = sharePrice;
    }

    public Date getStockDate() {
        return stockDate;
    }

    public void setStockDate(Date stockDate) {
        this.stockDate = stockDate;
    }

    public void set(ResultSet res) throws SQLException {
        this.sharePrice = res.getDouble("sharePrice");
        this.stockDate = res.getDate("stockDate");
    }

    public JSONObject getJson() {
        JSONObject json = new JSONObject();

        json.put("sharePrice", this.sharePrice);
        json.put("stockDate", this.stockDate.toString());
        
        return json;
    }

}
