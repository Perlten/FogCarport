/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.orders;

import FunctionLayer.FOGException;
import FunctionLayer.LogicFacade;
import FunctionLayer.entities.Order;
import PresentationLayer.Command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Using the LogicFacad to show a specific order on the "orders" page.
 *
 * @author adamlass
 */
public class ShowOrder implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws FOGException {
        int orderid = Integer.parseInt(request.getParameter("orderId"));
        Order order = LogicFacade.getOrder(orderid);
        request.setAttribute("order", order);
        return new GetOrdersPage().execute(request, response);
    }
}
