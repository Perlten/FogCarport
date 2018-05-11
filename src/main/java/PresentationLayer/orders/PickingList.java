/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.orders;

import FunctionLayer.FOGException;
import FunctionLayer.LogicFacade;
import FunctionLayer.calculator.Calculator;
import FunctionLayer.calculator.Product;
import FunctionLayer.entities.Order;
import PresentationLayer.Command;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author adamlass
 */
public class PickingList extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws FOGException {
        int orderId = Integer.parseInt(request.getParameter("orderPicking"));
        Order order = LogicFacade.getOrder(orderId);
        Calculator calc = new Calculator(order);
        LogicFacade.removeLines(orderId);
        calc.calculate();
        List<Product> products = LogicFacade.orderProducts(orderId);
        request.setAttribute("pickingList", products);
        return "WEB-INF/pickingListPage";
    }

}
