package PresentationLayer;

import FunctionLayer.FOGException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UnknownCommand implements Command {

    @Override
    public String execute( HttpServletRequest request, HttpServletResponse response ) throws FOGException {
        String msg = "Could not find your page";
        throw new FOGException( msg );
    }
}
