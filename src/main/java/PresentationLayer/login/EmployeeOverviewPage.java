/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.login;

import FunctionLayer.FOGException;
import FunctionLayer.LogicFacade;
import FunctionLayer.entities.Employee;
import PresentationLayer.Command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Perlt
 */
public class EmployeeOverviewPage implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws FOGException {
        
        Employee emp = (Employee) request.getSession().getAttribute("employee");
        
        int confirmedOrder = LogicFacade.numberOfConfirmedOrder();
        request.setAttribute("numberOfConfirmedOrder", confirmedOrder);
        request.setAttribute("10UnconfirmedOrders", LogicFacade.getLatest10UnconfirmedOrders());
        request.setAttribute("eventList", LogicFacade.getEmployeeEvent(emp.getEmployeeId(), 10));
        
        return "WEB-INF/employeeFrontpage";
    }

}
