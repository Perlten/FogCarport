package PresentationLayer;

import PresentationLayer.orders.ShowOrder;
import PresentationLayer.orders.EditOrderPage;
import PresentationLayer.orders.EditOrder;
import PresentationLayer.orders.DeleteOrder;
import PresentationLayer.orders.ConfirmOrder;
import PresentationLayer.orders.GetOrders;
import FunctionLayer.DAOException;
import PresentationLayer.orders.style.CreateStyle;
import PresentationLayer.orders.style.CreateStylePage;
import PresentationLayer.orders.style.DeleteStyle;
import PresentationLayer.orders.style.UpdateStyle;
import PresentationLayer.orders.style.getStyle;
import PresentationLayer.orders.style.updateStylePage;
import PresentationLayer.requesting.GiveDimentions;
import PresentationLayer.requesting.GiveStyling;
import PresentationLayer.requesting.Styling;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Command {

    private static HashMap<String, Command> commands;

    private static void initCommands() {
        commands = new HashMap<>();
        commands.put("GetOrders", new GetOrders());
        commands.put("ShowOrder", new ShowOrder());
        commands.put("ConfirmOrder", new ConfirmOrder());
        commands.put("DeleteOrder", new DeleteOrder());
        commands.put("EditOrderPage", new EditOrderPage());
        commands.put("EditOrder", new EditOrder());
        commands.put("UpdateStylePage", new updateStylePage());
        commands.put("GiveDimentions", new GiveDimentions());
        commands.put("Styling", new Styling());
        commands.put("GetStyle", new getStyle());
        commands.put("UpdateStyle", new UpdateStyle());
        commands.put("CreateStylePage", new CreateStylePage());
        commands.put("CreateStyle", new CreateStyle());
        commands.put("DeleteStyle", new DeleteStyle());
        commands.put("GiveStyling", new GiveStyling());
    }

    static Command from( HttpServletRequest request ) {
        String commandName = request.getParameter( "command" );
        if ( commands == null ) {
            initCommands();
        }
        return commands.getOrDefault(commandName, new UnknownCommand() );
    }

    public abstract String execute( HttpServletRequest request, HttpServletResponse response ) 
            throws DAOException;

}
