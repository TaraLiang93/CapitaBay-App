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
 * @author Patrick
 */
public class CurrentStockHoldings implements Serializable{
    private String stockSymbol;
    private int totalShares;
    private int accountNumber;
    private double currentPrice;

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    
    
    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int AccountNumber) {
        this.accountNumber = AccountNumber;
    }
        
    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public int getTotalShares() {
        return totalShares;
    }

    public void setTotalShares(int totalShares) {
        this.totalShares = totalShares;
    }

    public void set(ResultSet res) throws SQLException {
        this.stockSymbol = res.getString("stockSymbol");
        this.totalShares = res.getInt("TotalShares");
        this.accountNumber = res.getInt("AccountNumber");
    }
    
    
}
