/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.requesting;

import FunctionLayer.FOGException;
import FunctionLayer.LogicFacade;
import FunctionLayer.entities.Customization;
import FunctionLayer.entities.Order;
import PresentationLayer.Command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author adamlass
 */
public class GiveStyling extends Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws FOGException {
        try {
            Order order = (Order) request.getSession().getAttribute("order");
            
            int cladding = Integer.parseInt(request.getParameter("cladding"));
            int tile = Integer.parseInt(request.getParameter("tile"));
            
            Customization cust = order.getCustomization();
            
            cust.setCladding(LogicFacade.getCladding(cladding));
            cust.setTile(LogicFacade.getTile(tile));
            
        } catch (Exception e) {
            throw new FOGException("Could not submit styling");
        }
        return "WEB-INF/credentialsPage";
//        return new Styling().execute(request, response);
    }
    
    
}
