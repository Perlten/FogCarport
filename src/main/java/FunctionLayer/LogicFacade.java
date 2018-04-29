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
     * @throws DAOException
     */
    public static List<Order> getOrders() throws DAOException {
        return OrderMapper.getOrders(-1);
    }

    /**
     * Returns a specific order.
     *
     * @param orderid A valid id of an order.
     * @return if valid input, the
     * @throws DAOException
     */
    public static Order getOrder(int orderid) throws DAOException {
        if (orderid < 0) {
            throw new IllegalArgumentException("orderid can't be negative");
        }
        return OrderMapper.getOrders(orderid).get(0);
    }

    public static void makeOrder(Order order) throws DAOException {
        OrderMapper.MakeOrder(order);
    }

    /**
     * Confirms order
     *
     * @param orderId
     * @throws DAOException
     */
    public static void confirmOrder(int orderId) throws DAOException {
        OrderMapper.confirmOrder(orderId);
    }

    /**
     * Changes to order in
     *
     * @param order
     * @throws DAOException
     */
    public static void changeOrder(Order order) throws DAOException {
        OrderMapper.changeOrder(order);
    }

    /**
     * Removes shed
     *
     * @param orderId
     * @throws DAOException
     */
    public static void removeOrder(int orderId) throws DAOException {
        OrderMapper.removeOrder(orderId);
    }

    /**
     *
     * @return @throws DAOException
     */
    public static List<StyleOption> getCladdingList() throws DAOException {
        return StyleMapper.getCladding(-1);
    }

    /**
     *
     * @return @throws DAOException
     */
    public static List<StyleOption> getTileList() throws DAOException {
        return StyleMapper.getTile(-1);
    }

    /**
     *
     * @param id
     * @return
     * @throws DAOException
     */
    public static StyleOption getCladding(int id) throws DAOException {
        return StyleMapper.getCladding(id).get(0);
    }

    /**
     *
     * @param id
     * @return
     * @throws DAOException
     */
    public static StyleOption getTile(int id) throws DAOException {
        return StyleMapper.getTile(id).get(0);
    }

    public static void updateCladding(StyleOption cladding, int id) throws DAOException {
        StyleMapper.updateCladding(cladding, id);
    }

    public static void updateTile(StyleOption tile, int id) throws DAOException {
        StyleMapper.updateTile(tile, id);
    }

    public static void createCladding(StyleOption cladding) throws DAOException {
        StyleMapper.createCladding(cladding);
    }

    public static void createTile(StyleOption tile) throws DAOException {
        StyleMapper.createTile(tile);
    }

    public static void removeCladding(int id) throws DAOException {
        StyleMapper.removeCladding(id);
    }

    public static void removeTile(int id) throws DAOException {
        StyleMapper.removeTile(id);
    }
}
