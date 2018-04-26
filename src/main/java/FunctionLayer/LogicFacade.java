package FunctionLayer;

import DBAccess.OrderMapper;
import FunctionLayer.entities.Order;
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
    public static List<Order> getOrders() throws ClassNotFoundException, SQLException {
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
    public static Order getOrder(int orderid) throws ClassNotFoundException, SQLException {
        if (orderid < 0) {
            throw new IllegalArgumentException("orderid can't be negative");
        }
        return OrderMapper.getOrders(orderid).get(0);
    }

    /**
     * Confirms order
     *
     * @param orderId
     */
    public static void confirmOrder(int orderId) {
        OrderMapper.confirmOder(orderId);
    }

    /**
     * Changes to order in
     *
     * @param order
     */
    public static void changeOrder(Order order) {
        OrderMapper.changeOrder(order);
    }

    /**
     * Removes shed
     *
     * @param orderId
     */
    public static void removeOrder(int orderId) {
        OrderMapper.removeOrder(orderId);
    }
}
