/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.LoginSampleException;
import FunctionLayer.entities.Order;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author adamlass
 */
public class GetOrders extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        try {
            List<Order> orders = FunctionLayer.LogicFacade.getOrders();
            request.getSession().setAttribute("orders", orders);

        } catch (Exception e) {
            throw new LoginSampleException("Could not show all orders!");
        }
        return "WEB-INF/orders";
    }

}