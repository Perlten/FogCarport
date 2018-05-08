/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import FunctionLayer.FOGException;
import FunctionLayer.entities.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Perlt
 */
public class EmployeeMapper {

    public static Employee verfyLogin(String username, String password) throws FOGException {
//        TODO: change roleid name;
        String sql = "SELECT idemployee, username, roleid, firstname, lastname, email, employed, date_created FROM fog.employee WHERE username = ? and password = ?";
        try {
            Connection con = Connector.connection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int employeeId = rs.getInt("idemployee");
                int authenticationLevel = rs.getInt("roleid");
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                String email = rs.getString("email");
                boolean employed = rs.getBoolean("employed");
                Calendar date = Calendar.getInstance();
                Timestamp ts = rs.getTimestamp("date_created");
                date.setTime((Date) ts);
                return new Employee(employeeId, authenticationLevel, username, firstName, lastName, email, employed, date);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new FOGException(ex.getMessage());
        }
        throw new FOGException("Could not login");
    }

    public static Employee getEmployeeByEmail(String email) throws FOGException {
        String sql = "SELECT idemployee, username, roleid, firstname, lastname, employed, date_created FROM fog.employee WHERE email = ?";
        try {
            Connection con = Connector.connection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int employeeId = rs.getInt("idemployee");
                int authenticationLevel = rs.getInt("roleid");
                String username = rs.getString("username");
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                boolean employed = rs.getBoolean("employed");
                Calendar date = Calendar.getInstance();
                Timestamp ts = rs.getTimestamp("date_created");
                date.setTime((Date) ts);
                return new Employee(employeeId, authenticationLevel, username, firstName, lastName, email, employed, date);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new FOGException("Could not find employee");
        }
        throw new FOGException("Could not find employee");
    }
    
    public static void changePasswordForEmployee(int id, String password) throws FOGException{
        String sql = "UPDATE fog.employee SET password = ? WHERE idemployee = ?";
         try {
            Connection con = Connector.connection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, password);
            ps.setInt(2, id);
            ps.execute();
         }catch(ClassNotFoundException | SQLException e){
             throw new FOGException("Could not change password");
         }
    }
   
}
