/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.orders;

import FunctionLayer.Calculator;
import FunctionLayer.FOGException;
import FunctionLayer.LogicFacade;
import FunctionLayer.entities.Customer;
import FunctionLayer.entities.Customization;
import FunctionLayer.entities.Employee;
import FunctionLayer.entities.Event;
import FunctionLayer.entities.Order;
import FunctionLayer.entities.Shed;
import PresentationLayer.Command;
import static PresentationLayer.Invoker.PATTERN;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Perlt
 */
public class EditOrder implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws FOGException {

        //price
        double price = Double.parseDouble(request.getParameter("price"));
        if (price < 0) {
            throw new FOGException("Price can't be under 0!");
        }

        //Customer
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        int phoneNumber = Integer.parseInt(request.getParameter("phoneNumber"));

        if (!PATTERN.matcher(email).matches()) {
            throw new FOGException("Not a valid email");
        }

        //Dimentions
        int length = Integer.parseInt(request.getParameter("length"));
        int height = Integer.parseInt(request.getParameter("height"));
        int width = Integer.parseInt(request.getParameter("width"));
        double roofAngle = Double.parseDouble(request.getParameter("roofAngle"));
        int shedLength = Integer.parseInt(request.getParameter("shedLength"));
        int shedWidth = Integer.parseInt(request.getParameter("shedWidth"));
        boolean shed = Boolean.parseBoolean(request.getParameter("shed"));

        //order
        int orderId = Integer.parseInt(request.getParameter("orderId"));

        //styling
        int tileId = Integer.parseInt(request.getParameter("tile"));

        int claddingId = 0;
        if (shed) {
            claddingId = Integer.parseInt(request.getParameter("cladding"));
        }

        Order order = LogicFacade.getOrder(orderId);

        Customization c = order.getCustomization();
        c.setLength(length);
        c.setHeight(height);
        c.setWidth(width);
        c.setRoofangle(roofAngle);
        c.setTile(LogicFacade.getTile(tileId));

        if (shed) {
            c.setCladding(LogicFacade.getCladding(claddingId));
        } else {
            c.setCladding(null);
        }

        //recalculate
        Calculator calc = LogicFacade.getCalculator(order);
        LogicFacade.removeLines(orderId);
        calc.calculate();
        LogicFacade.writeLines(calc.getProducts(), orderId);
        order.setPrice(price);

        if (shed) {
            c.setShed(new Shed(shedLength, shedWidth));
        } else {
            c.setShed(null);
        }

        Customer customer = order.getCustomer();
        customer.setFirstname(firstName);
        customer.setLastname(lastName);
        customer.setEmail(email);
        customer.setPhonenumber(phoneNumber);

        LogicFacade.changeOrder(order);

        //event
        Employee emp = (Employee) request.getSession().getAttribute("employee");
        LogicFacade.writeOrderEmployeeEvent(new Event(emp, 4, orderId));

        return new ShowOrder().execute(request, response);
    }

}
