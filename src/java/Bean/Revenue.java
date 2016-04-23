/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class Revenue implements Serializable {
    private String revenueType;
    private double revenue;

    public String getRevenueType() {
        return revenueType;
    }

    public void setRevenueType(String revenueType) {
        this.revenueType = revenueType;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }
    
    public void set(ResultSet res, String typeRevenue) throws SQLException {
        try {
            this.revenueType = res.getString(typeRevenue);
            this.revenue = res.getDouble("Revenue");

        }catch (SQLException ex) {
            Logger.getLogger(Revenue.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    
}
//
//CREATE PROCEDURE listRevenueByStockType(IN e_ssn INTEGER,IN stockty VARCHAR(32))
//BEGIN
//	-- IF(SELECT E.SocialSecurityNumber FROM Employee E WHERE ) 
//	DECLARE currentEmployeePosition VARCHAR(12);
//
//	SELECT E.Position INTO currentEmployeePosition
//	FROM Employee E
//	WHERE E.SocialSecurityNumber = e_ssn;
//
//	IF currentEmployeePosition = 'Manager' THEN
//		SELECT DISTINCT S.StockType,SUM(O.NumberOfShares*O.SharePrice) AS Revenue
//		FROM Orders O
//		INNER JOIN StockTable S 
//		ON O.StockSymbol = S.StockSymbol 
//		WHERE S.StockType = stockty AND O.OrderType = 'buy';
//	END IF;
//
//END $$
