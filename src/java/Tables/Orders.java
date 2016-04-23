/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tables;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jason
 */
public class Orders implements Serializable{
    
    private int customerSSN;
    private int numberOfShares;
    private String orderTime;
    private int orderID;
    private int employeeSSN;
    private int accountNumber;
    private String stockSymbol;
    private String orderDate;
    private double sharePrice;
    private String orderType;

    public int getCustomerSSN() {
        return customerSSN;
    }

    public void setCustomerSSN(int customerSSN) {
        this.customerSSN = customerSSN;
    }

    public int getNumberOfShares() {
        return numberOfShares;
    }

    public void setNumberOfShares(int numberOfShares) {
        this.numberOfShares = numberOfShares;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getEmployeeSSN() {
        return employeeSSN;
    }

    public void setEmployeeSSN(int employeeSSN) {
        this.employeeSSN = employeeSSN;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
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
            this.customerSSN = res.getInt("SocialSecurityNumber");
            this.numberOfShares = res.getInt("NumberOfShares");
            this.orderTime = res.getString("OrderTime");
            this.orderID = res.getInt("OrderID");
            this.employeeSSN = res.getInt("EmployeeSSN");
            this.accountNumber = res.getInt("AccountNumber");
            this.stockSymbol = res.getString("StockSymbol");
            this.orderDate = res.getString("OrderDate");
            this.sharePrice = res.getDouble("SharePrice");
            this.orderType = res.getString("OrderType");
        } catch (SQLException ex) {
            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    
    
    
}
