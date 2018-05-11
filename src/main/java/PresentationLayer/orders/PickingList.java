/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.orders;

import FunctionLayer.FOGException;
import FunctionLayer.LogicFacade;
import FunctionLayer.calculator.Calculator;
import FunctionLayer.entities.Customization;
import FunctionLayer.entities.Shed;
import PresentationLayer.Command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author adamlass
 */
public class PickingList extends Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws FOGException {
        int orderId = Integer.parseInt(request.getParameter("orderPicking"));
        Calculator calc = new Calculator(LogicFacade.getOrder(orderId));
        calc.calculate();
        request.setAttribute("pickingList", calc.getProducts());
        return "WEB-INF/pickingListPage";
    }
    
}
