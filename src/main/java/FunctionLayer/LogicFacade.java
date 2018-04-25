package FunctionLayer;

import DBAccess.OrderMapper;

public class LogicFacade {

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
