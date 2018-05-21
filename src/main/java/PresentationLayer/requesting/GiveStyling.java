/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.requesting;

import FunctionLayer.FOGException;
import FunctionLayer.LogicFacade;
import FunctionLayer.Calculator;
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
public class GiveStyling extends Command {

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
        try {
            cladding = Integer.parseInt(request.getParameter("cladding"));
            tile = Integer.parseInt(request.getParameter("tile"));
        } catch (Exception ex) {
            throw new FOGException("You must choose a cladding and a tile option");
        }
        Customization cust = order.getCustomization();

        cust.setCladding(LogicFacade.getCladding(cladding));
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
