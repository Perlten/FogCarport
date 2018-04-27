package FunctionLayer;

import DBAccess.OrderMapper;
import FunctionLayer.entities.Customer;
import FunctionLayer.entities.Customization;
import FunctionLayer.entities.Order;
import FunctionLayer.entities.Shed;
import java.sql.SQLException;
import java.util.List;

public class LogicFacade {

    /**
     * returns all of the orders by inputing -1 in getOrders
     *
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static List<Order> getOrders() throws LoginSampleException  {
        return OrderMapper.getOrders(-1);
    }

    /**
     * Returns a specific order.
     *
     * @param orderid A valid id of an order.
     * @return if valid input, the
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Order getOrder(int orderid) throws LoginSampleException {
        if (orderid < 0) {
            throw new IllegalArgumentException("orderid can't be negative");
        }
        return OrderMapper.getOrders(orderid).get(0);
    }
    
    public static void makeOrder(Order order) throws LoginSampleException{
        OrderMapper.MakeOrder(order);
    }

    /**
     * Confirms order
     *
     * @param orderId
     */
    public static void confirmOrder(int orderId) throws LoginSampleException {
        OrderMapper.confirmOrder(orderId);
    }

    /**
     * Changes to order in
     *
     * @param order
     */
    public static void changeOrder(Order order) throws LoginSampleException  {
        OrderMapper.changeOrder(order);
    }

    /**
     * Removes shed
     *
     * @param orderId
     */
    public static void removeOrder(int orderId) throws LoginSampleException  {
        OrderMapper.removeOrder(orderId);
    }
}
