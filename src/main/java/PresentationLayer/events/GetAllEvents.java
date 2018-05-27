/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.events;

import FunctionLayer.FOGException;
import FunctionLayer.LogicFacade;
import PresentationLayer.Command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author adamlass
 */
public class GetAllEvents implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws FOGException {
        int limit = Integer.parseInt(request.getParameter("limit"));
        request.setAttribute("allEvents", LogicFacade.getAllEvents(limit));
        return new GetAllEventsPage().execute(request, response);
    }

    
}
