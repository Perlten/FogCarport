/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.orders.style;

import FunctionLayer.DAOException;
import FunctionLayer.LogicFacade;
import PresentationLayer.Command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Perlt
 */
public class DeleteStyle extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DAOException {

        int id = Integer.parseInt(request.getParameter("styleId"));
        String type = request.getParameter("type");
        
        if(type.equals("cladding")){
            LogicFacade.removeCladding(id);
        }
        if(type.equals("tile")){
            LogicFacade.removeTile(id);
        }
        
        return new updateStylePage().execute(request, response);
    }
}
