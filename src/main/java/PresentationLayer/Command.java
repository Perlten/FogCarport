package PresentationLayer;

import PresentationLayer.requesting.GiveCredentialsPage;
import PresentationLayer.orders.ShowOrder;
import PresentationLayer.orders.EditOrderPage;
import PresentationLayer.orders.EditOrder;
import PresentationLayer.orders.DeleteOrder;
import PresentationLayer.orders.ConfirmOrder;
import PresentationLayer.orders.GetOrders;
import FunctionLayer.FOGException;
import PresentationLayer.login.EditEmployee;
import PresentationLayer.login.FireEmployee;
import PresentationLayer.login.LoginVerification;
import PresentationLayer.login.NewPassword;
import PresentationLayer.login.Overview;
import PresentationLayer.login.ResetPassword;
import PresentationLayer.login.SendNewPassword;
import PresentationLayer.login.UpdateEmployee;
import PresentationLayer.login.UpdateStaff;
import PresentationLayer.orders.Unconfirm;
import PresentationLayer.orders.style.CreateStyle;
import PresentationLayer.orders.style.CreateStylePage;
import PresentationLayer.orders.style.DeleteStyle;
import PresentationLayer.orders.style.UpdateStyle;
import PresentationLayer.orders.style.GetStyle;
import PresentationLayer.orders.style.updateStylePage;
import PresentationLayer.requesting.GiveCredentials;
import PresentationLayer.requesting.GiveDimentions;
import PresentationLayer.requesting.GiveDimentionsPage;
import PresentationLayer.requesting.GiveStyling;
import PresentationLayer.requesting.LoadOrder;
import PresentationLayer.requesting.Styling;
import PresentationLayer.requesting.SubmitOrder;
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
        commands.put("GetStyle", new GetStyle());
        commands.put("UpdateStyle", new UpdateStyle());
        commands.put("CreateStylePage", new CreateStylePage());
        commands.put("CreateStyle", new CreateStyle());
        commands.put("DeleteStyle", new DeleteStyle());
        commands.put("GiveStyling", new GiveStyling());
        commands.put("GiveCredentials", new GiveCredentials());
        commands.put("Unconfirm", new Unconfirm());
        commands.put("GiveDimentionsPage", new GiveDimentionsPage());
        commands.put("GiveCredentialsPage", new GiveCredentialsPage());
        commands.put("SubmitOrder", new SubmitOrder());
        commands.put("LoadOrder", new LoadOrder());
        commands.put("LoginVerification", new LoginVerification());
        commands.put("SendNewPassword", new SendNewPassword());
        commands.put("Overview", new Overview());
        commands.put("UpdateStaff", new UpdateStaff());
        commands.put("EditEmployee", new EditEmployee());
        commands.put("UpdateEmployee", new UpdateEmployee());
        commands.put("FireEmployee", new FireEmployee());
        commands.put("ResetPassword", new ResetPassword());
        commands.put("NewPassword", new NewPassword());
    }

    static Command from( HttpServletRequest request ) {
        String commandName = request.getParameter( "command" );
        if ( commands == null ) {
            initCommands();
        }
        return commands.getOrDefault(commandName, new UnknownCommand() );
    }

    public abstract String execute( HttpServletRequest request, HttpServletResponse response ) 
            throws FOGException;

}
