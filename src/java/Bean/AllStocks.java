/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Jason
 */
public class AllStocks implements Serializable{
    
    private String stockSymbol;
    private String stockType;
    private String stockName;
    private double sharePrice;
    private String stockDate;
    private int numberOfSharesAvaliable;
    
    
//    private 

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

    public String getStockDate() {
        return stockDate;
    }

    public void setStockDate(String stockDate) {
        this.stockDate = stockDate;
    }

    public int getNumberOfSharesAvaliable() {
        return numberOfSharesAvaliable;
    }

    public void setNumberOfSharesAvaliable(int numberOfSharesAvaliable) {
        this.numberOfSharesAvaliable = numberOfSharesAvaliable;
    }
    
    public void set(ResultSet res) throws SQLException {
        this.stockSymbol = res.getString("StockSymbol");
        this.stockType = res.getString("stockType");
        this.stockName = res.getString("stockName");
        this.sharePrice = res.getDouble("SharePrice");
        this.stockDate = res.getString("stockDate");
        this.numberOfSharesAvaliable = res.getInt("NumberOfSharesAvaliable");
    }
    
}
