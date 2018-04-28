/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.orders;

import PresentationLayer.orders.GetOrders;
import FunctionLayer.LogicFacade;
import FunctionLayer.DAOException;
import PresentationLayer.Command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Perlt
 */
public class DeleteOrder extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DAOException {
        int orderId = Integer.parseInt(request.getParameter("orderToDelete"));
        LogicFacade.removeOrder(orderId);
        return new GetOrders().execute(request, response);
    }
    
}
