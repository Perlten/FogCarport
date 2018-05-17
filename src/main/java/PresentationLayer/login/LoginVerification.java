/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.login;

import FunctionLayer.FOGException;
import FunctionLayer.LogicFacade;
import FunctionLayer.entities.Employee;
import FunctionLayer.entities.Event;
import PresentationLayer.Command;
import PresentationLayer.orders.GetOrdersPage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Perlt
 */
public class LoginVerification extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws FOGException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Employee emp = LogicFacade.verfyLogin(username, password);
        request.getSession().setAttribute("employee", emp);

        //event
        LogicFacade.writeEmployeeEvent(new Event(emp, 6));

        if (emp.isResetPassword()) {
            return "WEB-INF/employeeLogin/newPassword";
        }

        return new employeeOverviewPage().execute(request, response);
    }

}
