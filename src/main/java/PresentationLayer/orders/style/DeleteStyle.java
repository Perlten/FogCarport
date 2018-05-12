/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.orders.style;

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
public class DeleteStyle extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws FOGException {

        int id = Integer.parseInt(request.getParameter("styleId"));
        String type = request.getParameter("type");
        
        if(type.equals("cladding")){
            LogicFacade.removeCladding(id);
        }
        if(type.equals("tile")){
            LogicFacade.removeTile(id);
        }
        
        
        Employee emp = (Employee) request.getSession().getAttribute("employee");
        LogicFacade.writeEmployeeEvent(new Event(emp, 12));
        
        return new updateStylePage().execute(request, response);
    }
}
