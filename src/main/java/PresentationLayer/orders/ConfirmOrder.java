package PresentationLayer.orders;

import PresentationLayer.orders.GetOrders;
import FunctionLayer.LogicFacade;
import FunctionLayer.FOGException;
import PresentationLayer.Command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConfirmOrder extends Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws FOGException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        LogicFacade.confirmOrder(orderId);
        return new ShowOrder().execute(request, response);
    }
    
}
