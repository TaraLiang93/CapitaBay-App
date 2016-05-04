/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tables;
import Bean.DataRepo;
import java.io.Serializable;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author root
 */
public class Stock implements Serializable, DataRepo{
    private String stockSymbol;
    private String stockType;
    private String stockName;
    private double sharePrice;
    private Date stockDate;
    private Time stockTime;
    private int numberOfSharesAvaliable;

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public String getStockType() {
        return stockType;
    }

    public void setStockType(String stockType) {
        this.stockType = stockType;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

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

    public Time getStockTime() {
        return stockTime;
    }

    public void setStockTime(Time stockTime) {
        this.stockTime = stockTime;
    }

    public int getNumberOfSharesAvaliable() {
        return numberOfSharesAvaliable;
    }

    public void setNumberOfSharesAvaliable(int numberOfSharesAvaliable) {
        this.numberOfSharesAvaliable = numberOfSharesAvaliable;
    }

    
    
    @Override
    public void set(ResultSet res) {
        try {
            this.stockSymbol = res.getString("StockSymbol");
            this.numberOfSharesAvaliable = res.getInt("NumberOfSharesAvaliable");
            this.stockType = res.getString("StockType");
            this.stockName = res.getString("StockName");
            this.stockTime = res.getTime("StockTime");
            this.stockDate = res.getDate("StockDate");
            this.sharePrice = res.getDouble("SharePrice");
        } catch (SQLException ex) {
            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public JSONObject getJson() {
        JSONObject json = new JSONObject();
        
        json.put("ss", this.stockSymbol);
        json.put("nos", this.numberOfSharesAvaliable);
        json.put("st", this.stockType);
        json.put("sn", this.stockName);
        json.put("s_time", this.stockTime.toString());
        json.put("s_date", this.stockDate.toString());
        json.put("sp", this.sharePrice);
        

        return json;
    }
    
}
