/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tables;
import java.io.Serializable;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class Stock implements Serializable{
    private String StockSymbol;
    private String StockType;
    private String StockName;
    private double SharePrice;
    private Date StockDate;
    private Time StockTime;
    private int NumberOfSharesAvaliable;

    public String getStockSymbol() {
        return StockSymbol;
    }

    public void setStockSymbol(String StockSymbol) {
        this.StockSymbol = StockSymbol;
    }

    public String getStockType() {
        return StockType;
    }

    public void setStockType(String StockType) {
        this.StockType = StockType;
    }

    public String getStockName() {
        return StockName;
    }

    public void setStockName(String StockName) {
        this.StockName = StockName;
    }

    public double getSharePrice() {
        return SharePrice;
    }

    public void setSharePrice(double SharePrice) {
        this.SharePrice = SharePrice;
    }

    public Date getStockDate() {
        return StockDate;
    }

    public void setStockDate(Date StockDate) {
        this.StockDate = StockDate;
    }

    public Time getStockTime() {
        return StockTime;
    }

    public void setStockTime(Time StockTime) {
        this.StockTime = StockTime;
    }

    public int getNumberOfSharesAvaliable() {
        return NumberOfSharesAvaliable;
    }

    public void setNumberOfSharesAvaliable(int NumberOfSharesAvaliable) {
        this.NumberOfSharesAvaliable = NumberOfSharesAvaliable;
    }
    
    
    public void set(ResultSet res) {
        try {
            this.StockSymbol = res.getString("StockSymbol");
            this.NumberOfSharesAvaliable = res.getInt("NumberOfSharesAvaliable");
            this.StockType = res.getString("StockType");
            this.StockName = res.getString("StockName");
            this.StockTime = res.getTime("StockTime");
            this.StockDate = res.getDate("StockDate");
            this.SharePrice = res.getDouble("SharePrice");
        } catch (SQLException ex) {
            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
