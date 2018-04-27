/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.orders;

import FunctionLayer.LoginSampleException;
import FunctionLayer.entities.Order;
import PresentationLayer.Command;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Using the LogicFacade to load all the orders from the database.
 * @author adamlass
 */
public class GetOrders extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        try {
            List<Order> orders = FunctionLayer.LogicFacade.getOrders();
            request.setAttribute("orders", orders);
        } catch (Exception e) {
            throw new LoginSampleException("Could not show all orders!");
        }
        return "WEB-INF/orders";
    }

}
