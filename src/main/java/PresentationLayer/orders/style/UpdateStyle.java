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
import FunctionLayer.entities.StyleOption;
import PresentationLayer.Command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Perlt
 */
public class UpdateStyle implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws FOGException {

        int id = Integer.parseInt(request.getParameter("styleId"));
        String type = request.getParameter("type");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));

        StyleOption selectedStyle = null;
        if (type.equals("cladding")) {
            selectedStyle = LogicFacade.getCladding(id);
        }
        if (type.equals("tile")) {
            selectedStyle = LogicFacade.getTile(id);
        }

        selectedStyle.setName(name);
        selectedStyle.setDescription(description);
        selectedStyle.setPrice(price);

        if (type.equals("cladding")) {
            LogicFacade.updateCladding(selectedStyle, selectedStyle.getId());
        }
        if (type.equals("tile")) {
            LogicFacade.updateTile(selectedStyle, selectedStyle.getId());
        }

        Employee emp = (Employee) request.getSession().getAttribute("employee");
        LogicFacade.writeEmployeeEvent(new Event(emp, 13));

        return new GetStyle().execute(request, response);
    }

}
