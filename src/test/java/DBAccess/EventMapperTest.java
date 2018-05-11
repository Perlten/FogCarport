/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

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
import static junit.framework.Assert.assertEquals;
/**
 *
 * @author Perlt
 */
public class EventMapperTest {

    private final String sql;
    private final EventMapper mapper;
    private final Connection con;

    public EventMapperTest() throws IOException, ClassNotFoundException, SQLException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("sql/test_db.sql"), "UTF-8"));
        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }
        this.sql = sb.toString();
        this.con = new TestConnection().connection();
        this.mapper = new EventMapper(con);
    }

    @Before
    public void setUp() throws SQLException {
        Statement statement = con.createStatement();
        statement.executeUpdate(sql);
    }

    /**
     * Test of writeEvent method, of class EventMapper.
     */
    @Test
    public void testWriteEvent() throws Exception {
        
    }

    /**
     * Test of getOrderEvent method, of class EventMapper.
     */
    @Test
    public void testGetOrderEvent() throws Exception {
    }

    /**
     * Test of getEmployeeEvent method, of class EventMapper.
     */
    @Test
    public void testGetEmployeeEvent() throws Exception {
    }

}
