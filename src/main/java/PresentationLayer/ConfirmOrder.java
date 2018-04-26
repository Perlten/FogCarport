/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Perlt
 */
public class ConfirmOrder extends Command{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        int orderId = Integer.parseInt(request.getParameter("orderToConfirm"));
        LogicFacade.confirmOrder(orderId);
        return new GetOrders().execute(request, response);
    }
    
}
