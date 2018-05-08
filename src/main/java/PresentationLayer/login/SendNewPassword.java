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
public class SendNewPassword extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws FOGException {
        String email = request.getParameter("email");
        LogicFacade.SendNewPasswordToEmployee(email);
        return "employeeLogin";
    }
    
}
