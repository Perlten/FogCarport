/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.requesting;

import FunctionLayer.FOGException;
import FunctionLayer.LogicFacade;
import FunctionLayer.calculator.Calculator;
import FunctionLayer.entities.Employee;
import FunctionLayer.entities.Event;
import FunctionLayer.entities.Order;
import PresentationLayer.Command;
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
        order.setOrdered(true);
        session.setAttribute("order", null);
        session.setAttribute("confirmedOrder", order);
        LogicFacade.sendEmailToCustomer(order);

        //event
        Employee emp = (Employee) request.getSession().getAttribute("employee");
        LogicFacade.writeOrderEvent(new Event(1, order.getOrderid()));
        return new LoadOrder().execute(request, response);
    }

}
