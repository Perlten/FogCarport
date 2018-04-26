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
 * @author Perlt
 */
public class EditOrderPage extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        int id = Integer.parseInt(request.getParameter("orderToEdit")); 
        try {
            Order order = LogicFacade.getOrder(id);
            request.setAttribute("order", order);
        } catch (Exception ex) {
            throw new LoginSampleException(ex.getMessage());
        }
        return "WEB-INF/editOrder";
    }
}
