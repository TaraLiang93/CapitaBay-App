/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class Transaction {
    private int transId;
    private long employeeSSN;
    private long ssn;
    private int accountNum;
    private String stockSymbol;
    private double fee;
    private String dat;
    private double price;
    private int nos;

    public int getNos() {
        return nos;
    }

    public void setNos(int nos) {
        this.nos = nos;
    }
           

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    public long getEmployeeSSN() {
        return employeeSSN;
    }

    public void setEmployeeSSN(long employeeSSN) {
        this.employeeSSN = employeeSSN;
    }

    public long getSsn() {
        return ssn;
    }

    public void setSsn(long ssn) {
        this.ssn = ssn;
    }

    public int getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(int accountNum) {
        this.accountNum = accountNum;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public String getDat() {
        return dat;
    }

    public void setDat(String dat) {
        this.dat = dat;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public void set(ResultSet res){
        try{
            this.stockSymbol = res.getString("StockSymbol");
            this.employeeSSN = res.getInt("EmployeeSSN");
            this.ssn = res.getInt("SocialSecurityNumber");
            this.accountNum = res.getInt("AccountNumber");
            this.transId = res.getInt("TransID") ;
            this.fee = res.getDouble("Fee");
            this.dat = res.getString("DateProcessed");
            this.price = res.getDouble("PricePerShare");
        } catch (SQLException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
