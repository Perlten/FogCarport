/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.requesting;

import FunctionLayer.DAOException;
import FunctionLayer.entities.Customization;
import FunctionLayer.entities.Order;
import FunctionLayer.entities.Shed;
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
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DAOException {
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
                

            //TODO Save customization Options
            Customization cust = new Customization(length, width, height, roofAngle, shedObj, null, null);
            Order order = new Order(null, cust);

            request.getSession().setAttribute("order", order);
        } catch (Exception e) {
            throw new DAOException("Could not submit customization!");
        }
        return "index";
    }

    

}
