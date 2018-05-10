package PresentationLayer.editEmployee;

import FunctionLayer.FOGException;
import FunctionLayer.LogicFacade;
import FunctionLayer.entities.Employee;
import PresentationLayer.Command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        return new EditEmployee().execute(request, response);
    }
    
}
