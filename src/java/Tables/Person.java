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
public class Person implements Serializable{
    
    private String firstName;
    private String lastName;
    private String address;
    private String telephone;
    private String userName;
    private String password;
    private int zipCode;
    private int socialSecurityNumber;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public int getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(int socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }
    
    public void set(ResultSet res) throws SQLException{
        this.firstName = res.getString("FirstName");
        this.lastName = res.getString("LastName");
        this.address = res.getString("Address");
        this.telephone = res.getString("Telephone");
        this.userName = res.getString("Username");
        this.password = res.getString("Password");
        this.zipCode = res.getInt("ZipCode");
        this.socialSecurityNumber = res.getInt("socialSecurityNumber");
    }
}
