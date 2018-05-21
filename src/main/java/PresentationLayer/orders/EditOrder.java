/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.orders;

import FunctionLayer.LogicFacade;
import FunctionLayer.FOGException;
import FunctionLayer.Calculator;
import static FunctionLayer.LogicFacade.PATTERN;
import FunctionLayer.entities.Customer;
import FunctionLayer.entities.Customization;
import FunctionLayer.entities.Employee;
import FunctionLayer.entities.Event;
import FunctionLayer.entities.Order;
import FunctionLayer.entities.Shed;
import PresentationLayer.Command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Perlt
 */
public class EditOrder extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws FOGException {

        //Dimensions
        int length = Integer.parseInt(request.getParameter("length"));
        int height = Integer.parseInt(request.getParameter("height"));
        int width = Integer.parseInt(request.getParameter("width"));
        double roofAngle = Double.parseDouble(request.getParameter("roofAngle"));
        int shedLength = Integer.parseInt(request.getParameter("shedLength"));
        int shedWidth = Integer.parseInt(request.getParameter("shedWidth"));
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        int claddingId = Integer.parseInt(request.getParameter("cladding"));
        int tileId = Integer.parseInt(request.getParameter("tile"));
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

        String isShed = request.getParameter("shed");

        Order order = LogicFacade.getOrder(orderId);

        Customization c = order.getCustomization();
        c.setLength(length);
        c.setHeight(height);
        c.setWidth(width);
        c.setRoofangle(roofAngle);
        c.setCladding(LogicFacade.getCladding(claddingId));
        c.setTile(LogicFacade.getTile(tileId));

        //recalculate
        Calculator calc = LogicFacade.getCalculator(order);
        LogicFacade.removeLines(orderId);
        calc.calculate();
        LogicFacade.writeLines(calc.getProducts(), orderId);
        order.setPrice(price);

        if (isShed == null) {
            c.setShed(null);
        } else if (isShed.equals("true")) {
            c.setShed(new Shed(shedLength, shedWidth));
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
