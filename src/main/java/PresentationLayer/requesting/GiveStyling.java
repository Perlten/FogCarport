/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.requesting;

import FunctionLayer.Calculator;
import FunctionLayer.FOGException;
import FunctionLayer.LogicFacade;
import FunctionLayer.entities.Customization;
import FunctionLayer.entities.Order;
import PresentationLayer.Command;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author adamlass
 */
public class GiveStyling implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws FOGException {
        String submit = "";
        submit = request.getParameter("submit");
        if (submit.equals("Back")) {
            return "index";
        }

        Order order = (Order) request.getSession().getAttribute("order");

        int cladding = 0;
        int tile = 0;

        Customization cust = order.getCustomization();

        try {
            tile = Integer.parseInt(request.getParameter("tile"));
            if (order.getCustomization().getShed() != null) {
                cladding = Integer.parseInt(request.getParameter("cladding"));
                cust.setCladding(LogicFacade.getCladding(cladding));
            }

        } catch (Exception e) {
            throw new FOGException("Please choose a style for all categories!");
        }
        cust.setTile(LogicFacade.getTile(tile));

        Calculator calc = LogicFacade.getCalculator(order);
        calc.calculate();
        order.setPrice(calc.totalPrice());

        //setting allowed
        HashMap<String, Boolean> allowed = (HashMap<String, Boolean>) request.getSession().getAttribute("allowed");
        allowed.put("Credentials", true);

        if (submit.equals("Update")) {
            return new StylingPage().execute(request, response);
        }

        return "WEB-INF/requesting/credentials";
    }

}
