/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONObject;

/**
 *
 * @author Jason
 */
public interface DataRepo {
    
    
    public void set(ResultSet res) throws SQLException;
    
    public JSONObject getJson();
    
}
