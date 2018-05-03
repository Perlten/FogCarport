/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.requesting;

import FunctionLayer.FOGException;
import FunctionLayer.entities.Customer;
import FunctionLayer.entities.Customization;
import FunctionLayer.entities.Order;
import FunctionLayer.entities.Shed;
import FunctionLayer.entities.StyleOption;
import PresentationLayer.Command;
import PresentationLayer.Helper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Used to submit the dimentions from indexpage.
 *
 * If no roof is selected the angle is just set 0.
 *
 * If an input is missing it will just be set to 0
 *
 *
 * @author adamlass
 */
public class GiveDimentions extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws FOGException {
        try {
            int length = Helper.safeInt(request, "length");
            int width = Helper.safeInt(request, "width");
            int height = Helper.safeInt(request, "height");

            boolean roof = Boolean.parseBoolean(request.getParameter("roof"));

            boolean shed = Boolean.parseBoolean(request.getParameter("shed"));

            double roofAngle = Helper.safeDouble(request, "roofAngle");
            int shedLength = Helper.safeInt(request, "shedLength");
            int shedWidth = Helper.safeInt(request, "shedWidth");

            if (roof) {
                if (roofAngle <= 0 || roofAngle > 89) {
                    throw new IllegalArgumentException();
                }
            } else {
                roofAngle = 0;
            }

            Shed shedObj = null;
            if (shed) {
                shedObj = new Shed(shedLength, shedWidth);
            }

            Order sessionOrder = (Order) request.getSession().getAttribute("order");
            Customization sessionCustomization = null;
            StyleOption cladding = null;
            StyleOption tile = null;
            Customer sessionCustomer = null;

            if (sessionOrder != null) {
                sessionCustomization = sessionOrder.getCustomization();
                cladding = sessionCustomization.getCladding();
                tile = sessionCustomization.getTile();
                sessionCustomer = sessionOrder.getCustomer();
            }

            Customization customization = new Customization(length, width, height, roofAngle, shedObj, cladding, tile);
            Order order = new Order(sessionCustomer, customization);

            request.getSession().setAttribute("order", order);
        } catch (Exception e) {
            throw new FOGException("Could not submit customization!");
        }
        return new Styling().execute(request, response);
//        return "SVGleg";
    }

}
