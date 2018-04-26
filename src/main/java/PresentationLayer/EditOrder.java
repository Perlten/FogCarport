/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import FunctionLayer.entities.Customization;
import FunctionLayer.entities.Order;
import FunctionLayer.entities.Shed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Perlt
 */
public class EditOrder extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {

        int length = Integer.parseInt(request.getParameter("length"));
        int height = Integer.parseInt(request.getParameter("height"));
        int width = Integer.parseInt(request.getParameter("width"));
        double roofAngle = Double.parseDouble(request.getParameter("roofAngle"));
        int shedLength = Integer.parseInt(request.getParameter("shedLength"));
        int shedWidth = Integer.parseInt(request.getParameter("shedWidth"));
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        Order order = LogicFacade.getOrder(orderId);

        Customization c = order.getCustomization();
        Shed shed = c.getShed();
        c.setLength(length);
        c.setHeight(height);
        c.setWidth(width);
        c.setRoofangle(roofAngle);
        if (order.getCustomization().getShed() != null) {
            shed.setLength(shedLength);
            shed.setWidth(shedWidth);
        }
        LogicFacade.changeOrder(order);

        return new GetOrders().execute(request, response);
    }

}
