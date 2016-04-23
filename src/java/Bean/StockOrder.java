/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

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
public class StockOrder implements Serializable{
    private String stockSymbol;
    private String StockName;
    private String StockType;
    private int NumberOfSharesAvaliable;
    private int customerSSN;
    private int accountNumber;
    private int numberOfShares;
    private double sharePrice;
    private String orderType;

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public String getStockName() {
        return StockName;
    }

    public void setStockName(String StockName) {
        this.StockName = StockName;
    }

    public String getStockType() {
        return StockType;
    }

    public void setStockType(String StockType) {
        this.StockType = StockType;
    }

    public int getNumberOfSharesAvaliable() {
        return NumberOfSharesAvaliable;
    }

    public void setNumberOfSharesAvaliable(int NumberOfSharesAvaliable) {
        this.NumberOfSharesAvaliable = NumberOfSharesAvaliable;
    }

    public int getCustomerSSN() {
        return customerSSN;
    }

    public void setCustomerSSN(int customerSSN) {
        this.customerSSN = customerSSN;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getNumberOfShares() {
        return numberOfShares;
    }

    public void setNumberOfShares(int numberOfShares) {
        this.numberOfShares = numberOfShares;
    }

    public double getSharePrice() {
        return sharePrice;
    }

    public void setSharePrice(double sharePrice) {
        this.sharePrice = sharePrice;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
    
    public void set(ResultSet res) {
        try {
            this.stockSymbol = res.getString("StockSymbol");
            this.StockName = res.getString("StockName");
            this.StockType = res.getString("StockType");
            this.NumberOfSharesAvaliable = res.getInt("NumberOfSharesAvaliable");
            this.customerSSN = res.getInt("SocialSecurityNumber");
            this.accountNumber = res.getInt("AccountNumber");
            this.numberOfShares = res.getInt("NumberOfShares");
            this.sharePrice = res.getDouble("SharePrice");
            this.orderType = res.getString("OrderType");
        } catch (SQLException ex) {
            Logger.getLogger(StockOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
