package PresentationLayer.orders;

import PresentationLayer.orders.GetOrders;
import FunctionLayer.LogicFacade;
import FunctionLayer.FOGException;
import FunctionLayer.entities.Event;
import PresentationLayer.Command;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConfirmOrder extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws FOGException {

        try {
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            LogicFacade.confirmOrder(orderId);
            
            
            Event event = new Event(-1, orderId, -1, -1, 2, null, null, null, null, null);
            LogicFacade.writeEvent(event);
        } catch (Exception ex) {
            throw new FOGException("Could not confirm order!");
        }

        return new ShowOrder().execute(request, response);
    }

}
