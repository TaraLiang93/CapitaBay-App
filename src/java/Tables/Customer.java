/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tables;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Jason
 */
public class Customer extends Person implements Serializable{
    
    private double rating;
    private String email;
    private String creditCardNumber;

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }
    
    
    @Override
    public void set(ResultSet res) throws SQLException{
        super.set(res);
        this.rating = res.getDouble("Rating");
        this.email = res.getString("Email");
        this.creditCardNumber = res.getString("CreditCardNumber");
    }
    
}
