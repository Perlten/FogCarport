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
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author adamlass
 */
public class LoadOrderPage extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws FOGException {
            HttpSession session = request.getSession();

            Order order = null;

            int orderid = 0;

            if (request.getAttribute("confirmedId") != null) {
                orderid = (int) request.getAttribute("confirmedId");
            } else if (request.getParameter("id") != null) {
                orderid = Integer.parseInt(request.getParameter("id"));
            } else {
                return "WEB-INF/requesting/confirm";
            }

            order = LogicFacade.getOrder(orderid);
            session.setAttribute("confirmedOrder", order);
            
            //setting events
            List<Event> events = LogicFacade.getOrderEvent(orderid);
            request.getSession().setAttribute("orderEvents", events);
            
            //setting allowed
            HashMap<String, Boolean> allowed = (HashMap<String, Boolean>) request.getSession().getAttribute("allowed");
            allowed.put("Confirm", true);
            allowed.put("Dimentions", false);

        return "WEB-INF/requesting/confirm";
    }

}
