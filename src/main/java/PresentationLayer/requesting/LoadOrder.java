/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.requesting;

import FunctionLayer.FOGException;
import FunctionLayer.LogicFacade;
import FunctionLayer.entities.Order;
import PresentationLayer.Command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author adamlass
 */
public class LoadOrder extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws FOGException {
        try {
            int orderid = Integer.parseInt(request.getParameter("id"));
            Order order = LogicFacade.getOrder(orderid);
            request.getSession().setAttribute("confirmedOrder", order);

        } catch (Exception e) {
            throw new FOGException("Not a valid order!");
        }
        return "WEB-INF/confirm";
    }

}
