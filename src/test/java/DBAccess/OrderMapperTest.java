/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import FunctionLayer.LogicFacade;
import FunctionLayer.entities.Customer;
import FunctionLayer.entities.Customization;
import FunctionLayer.entities.Order;
import FunctionLayer.entities.Shed;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class OrderMapperTest {

    private static Order order1 = new Order(new Customer("Sup", "dsa", "perlt", 123), new Customization(1, 2, 3, 4, new Shed(1, 2)));
    private static Order order2 = new Order(new Customer("Sup", "dsa", "perlt", 123), new Customization(1, 2, 3, 4, new Shed(1, 2)));
    private static int size;

    public OrderMapperTest() {

    }

    @BeforeClass
    public static void setUp() throws ClassNotFoundException, SQLException {
        size = OrderMapper.getOrders(-1).size();
        OrderMapper.MakeOrder(order1);
        OrderMapper.MakeOrder(order2);
    }

    @AfterClass
    public static void tearDown() throws ClassNotFoundException, SQLException {
        for (int i = 0; i < 2; i++) {
            OrderMapper.removeLast();
        }
    }

    @Test
    public void testGetOrders() throws Exception {
        int actual;
        int expected = size + 2;

        actual = OrderMapper.getOrders(-1).size();

        assertEquals(expected, actual);

    }

    @Test
    public void testChangeOrder() throws ClassNotFoundException, SQLException {
        String newName = "i laver test";
        Order order = LogicFacade.getOrder(LogicFacade.getOrders().size());
        order.getCustomer().setFirstname(newName);
        try {
            OrderMapper.changeOrder(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Order newOrder = LogicFacade.getOrder(LogicFacade.getOrders().size());
        assertEquals(order.getCustomer().getFirstname(), newOrder.getCustomer().getFirstname());
    }

    @Test
    public void testConfirmOder() {
    }

    @Test
    public void testRemoveOrder() {

    }

    @Test
    public void testMakeOrder() throws ClassNotFoundException, SQLException {
        List<Order> orderList = OrderMapper.getOrders(-1);
        Order dbOrder = orderList.get(orderList.size() - 1);

        assertEquals(dbOrder.getCustomer(), order2.getCustomer());
        assertEquals(dbOrder.getCustomization(), order2.getCustomization());

    }

}
