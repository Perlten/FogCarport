/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.requesting;

import FunctionLayer.FOGException;
import FunctionLayer.LogicFacade;
import FunctionLayer.Calculator;
import FunctionLayer.entities.Employee;
import FunctionLayer.entities.Event;
import FunctionLayer.entities.Order;
import PresentationLayer.Command;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author adamlass
 */
public class SubmitOrder extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws FOGException {
        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("order");
        LogicFacade.makeOrder(order);

        Calculator calc = new Calculator(order);
        calc.calculate();
        LogicFacade.writeLines(calc.getProducts(), order.getOrderid());

        order.setOrdered(true);
        session.setAttribute("order", null);
        request.setAttribute("confirmedId", order.getOrderid());
        LogicFacade.sendEmailToCustomer(order);
        LogicFacade.emailToAllEmployeeWithNewOrder(order.getOrderid());

        //event
        LogicFacade.writeOrderEvent(new Event(1, order.getOrderid()));

        //setting allowed
        HashMap<String, Boolean> allowed = (HashMap<String, Boolean>) request.getSession().getAttribute("allowed");
        allowed.put("Dimentions", false);
        allowed.put("Styling", false);
        allowed.put("Credentials", false);

        return new LoadOrderPage().execute(request, response);
    }

}
