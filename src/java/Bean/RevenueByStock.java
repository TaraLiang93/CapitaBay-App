/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONObject;

/**
 *
 * @author Jason
 */
public class RevenueByStock implements Serializable, DataRepo{

    private String stockSymbol;
    private double revenue;

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    @Override
    public void set(ResultSet res) throws SQLException {
        this.stockSymbol = res.getString("StockSymbol");
        this.revenue = res.getDouble("Revenue");
        
    }

    @Override
    public JSONObject getJson() {
        JSONObject json = new JSONObject();
        
        json.put("ss", this.stockSymbol);
        json.put("rev", this.revenue);
        
        return json;
    }
    
    
}
