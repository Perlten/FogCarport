/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.orders;

import FunctionLayer.FOGException;
import FunctionLayer.LogicFacade;
import FunctionLayer.entities.Event;
import PresentationLayer.Command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Perlt
 */
public class Unconfirm extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws FOGException {
        try {
            int orderid = Integer.parseInt(request.getParameter("orderId"));

            LogicFacade.unconfirmOrder(orderid);

            //Event
            LogicFacade.writeEvent(new Event(orderid, 5));
        } catch (Exception e) {
            throw new FOGException("Could not unconfirm the order!");
        }

        return new ShowOrder().execute(request, response);
    }

}
