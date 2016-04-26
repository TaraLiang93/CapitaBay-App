/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tables;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.joda.time.DateTime;

/**
 *
 * @author Jason
 */
public class Employee extends Person implements Serializable{
    
    private String position;
    private String startDate;
    private double hourlyRate;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
    
    @Override
    public void set(ResultSet res) throws SQLException{
        super.set(res);
        this.position = res.getString("Position");
        this.startDate = res.getString("StartDate");
        this.hourlyRate = res.getDouble("HourlyRate");
    }
    
}
