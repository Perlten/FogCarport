package PresentationLayer.orders;

import FunctionLayer.DAOException;
import FunctionLayer.LogicFacade;
import PresentationLayer.Command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class getStyle extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DAOException {
        
        int id = Integer.parseInt(request.getParameter("styleId"));
        String type = request.getParameter("type");
        
        if(type.equals("tile")){
            request.setAttribute("selectedStyle", LogicFacade.getTile(id));
            request.setAttribute("type", "tile");
        }
        if(type.equals("cladding")){
            request.setAttribute("selectedStyle", LogicFacade.getCladding(id));
            request.setAttribute("type", "cladding");
        }

        return new updateStylePage().execute(request, response);
    }

}
