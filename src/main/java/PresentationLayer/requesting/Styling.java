/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.requesting;

import FunctionLayer.DAOException;
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
public class Styling extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DAOException {
        try {
            List<StyleOption> claddings = LogicFacade.getCladdingList();
            request.setAttribute("claddings", claddings);
            List<StyleOption> tiles = LogicFacade.getTileList() ;
            request.setAttribute("tiles", claddings);
        } catch (Exception e) {
            throw new DAOException("StyleOptions could not load!");
        }
        return "WEB-INF/styling";

    }

}
