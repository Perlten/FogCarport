/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.requesting;

import FunctionLayer.FOGException;
import FunctionLayer.LogicFacade;
import FunctionLayer.entities.StyleOption;
import PresentationLayer.Command;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author adamlass
 */
public class StylingPage extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws FOGException {
        List<StyleOption> claddings = LogicFacade.getCladdingList();
        request.setAttribute("claddings", claddings);
        List<StyleOption> tiles = LogicFacade.getTileList();
        request.setAttribute("tiles", tiles);
        return "WEB-INF/requesting/styling";
    }

}
