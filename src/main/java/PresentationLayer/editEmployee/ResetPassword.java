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
public class ResetPassword extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws FOGException {
        
        int selectedEmployee = Integer.parseInt(request.getParameter("employeeId"));
        
        LogicFacade.resetEmployeePassword(selectedEmployee);
        
        Employee emp = LogicFacade.getEmployeeById(selectedEmployee);
        LogicFacade.writeEmployeeEvent(new Event(emp, 10));
        return new EditEmployee().execute(request, response);
    }
    
}
