package PresentationLayer;

import FunctionLayer.LoginSampleException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 The purpose of UnknownCommand is to...

 @author kasper
 */
public class UnknownCommand extends Command {

    @Override
    String execute( HttpServletRequest request, HttpServletResponse response ) throws LoginSampleException {
        String msg = "TODO: Be real son -Adam";
        throw new LoginSampleException( msg );
    }
}
