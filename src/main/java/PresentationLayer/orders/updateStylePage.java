/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.orders;

import FunctionLayer.DAOException;
import FunctionLayer.LogicFacade;
import FunctionLayer.entities.StyleOption;
import PresentationLayer.Command;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Perlt
 */
public class updateStylePage extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DAOException {
        
        List<StyleOption> claddingList = LogicFacade.getCladdingList();
        List<StyleOption> tileList = LogicFacade.getTileList();
        
        request.setAttribute("claddingList", claddingList);
        request.setAttribute("tileList", tileList);
        
         return "WEB-INF/updateStylePage";
    }
    
}
