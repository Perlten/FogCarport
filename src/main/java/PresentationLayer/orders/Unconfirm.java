/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.orders;

import FunctionLayer.FOGException;
import FunctionLayer.LogicFacade;
import PresentationLayer.Command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Perlt
 */
public class Unconfirm extends Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws FOGException {
        int id = Integer.parseInt(request.getParameter("orderId"));
        
        LogicFacade.unconfirmOrder(id);
        
        return new ShowOrder().execute(request, response);
    }
    
}
