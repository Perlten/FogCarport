/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.orders;

import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import FunctionLayer.entities.Customer;
import FunctionLayer.entities.Customization;
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
    public String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {

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
        
        //Customer
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        int phoneNumber = Integer.parseInt(request.getParameter("phoneNumber"));
        
        String isShed = request.getParameter("shed");
        Order order = LogicFacade.getOrder(orderId);

        Customization c = order.getCustomization();
        c.setLength(length);
        c.setHeight(height);
        c.setWidth(width);
        c.setRoofangle(roofAngle);
        c.setCladding(LogicFacade.getCladding(claddingId));
        c.setTile(LogicFacade.getTile(tileId));
        
        if(isShed == null){
            order.getCustomization().setShed(null);
        }else if (isShed.equals("true")) {
            order.getCustomization().setShed(new Shed(shedLength, shedWidth));
        }
        
        Customer customer = order.getCustomer();
        customer.setFirstname(firstName);
        customer.setLastname(lastName);
        customer.setEmail(email);
        customer.setPhonenumber(phoneNumber);
        
        LogicFacade.changeOrder(order);

        return new ShowOrder().execute(request, response);
    }

}
