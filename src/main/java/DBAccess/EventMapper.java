/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import FunctionLayer.entities.Event;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author adamlass
 */
public class EventMapper {

    /**
     * Write an order with a dummy-object of event.
     * @param event
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public static void writeEvent(Event event) throws SQLException, ClassNotFoundException {
        Connection con = Connector.connection();

        String sql = "INSERT INTO fog.event(idevent_type, idorder) values (?,?)";
        PreparedStatement pre = con.prepareStatement(sql);
        pre.setInt(1, event.getEventType());
        pre.setInt(2, event.getOrderid());
        pre.executeUpdate();
    }

    /**
     * Get event list with an orderid as reference.
     *
     * @param orderid the id of the order that we want the events on
     * @return a list of events concerning this order
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static List<Event> getEvents(int orderid) throws ClassNotFoundException, SQLException {
        Connection con = Connector.connection();

        String sql = "SELECT * FROM fog.event "
                + "INNER JOIN fog.event_type "
                + "ON event.idevent_type = event_type.idevent_type "
                + "WHERE idorder = ? order by idevent desc";

        PreparedStatement pre = con.prepareStatement(sql);
        pre.setInt(1, orderid);

        return convert(pre.executeQuery());
    }

    /**
     * Converts a resultset of events with innerjoined event_types to a list of
     * Event-objects
     *
     * @param res resultset made with innerjoined event_types
     * @return A list of Event objects from db
     * @throws SQLException
     */
    private static List<Event> convert(ResultSet res) throws SQLException {
        List<Event> events = new ArrayList<>();
        while (res.next()) {
            int eventId = res.getInt("idevent");
            int eventType = res.getInt("idevent_type");
            int orderId = res.getInt("idorder");
            int asignee = res.getInt("asignee");
            int accessLevel = res.getInt("access_level");

            Calendar date = Calendar.getInstance();
            Timestamp ts = res.getTimestamp("date");
            date.setTime((Date) ts);

            String title = res.getString("title");
            String description = res.getString("description");
            String eventName = res.getString("name");
            events.add(new Event(eventId, orderId, asignee, accessLevel, eventType, title, description, eventName, date));
        }
        return events;
    }

}
