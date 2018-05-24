/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.orders;

import FunctionLayer.FOGException;
import FunctionLayer.LogicFacade;
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
public class EditOrderPage implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws FOGException {
        int id = Integer.parseInt(request.getParameter("orderToEdit"));
        Order order = LogicFacade.getOrder(id);
        List<StyleOption> claddingList = LogicFacade.getCladdingList();
            List<StyleOption> tileList = LogicFacade.getTileList();

        request.setAttribute("claddingList", claddingList);
        request.setAttribute("tileList", tileList);
        request.setAttribute("order", order);
        return "WEB-INF/update/editOrder";
    }
}
