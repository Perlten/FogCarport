/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import FunctionLayer.entities.Employee;
import FunctionLayer.entities.Event;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.Assert.assertEquals;

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
            sb.append(System.lineSeparator());
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
     * Test of getOrderEvent method, of class EventMapper.
     */
    @Test
    public void testGetOrderEvent() throws Exception {
        List<Event> events = mapper.getOrderEvent(2);
        Event event1 = events.get(0);
        Event event2 = events.get(1);

        assertEquals(4, event1.getEventId());
        assertEquals(3, event2.getEventId());

        assertEquals(0, event1.getEmployee());

        assertEquals(4, event1.getEventType());
        assertEquals(3, event2.getEventType());
    }

    /**
     * Test of getEmployeeEvent method, of class EventMapper.
     */
    @Test
    public void testGetEmployeeEvent() throws Exception {
        List<Event> events = mapper.getEmployeeEvent(1, 99);
        Event event1 = events.get(1);
        Event event2 = events.get(0);

        assertEquals(1, event1.getEventId());
        assertEquals(2, event2.getEventId());

    }

    @Test
    public void testWriteOrderEvent() throws Exception {
        Event event = new Event(6, 3);
        mapper.writeOrderEvent(event);

        Event actual = mapper.getOrderEvent(3).get(0);
        assertEquals(6, actual.getEventType());
    }

    @Test
    public void testWriteOrderEmployeeEvent() throws Exception {
        Employee emp = new Employee(3, 0, null, null, null, null, false, null, false);
        Event event = new Event(emp, 1, 2);
        mapper.writeOrderEmployeeEvent(event);

        Event actual = mapper.getOrderEvent(2).get(0);
        assertEquals(3, actual.getEmployee());
    }

    @Test
    public void testWriteEmployeeEvent() throws Exception {
         Employee emp = new Employee(3, 0, null, null, null, null, false, null, false);
        Event event = new Event(emp, 2);
        mapper.writeEmployeeEvent(event);

        Event actual = mapper.getEmployeeEvent(3, 99).get(0);
        assertEquals(2, actual.getEventType());
    }
}
