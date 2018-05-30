package PresentationLayer.orders;

import FunctionLayer.FOGException;
import FunctionLayer.LogicFacade;
import FunctionLayer.entities.Employee;
import FunctionLayer.entities.Event;
import PresentationLayer.Command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConfirmOrder implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws FOGException {

        int orderId = Integer.parseInt(request.getParameter("orderId"));
        LogicFacade.confirmOrder(orderId);

        //event
        Employee emp = (Employee) request.getSession().getAttribute("employee");
        LogicFacade.writeOrderEmployeeEvent(new Event(emp, 2, orderId));

        return new ShowOrder().execute(request, response);
    }

}
