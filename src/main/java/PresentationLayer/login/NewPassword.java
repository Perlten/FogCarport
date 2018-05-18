package PresentationLayer.login;

import FunctionLayer.FOGException;
import FunctionLayer.LogicFacade;
import FunctionLayer.entities.Employee;
import PresentationLayer.Command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NewPassword extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws FOGException {

        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("passwordRepeat");
        Employee emp = (Employee) request.getSession().getAttribute("employee");

        if (!password.equals(repeatPassword)) {
            throw new FOGException("Password do not match");
        }
        try {
            LogicFacade.verfyLogin(emp.getUsername(), password);
        } catch (FOGException e) {
            LogicFacade.changePassword(emp.getEmployeeId(), password);
            return new employeeOverviewPage().execute(request, response);
        }
            throw new FOGException("The password you entered is the same as your old password");
    }
}
