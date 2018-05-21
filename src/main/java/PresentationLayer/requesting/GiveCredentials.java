/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.requesting;

import FunctionLayer.FOGException;
import FunctionLayer.entities.Customer;
import FunctionLayer.entities.Order;
import PresentationLayer.Command;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Perlt
 */
public class GiveCredentials extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws FOGException {
        String submit = "";

            submit = request.getParameter("submit");
            if (submit.equals("Back")) {
                return new StylingPage().execute(request, response);
            }
            Order order = (Order) request.getSession().getAttribute("order");

            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            int phoneNumber = Integer.parseInt(request.getParameter("phoneNumber"));

            Customer customer = new Customer(firstName, lastName, email, phoneNumber);
            order.setCustomer(customer);
            
            //setting allowed
            HashMap<String, Boolean> allowed = (HashMap<String, Boolean>) request.getSession().getAttribute("allowed");
            allowed.put("Confirm", true);

       
        if (submit.equals("Update")) {
            return new GiveCredentialsPage().execute(request, response);
        }

        return new LoadOrderPage().execute(request, response);
    }

}
