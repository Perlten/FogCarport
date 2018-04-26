/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import FunctionLayer.entities.Order;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author adamlass
 */
public class ShowOrder extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        try{
        int orderid = Integer.parseInt(request.getParameter("orderToShow"));
        Order order = LogicFacade.getOrder(orderid);
        request.getSession().setAttribute("selectedOrder", order);
        } catch (Exception e){
            throw new LoginSampleException("Could not show specific order!");
        }
        return "WEB-INF/orders";
    }
    
}
