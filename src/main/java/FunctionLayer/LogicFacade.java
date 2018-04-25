package FunctionLayer;

import DBAccess.OrderMapper;
import FunctionLayer.entities.Order;
import java.sql.SQLException;
import java.util.List;


/**
 * The purpose of LogicFacade is to...
 * @author kasper
 */
public class LogicFacade {
    
    public static List<Order> getOrders() throws ClassNotFoundException, SQLException{
        return OrderMapper.getOrders();
    }


}
