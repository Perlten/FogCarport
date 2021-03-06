/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.requesting;

import FunctionLayer.Calculator;
import FunctionLayer.FOGException;
import FunctionLayer.LogicFacade;
import FunctionLayer.entities.Customer;
import FunctionLayer.entities.Customization;
import FunctionLayer.entities.Order;
import FunctionLayer.entities.Shed;
import FunctionLayer.entities.StyleOption;
import PresentationLayer.Command;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
public class GiveDimentions implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws FOGException {
        Order sessionOrder = (Order) request.getSession().getAttribute("order");

        int length = Integer.parseInt(request.getParameter("length"));
        int width = Integer.parseInt(request.getParameter("width"));
        int height = Integer.parseInt(request.getParameter("height"));

        boolean roof = Boolean.parseBoolean(request.getParameter("roof"));

        boolean shed = Boolean.parseBoolean(request.getParameter("shed"));

        double roofAngle = 0;
        int shedLength = 0;
        int shedWidth = 0;

        if (roof) {
            roofAngle = Double.parseDouble(request.getParameter("roofAngle"));
            if (roofAngle <= 0 || roofAngle > 89) {
                throw new FOGException("Invalid roof angle");
            }
        }
        if (shed) {
            shedLength = Integer.parseInt(request.getParameter("shedLength"));
            shedWidth = Integer.parseInt(request.getParameter("shedWidth"));
        } else {
            if (sessionOrder != null) {
                sessionOrder.getCustomization().setCladding(null);
            }
        }

        String submit = request.getParameter("submit");

        Shed shedObj = null;
        if (shed) {
            shedObj = new Shed(shedLength, shedWidth);
        }

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

        //Estimating price
        Calculator calc = LogicFacade.getCalculator(order);
        calc.calculate();
        order.setPrice(calc.totalPrice());

        //setting session objects
        HttpSession session = request.getSession();

        session.setAttribute("order", order);

        //setting allowed
        HashMap<String, Boolean> allowed = (HashMap<String, Boolean>) request.getSession().getAttribute("allowed");
        allowed.put("Dimentions", true);
        allowed.put("Styling", true);
        allowed.put("Confirm", false);

        if (submit.equals("Update")) {
            session.setAttribute("active", "Styling");
            return "index";
        }
        return new StylingPage().execute(request, response);
    }

}
