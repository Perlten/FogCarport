/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import FunctionLayer.FOGException;
import FunctionLayer.entities.Customer;
import FunctionLayer.entities.Customization;
import FunctionLayer.entities.Order;
import FunctionLayer.entities.Shed;
import FunctionLayer.entities.StyleOption;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import static junit.framework.Assert.assertEquals;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author Perlt
 */
public class OrderMapperTest {

    private final String sql;
    private final OrderMapper mapper;
    private final Connection con;
    
    public OrderMapperTest() throws IOException, ClassNotFoundException, SQLException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("sql/test_db.sql"), "UTF-8"));
        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }
        this.sql = sb.toString();
        this.con = new TestConnection().connection();
        this.mapper = new OrderMapper(con);
    }
    
    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {
        Statement statement = con.createStatement();
        statement.executeUpdate(sql);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetAllOrders() throws FOGException {
        int expected = 4;
        int actual = mapper.getOrders(-1).size();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetSpecificOrder() throws FOGException {
        Order order = mapper.getOrders(3).get(0);

        String expected = "Per";
        String actual = order.getCustomer().getFirstname();

        assertEquals(expected, actual);
    }

    @Test
    public void getCustomerListLimit2() throws FOGException {
        int expected = 2;
        int actual = mapper.getCustomerList(2).size();

        assertEquals(expected, actual);
    }

    @Test
    public void testChangeOrder() throws FOGException {
        Order order = mapper.getOrders(4).get(0);
        order.getCustomer().setFirstname("Hello");
        order.getCustomer().setLastname("World");
        StyleMapper styleMapper = new StyleMapper(con);
        //TODO: Make Better
        StyleOption cladding = styleMapper.getCladding(1).get(0);
        StyleOption tile = styleMapper.getCladding(1).get(0);

        order.getCustomization().setCladding(cladding);
        order.getCustomization().setTile(tile);
        mapper.changeOrder(order);

        Order newOrder = mapper.getOrders(4).get(0);
        String expectdFirstName = "Hello";
        String actualFirstName = newOrder.getCustomer().getFirstname();

        String expectdLastName = "World";
        String actualLastName = newOrder.getCustomer().getLastname();

        assertEquals(expectdFirstName, actualFirstName);
        assertEquals(expectdLastName, actualLastName);
    }

    @Test
    public void testConfirmOrder() throws FOGException {
        assertFalse(mapper.getOrders(2).get(0).isConfirmed());
        mapper.confirmOrder(2);
        Order order = mapper.getOrders(2).get(0);
        assertTrue(order.isConfirmed());
    }

    @Test
    public void testUnconfirmOrder() throws FOGException {
        mapper.confirmOrder(2);
        Order order = mapper.getOrders(2).get(0);
        assertTrue(order.isConfirmed());
        mapper.unconfirmOrder(2);
        order = mapper.getOrders(2).get(0);
        assertFalse(order.isConfirmed());
    }

    @Test(expected = FOGException.class)
    public void testRemoveOrder() throws FOGException {
        mapper.removeOrder(2);
        mapper.getOrders(2).get(0);

        assert false;
    }

    @Test
    public void testMakeOrder() throws FOGException {
        Customer customer = new Customer("Lars", "Larsen", "Larsen@gmail.com", 2334);
        Customization customization = new Customization(900, 900, 250, 0, new Shed(200, 200), new StyleOption("test", "test", 22, 1), new StyleOption("test", "test", 22, 1));
        Order order = new Order(customer, customization);
        mapper.makeOrder(order);

        int expected = 5;
        int actual = mapper.getOrders(-1).size();

        assertEquals(expected, actual);
    }

    @Test
    public void testNumberOfUnconfirmedOrders() throws FOGException {
        mapper.confirmOrder(2);
        int expected = 3;
        int actual = mapper.numberOfUnconfirmedOrders();

        assertEquals(expected, actual);
    }

    @Test
    public void testGetUnconfirmedOrders() throws FOGException {
        mapper.confirmOrder(1);

        int expected = 3;
        int actual = mapper.getUnconfirmedOrders(0).size();

        assertEquals(expected, actual);
    }
}
