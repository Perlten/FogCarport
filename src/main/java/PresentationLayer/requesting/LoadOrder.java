/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.requesting;

import FunctionLayer.FOGException;
import FunctionLayer.LogicFacade;
import FunctionLayer.entities.Event;
import FunctionLayer.entities.Order;
import PresentationLayer.Command;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author adamlass
 */
public class LoadOrder extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws FOGException {
        try {
            HttpSession session = request.getSession();

            Order order = (Order) session.getAttribute("confirmedOrder");
            if (order == null) {
                int orderid = Integer.parseInt(request.getParameter("id"));
                order = LogicFacade.getOrder(orderid);
                session.setAttribute("confirmedOrder", order);
            }
            List<Event> events = LogicFacade.getEvents(order.getOrderid());
            request.setAttribute("orderEvents", events);

        } catch (Exception e) {
            throw new FOGException("Not a valid order!");
        }
        return "WEB-INF/confirm";
    }

}
