package FunctionLayer;

import DBAccess.OrderMapper;
import DBAccess.StyleMapper;
import FunctionLayer.entities.Order;
import FunctionLayer.entities.StyleOption;
import java.sql.SQLException;
import java.util.List;

public class LogicFacade {

    /**
     * returns all of the orders by inputing -1 in getOrders
     *
     * @return
     * @throws FOGException
     */
    public static List<Order> getOrders() throws FOGException {
        return OrderMapper.getOrders(-1);
    }

    /**
     * Returns a specific order.
     *
     * @param orderid A valid id of an order.
     * @return if valid input, the
     * @throws FOGException
     */
    public static Order getOrder(int orderid) throws FOGException {
        if (orderid < 0) {
            throw new IllegalArgumentException("orderid can't be negative");
        }
        return OrderMapper.getOrders(orderid).get(0);
    }

    public static void makeOrder(Order order) throws FOGException {
        OrderMapper.MakeOrder(order);
    }

    /**
     * Confirms order
     *
     * @param orderId
     * @throws FOGException
     */
    public static void confirmOrder(int orderId) throws FOGException {
        OrderMapper.confirmOrder(orderId);
    }

    /**
     * Changes to order in
     *
     * @param order
     * @throws FOGException
     */
    public static void changeOrder(Order order) throws FOGException {
        OrderMapper.changeOrder(order);
    }

    /**
     * Removes shed
     *
     * @param orderId
     * @throws FOGException
     */
    public static void removeOrder(int orderId) throws FOGException {
        OrderMapper.removeOrder(orderId);
    }

    /**
     *
     * @return @throws FOGException
     */
    public static List<StyleOption> getCladdingList() throws FOGException {
        return StyleMapper.getCladding(-1);
    }

    /**
     *
     * @return @throws FOGException
     */
    public static List<StyleOption> getTileList() throws FOGException {
        return StyleMapper.getTile(-1);
    }

    /**
     *
     * @param id
     * @return
     * @throws FOGException
     */
    public static StyleOption getCladding(int id) throws FOGException {
        if (!StyleMapper.getCladding(id).isEmpty()) {
            return StyleMapper.getCladding(id).get(0);
        }
        return null;
    }
    
    public static void unconfirmOrder(int id) throws FOGException{
        OrderMapper.unconfirmOrder(id);
    } 

    /**
     *
     * @param id
     * @return
     * @throws FOGException
     */
    public static StyleOption getTile(int id) throws FOGException {
        if (!StyleMapper.getTile(id).isEmpty()) {
            return StyleMapper.getTile(id).get(0);
        }
        return null;
    }

    public static void updateCladding(StyleOption cladding, int id) throws FOGException {
        StyleMapper.updateCladding(cladding, id);
    }

    public static void updateTile(StyleOption tile, int id) throws FOGException {
        StyleMapper.updateTile(tile, id);
    }

    public static void createCladding(StyleOption cladding) throws FOGException {
        StyleMapper.createCladding(cladding);
    }

    public static void createTile(StyleOption tile) throws FOGException {
        StyleMapper.createTile(tile);
    }

    public static void removeCladding(int id) throws FOGException {
        StyleMapper.removeCladding(id);
    }

    public static void removeTile(int id) throws FOGException {
        StyleMapper.removeTile(id);
    }
}
