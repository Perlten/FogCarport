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
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class OrderMapperTest {
    
    public OrderMapperTest() {
    }
    
    @Before
    public void setUp() {
    }

    @Test
    public void testGetOrders() throws Exception {
//        int actual;
//        int expected;
//        int inserted = 2;
//        
//        expected = OrderMapper.getOrders(-1).size() + 2;
//        
//        Order order1 = new Order(new Customer("lol", "dsa", "perlt", 123), new Customization(1, 2, 3, 4, new Shed(1, 2)));
//        Order order2 = new Order(new Customer("lol2", "dsa", "perlt", 123), new Customization(1, 2, 3, 4, new Shed(1, 2)));
//        OrderMapper.MakeOrder(order1);
//        OrderMapper.MakeOrder(order2);
//        
//        actual = OrderMapper.getOrders(-1).size();
//        
//        assertTrue(actual == expected);
//        
//        for (int i = 0; i < inserted; i++) {
//            OrderMapper.removeOrder(OrderMapper.getOrders(-1).size() - 1);
//        }
    }

    @Test
    public void testChangeOrder() {
        
    }

    @Test
    public void testConfirmOder() {
    }

    @Test
    public void testRemoveOrder() {
    }

    @Test
    public void testMakeOrder() throws ClassNotFoundException, SQLException {
//        Order orderInserted = new Order(new Customer("test", "dsa", "perlt", 123), new Customization(1, 2, 3, 4, new Shed(1, 2)));
//        OrderMapper.MakeOrder(orderInserted);
//        
//        List<Order> orderList = OrderMapper.getOrders(-1);
//        Order dbOrder = orderList.get(orderList.size() - 1);
//        
//        assertEquals(dbOrder.getCustomer(), orderInserted.getCustomer());
//        assertEquals(dbOrder.getCustomization(), orderInserted.getCustomization());
//        
//        OrderMapper.removeOrder(dbOrder.getOrderid());
    }
    
}
