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
public class ListRevenueByStockType implements Serializable, DataRepo{
    
        private String stockType;
        private double revenue;

    public String getStockType() {
        return stockType;
    }

    public void setStockType(String stockType) {
        this.stockType = stockType;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    @Override
    public void set(ResultSet res) throws SQLException {
        this.stockType = res.getString("StockType");
        this.revenue = res.getDouble("Revenue");
    }

    @Override
    public JSONObject getJson() {
        JSONObject json = new JSONObject();
        
        json.put("st", this.stockType);
        json.put("rev", this.revenue);
        
        return json;
    }

        
}
