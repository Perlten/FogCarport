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
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Perlt
 */
public class EmployeeMapper {

    private Connection con;

    public EmployeeMapper() throws FOGException {
        try {
            con = new LiveConnection().connection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new FOGException("Could not find connection");
        }
    }

    public EmployeeMapper(Connection con) {
        this.con = con;
    }

    /**
     * 
     * @param username
     * @param password Password is encryptet
     * @return
     * @throws FOGException 
     */
    public Employee verfyLogin(String username, String password) throws FOGException {
        String sql = "SELECT idemployee, username, roleid, firstname, lastname, email, employed, date_created, reset_password FROM employee WHERE BINARY username = ? and BINARY password = ? AND employed = true";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            List<Employee> list = convert(rs);
            return list.get(0);
        } catch (SQLException ex) {
            throw new FOGException("could not verify login");
        }
    }
    //TODO: make use of dummy employee object
    public void createEmployee(String firstname, String lastname, String username, String email, int accessLevel, String password, String salt) throws FOGException {
        String sql = "INSERT INTO employee(username, roleid, firstname, lastname, password, email, salt)"
                + " VALUES(?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setInt(2, accessLevel);
            ps.setString(3, firstname);
            ps.setString(4, lastname);
            ps.setString(5, password);
            ps.setString(6, email);
            ps.setString(7, salt);
            ps.execute();
        } catch (SQLException ex) {
            throw new FOGException(ex.getMessage());
        }
    }

    public Employee getEmployeeByEmail(String email) throws FOGException {
        String sql = "SELECT idemployee, username, roleid, firstname, lastname, email, employed, date_created, reset_password FROM employee WHERE email = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            List<Employee> list = convert(rs);
            return list.get(0);
        } catch (SQLException e) {
            throw new FOGException("Could not find employee");
        }
    }

    public void changePasswordAndResetPassword(int id, String password, String salt) throws FOGException {
        String sql = "UPDATE employee SET reset_password = true, password = ?, salt = ?  WHERE idemployee = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, password);
            ps.setString(2, salt);
            ps.setInt(3, id);
            ps.execute();
        } catch (SQLException e) {
            throw new FOGException("Could not change password");
        }
    }

    public List<Employee> getAllEmployees(boolean noFired) throws FOGException {
        String sql = "SELECT * FROM employee";
        if(noFired){
            sql += " where employed = true";
        }
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            return convert(rs);
        } catch (SQLException e) {
            throw new FOGException("Could not find employees");
        }
    }

    public Employee getEmployeeById(int id) throws FOGException {
        String sql = "SELECT * FROM employee WHERE idemployee = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            List<Employee> list = convert(rs);
            return list.get(0);
        } catch (SQLException e) {
            throw new FOGException("Could not find employee");
        }
    }

    private List<Employee> convert(ResultSet rs) throws SQLException, FOGException {
        List<Employee> list = new ArrayList<>();
        while (rs.next()) {
            int employeeId = rs.getInt("idemployee");
            int authenticationLevel = rs.getInt("roleid");
            String username = rs.getString("username");
            String firstName = rs.getString("firstname");
            String email = rs.getString("email");
            String lastName = rs.getString("lastname");
            boolean employed = rs.getBoolean("employed");
            Calendar date = Calendar.getInstance();
            Timestamp ts = rs.getTimestamp("date_created");
            date.setTime((Date) ts);
            boolean resetPassword = rs.getBoolean("reset_password");
            list.add(new Employee(employeeId, authenticationLevel, username, firstName, lastName, email, employed, date, resetPassword));
        }
        if (list.isEmpty()) {
            throw new FOGException("Could not find employee(s)");
        }
        return list;
    }

    public void updateEmployee(Employee employee) throws FOGException {
        String sql = "UPDATE employee SET username = ?, roleid = ?, firstname = ?, lastname = ?, email = ? where idemployee = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, employee.getUsername());
            ps.setInt(2, employee.getAuthenticationLevel());
            ps.setString(3, employee.getFirstname());
            ps.setString(4, employee.getLastname());
            ps.setString(5, employee.getEmail());
            ps.setInt(6, employee.getEmployeeId());
            ps.execute();
        } catch (SQLException e) {
            throw new FOGException("Could not update employee");
        }
    }

    public void fireEmployee(int employeeId) throws FOGException {
        String sql = "UPDATE employee SET employed = false WHERE idemployee = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, employeeId);
            ps.execute();
        } catch (SQLException e) {
            throw new FOGException("Could not fire Employee");
        }
    }

    public void resetPassword(int employeeId) throws FOGException {
        String sql = "UPDATE employee SET reset_password = true WHERE idemployee = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, employeeId);
            ps.execute();
        } catch (SQLException e) {
            throw new FOGException("Could not reset password");
        }
    }
    //TODO: merge with similar method
    public void changePasswordAndRemoveResetPassword(int employeeId, String newPassword, String salt) throws FOGException {
        String sql = "UPDATE employee SET reset_password = false, password = ?, salt = ? WHERE idemployee = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, newPassword);
            ps.setString(2, salt);
            ps.setInt(3, employeeId);
            ps.execute();
        } catch (SQLException e) {
            throw new FOGException("Could not change password");
        }
    }

    public String getSalt(String username) throws FOGException {
        String sql = "SELECT salt FROM employee where username = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getString(1);
        } catch (SQLException e) {
            throw new FOGException(e.getMessage());
        }
    }
}
