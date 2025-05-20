/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.sql.*;

public class dbConnector {
       private Connection connect;
       
       public dbConnector() {
         try{
             connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/obaob_db", "root","" );
         }catch(SQLException ex){
           System.out.println("Can't connect to database"+ex.getMessage());
       }
           
     }
       
       public ResultSet getData(String sql) throws SQLException{
             Statement stmt = connect.createStatement();
              ResultSet rst = stmt.executeQuery(sql);
              return rst;
          
       }
       public Connection getConnection(){
           return connect;
       }
       
        public void insertLog(int userId, String action) {
        try {
            String query = "INSERT INTO system_logs (user_id, logs_action, logs_date) VALUES (?, ?, NOW())";
            PreparedStatement pst = connect.prepareStatement(query);
            pst.setInt(1, userId);
            pst.setString(2, action);
            pst.executeUpdate();
            pst.close();
        } catch (SQLException ex) {
            System.out.println("Error inserting log: " + ex.getMessage());
        }
    }
}
       
    
