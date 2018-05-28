/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.editEmployee;

import FunctionLayer.FOGException;
import FunctionLayer.LogicFacade;
import FunctionLayer.entities.Employee;
import FunctionLayer.entities.Event;
import PresentationLayer.Command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Perlt
 */
public class FireEmployee implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws FOGException {

        int employeeId = Integer.parseInt(request.getParameter("employeeId"));
        LogicFacade.fireEmployee(employeeId);

        Employee emp = (Employee) request.getSession().getAttribute("employee");
        LogicFacade.writeEmployeeEvent(new Event(emp, 9));
        return new EditEmployee().execute(request, response);
    }

}
