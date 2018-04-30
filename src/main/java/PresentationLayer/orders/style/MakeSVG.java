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
public class MakeSVG extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DAOException {
        
        request.setAttribute("order", LogicFacade.getOrder(365));
        
        return "testSVG";
    }
    
}
