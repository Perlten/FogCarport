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
public class UpdateEmployee extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws FOGException {
        
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String username = request.getParameter("username");
        int accessLevel = Integer.parseInt(request.getParameter("accessLevel"));
        String email = request.getParameter("email");
        
        
        int empId = Integer.parseInt(request.getParameter("employeeId"));
        Employee selectedEmp = LogicFacade.getEmployeeById(empId);
        
        selectedEmp.setFirstname(firstName);
        selectedEmp.setLastname(lastName);
        selectedEmp.setUsername(username);
        selectedEmp.setAuthenticationLevel(accessLevel);
        selectedEmp.setEmail(email);
        
        LogicFacade.UpdateEmployee(selectedEmp);
        return new UpdateStaff().execute(request, response);
    }
    
}
