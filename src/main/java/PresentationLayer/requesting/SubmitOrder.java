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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author adamlass
 */
public class SubmitOrder extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws FOGException {
            HttpSession session = request.getSession();
            Order order = (Order) session.getAttribute("order");
            LogicFacade.makeOrder(order);
            order.setOrdered(true);
            session.setAttribute("order", null);
            session.setAttribute("confirmedOrder", order);
            LogicFacade.sendEmailToCustomer(order);
            
            //adding event on eventlist
            LogicFacade.writeEvent(new Event(order.getOrderid(), 1));

        return new LoadOrder().execute(request, response);
    }

}
