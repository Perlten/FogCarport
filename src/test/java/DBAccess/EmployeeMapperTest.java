/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import FunctionLayer.FOGException;
import FunctionLayer.entities.Employee;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static junit.framework.Assert.*;

/**
 *
 * @author Perlt
 */
public class EmployeeMapperTest {

    private final String sql;
    private final EmployeeMapper mapper;
    private final Connection con;

    public EmployeeMapperTest() throws IOException, ClassNotFoundException, SQLException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("sql/test_db.sql"), "UTF-8"));
        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }
        this.sql = sb.toString();
        this.con = new TestConnection().connection();
        this.mapper = new EmployeeMapper(con);
    }

    @Before
    public void setUp() throws SQLException {
        Statement statement = con.createStatement();
        statement.executeUpdate(sql);
    }

    /**
     * Test of verfyLogin method, of class EmployeeMapper.
     */
    @Test
    public void testSuccessfulVerfyLogin() throws Exception {
        Employee emp = mapper.verfyLogin("Larsen", "LassLass");
        assertNotNull(emp);
    }

    /**
     * Test of verfyLogin method, of class EmployeeMapper.
     */
    @Test(expected = FOGException.class)
    public void testUnsuccessfulVerfyLogin() throws Exception {
        Employee emp = mapper.verfyLogin("larsen", "lasslass");
        assert false;
    }

    /**
     * Test of createEmployee method, of class EmployeeMapper.
     */
    @Test
    public void testCreateEmployee() throws Exception {
        Employee emp = new Employee("Pernille", "Jensen", "Persille", "Persille@gmail.com", 1);
        mapper.createEmployee(emp, "1234", "salt");

        int sizeExpected = 4;
        int sizeActual = mapper.getAllEmployees(false).size();
        assertEquals(sizeExpected, sizeActual);

        String nameExpected = "Pernille";
        String nameActual = mapper.getEmployeeByEmail("Persille@gmail.com").getFirstname();

        assertEquals(nameExpected, nameActual);

    }

    /**
     * Test of getEmployeeByEmail method, of class EmployeeMapper.
     */
    @Test
    public void testGetEmployeeByEmail() throws Exception {
        String firstNameExpected = "Nikolai";
        String firstNameActual = mapper.getEmployeeByEmail("Nikolai@gmail.com").getFirstname();

        assertEquals(firstNameExpected, firstNameActual);
    }

    /**
     * Test of getAllEmployees method, of class EmployeeMapper.
     */
    @Test
    public void testGetAllEmployees() throws Exception {
        int expected = 3;
        int actual = mapper.getAllEmployees(false).size();

        assertEquals(expected, actual);
    }

    /**
     * Test of getEmployeeById method, of class EmployeeMapper.
     */
    @Test
    public void testGetEmployeeById() throws Exception {
        String nameExpected = "Adam";
        String nameActual = mapper.getEmployeeById(2).getFirstname();

        assertEquals(nameExpected, nameActual);
    }

    /**
     * Test of updateEmployee method, of class EmployeeMapper.
     */
    @Test
    public void testUpdateEmployee() throws Exception {
        Employee emp = mapper.getEmployeeById(2);
        String expected = "LassLass@gmail.com";
        emp.setEmail(expected);
        mapper.updateEmployee(emp);
        emp = mapper.getEmployeeById(2);
        String actual = emp.getEmail();
        assertEquals(expected, actual);
    }

    /**
     * Test of fireEmployee method, of class EmployeeMapper.
     */
    @Test
    public void testFireEmployee() throws Exception {
        assertTrue(mapper.getEmployeeById(1).isEmployed());
        mapper.fireEmployee(1);
        assertFalse(mapper.getEmployeeById(1).isEmployed());
    }

    /**
     * Test of resetPassword method, of class EmployeeMapper.
     */
    @Test
    public void testResetPassword() throws Exception {
        assertFalse(mapper.getEmployeeById(3).isResetPassword());
        mapper.resetPassword(3);
        assertTrue(mapper.getEmployeeById(3).isResetPassword());
    }

    /**
     * Test of changePassword method, of class EmployeeMapper.
     */
    @Test
    public void testChangePasswordAndRemoveResetPassword() throws Exception {
        mapper.resetPassword(1);
        mapper.changePassword(1, "test", "salt", false);
        Employee emp = mapper.verfyLogin("Nikolai123", "test");
        assertNotNull(emp);
        assertFalse(emp.isResetPassword());
    }
    
    @Test
    public void testGetSalt() throws Exception{
        String expected = "mille";
        String actual = mapper.getSalt("Larsen");
        assertEquals(expected, actual);
    }
}
