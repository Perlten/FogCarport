/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.orders;

import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import FunctionLayer.entities.Order;
import FunctionLayer.entities.StyleOption;
import PresentationLayer.Command;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Perlt
 */
public class EditOrderPage extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        int id = Integer.parseInt(request.getParameter("orderToEdit")); 
        try {
            Order order = LogicFacade.getOrder(id);
            List<StyleOption> claddingList = LogicFacade.getCladdingList();
            List<StyleOption> tileList = LogicFacade.getTileList();
            
            request.setAttribute("claddingList", claddingList);
            request.setAttribute("tileList", tileList);
            request.setAttribute("order", order);
        } catch (Exception ex) {
            throw new LoginSampleException(ex.getMessage());
        }
        return "WEB-INF/editOrder";
    }
}
