package PresentationLayer;

import FunctionLayer.DAOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 The purpose of UnknownCommand is to...

 @author kasper
 */
public class UnknownCommand extends Command {

    @Override
    public String execute( HttpServletRequest request, HttpServletResponse response ) throws DAOException {
        String msg = "Seems something went wrong with getting your page. We can assure you our monkeys are working hard to fix it. Try again later thank you";
        throw new DAOException( msg );
    }
}
