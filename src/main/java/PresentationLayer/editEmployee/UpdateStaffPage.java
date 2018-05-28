/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.editEmployee;

import FunctionLayer.FOGException;
import FunctionLayer.LogicFacade;
import PresentationLayer.Command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Perlt
 */
public class UpdateStaffPage implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws FOGException {
        request.setAttribute("empList", LogicFacade.getAllEmployees());
        return "WEB-INF/update/updateStaff";
    }

}
