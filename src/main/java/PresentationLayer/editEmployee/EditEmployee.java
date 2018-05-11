package PresentationLayer.editEmployee;

import PresentationLayer.editEmployee.UpdateStaff;
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
public class EditEmployee extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws FOGException {
        int empId = Integer.parseInt(request.getParameter("employeeId"));
        Employee selectedEmployee = LogicFacade.getEmployeeById(empId);
        request.setAttribute("selectedEmployee", selectedEmployee);
        
       return new UpdateStaff().execute(request, response);
    }
    
}
