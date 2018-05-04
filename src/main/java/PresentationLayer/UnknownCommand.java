package PresentationLayer;

import FunctionLayer.FOGException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 The purpose of UnknownCommand is to...

 @author kasper
 */
public class UnknownCommand extends Command {

    @Override
    public String execute( HttpServletRequest request, HttpServletResponse response ) throws FOGException {
        String msg = "Could not find your page";
        throw new FOGException( msg );
    }
}
