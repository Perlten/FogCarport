package PresentationLayer.editEmployee;

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
public class EditEmployee implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws FOGException {
        int empId = Integer.parseInt(request.getParameter("employeeId"));
        Employee selectedEmployee = LogicFacade.getEmployeeById(empId);
        request.setAttribute("selectedEmployee", selectedEmployee);
        request.setAttribute("eventList", LogicFacade.getEmployeeEvent(selectedEmployee.getEmployeeId(), 10));
        
        
       return new UpdateStaffPage().execute(request, response);
    }
    
}
