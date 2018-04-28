/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.orders;

import PresentationLayer.orders.GetOrders;
import FunctionLayer.LogicFacade;
import FunctionLayer.DAOException;
import FunctionLayer.entities.Order;
import PresentationLayer.Command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Using the LogicFacad to show a specific order on the "orders" page.
 *
 * @author adamlass
 */
public class ShowOrder extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DAOException {
        int orderid = Integer.parseInt(request.getParameter("orderId"));
        Order order = LogicFacade.getOrder(orderid);
        request.setAttribute("selectedOrder", order);
        return new GetOrders().execute(request, response);
    }
}
