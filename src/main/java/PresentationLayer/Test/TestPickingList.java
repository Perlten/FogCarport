/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.Test;

import FunctionLayer.FOGException;
import FunctionLayer.calculator.Calculator;
import FunctionLayer.entities.Customization;
import PresentationLayer.Command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author adamlass
 */
public class TestPickingList extends Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws FOGException {
        Calculator calc = new Calculator( new Customization(100, 100, 100, 100, null, null, null));
        calc.calculate();
        request.setAttribute("pickingList", calc.getProducts());
        return "WEB-INF/orders";
    }
    
}
