package FunctionLayer;

import DBAccess.OrderMapper;
import FunctionLayer.entities.Order;
import java.sql.SQLException;
import java.util.List;


public class LogicFacade {
    /**
     * returns all of the orders by inputing -1 in getOrders 
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public static List<Order> getOrders() throws ClassNotFoundException, SQLException{
        return OrderMapper.getOrders(-1);
    }
    
    /**
     * Returns a specific order.
     * @param orderid A valid id of an order.
     * @return if valid input, the
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public static Order getOrder(int orderid) throws ClassNotFoundException, SQLException{
        if(orderid < 0){
            throw new IllegalArgumentException("orderid can't be negative");
        }
        return OrderMapper.getOrders(orderid).get(0);
    }

    public static void confirmOrder(int orderId) {
        OrderMapper.confirmOder(orderId);
    }

    /**
     * Change the dimension of a carport
     *
     * @param length
     * @param width
     * @param height
     * @param roofangle Angle of the roof
     * @param orderId
     */
    public static void changeCarport(int length, int width, int height, int roofangle, int orderId) {
        OrderMapper.changeCarportDimensions(length, width, height, roofangle, orderId);
    }

    /**
     * Changes the cladding type in a order
     *
     * @param orderId
     * @param claddingId
     */
    public static void changeCladding(int orderId, int claddingId) {
        OrderMapper.changeCladding(orderId, claddingId);
    }

    /**
     * Changes the tile type in a order 
     *
     * @param orderId
     * @param tileId
     */
    public static void changeTile(int orderId, int tileId) {
        OrderMapper.changeTile(orderId, tileId);
    }

    /**
     * Change the dimension of a shed
     *
     * @param length
     * @param width
     * @param orderId
     */
    public static void changeShed(int length, int width, int orderId) {
        OrderMapper.changeShed(length, width, orderId);
    }

    /**
     * Removes shed in database and sets its length and width to 0
     *
     * @param orderId
     */
    public static void removeShed(int orderId) {
        OrderMapper.removeShed(orderId);
    }
    /**
     * Removes order from database
     * @param orderId 
     */
    public static void removeOrder(int orderId) {
        OrderMapper.removeOrder(orderId);
    }
}
