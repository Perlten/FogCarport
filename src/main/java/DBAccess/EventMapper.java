/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import FunctionLayer.FOGException;
import FunctionLayer.entities.Event;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author adamlass
 */
public class EventMapper {

    private Connection con;

    public EventMapper() throws FOGException {
        try {
            con = new LiveConnection().connection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new FOGException("Could not find connection");
        }
    }

    public EventMapper(Connection con) {
        this.con = con;
    }

    /**
     * Write an order with a dummy-object of event.
     *
     * @param event
     * @throws FunctionLayer.FOGException
     */
    public void writeOrderEvent(Event event) throws FOGException {
        try {

            String sql = "INSERT INTO event(idevent_type, idorder) values (?,?)";
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, event.getEventType());
            pre.setInt(2, event.getOrderid());
            pre.execute();
        } catch (SQLException e) {
            throw new FOGException("Could not write order event");
        }
    }

    public void writeOrderEmployeeEvent(Event event) throws FOGException {
        try {
            String sql = "INSERT INTO event(idevent_type, idorder, employee) values (?,?,?)";
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, event.getEventType());
            pre.setInt(2, event.getOrderid());
            pre.setInt(3, event.getEmployee());
            pre.execute();
        } catch (SQLException e) {
            throw new FOGException("Could not write order employee event");
        }
    }

    public void writeEmployeeEvent(Event event) throws FOGException {
        try {

            String sql = "INSERT INTO event(idevent_type, employee) values (?,?)";
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, event.getEventType());
            pre.setInt(2, event.getEmployee());
            pre.execute();
        } catch (SQLException e) {
            throw new FOGException("Could not write employee event");

        }
    }

    /**
     * Get event list with an orderid as reference.
     *
     * @param orderid the id of the order that we want the events on
     * @return a list of events concerning this order
     * @throws FunctionLayer.FOGException
     */
    public List<Event> getOrderEvent(int orderid) throws FOGException {
        try {

            String sql = "SELECT * FROM event "
                    + "INNER JOIN event_type "
                    + "ON event.idevent_type = event_type.idevent_type "
                    + "WHERE idorder = ? order by idevent desc";

            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, orderid);

            return convert(pre.executeQuery());

        } catch (SQLException e) {
            throw new FOGException("Could not find events");
        }
    }

    /**
     * Get event list with an employee as reference.
     *
     * @param employeeId the id of the order that we want the events on
     * @return a list of events concerning this order
     * @throws FunctionLayer.FOGException
     */
    public List<Event> getEmployeeEvent(int employeeId, int limit) throws FOGException {
        try {

            String sql = "SELECT * FROM event "
                    + "INNER JOIN event_type "
                    + "ON event.idevent_type = event_type.idevent_type "
                    + "WHERE employee = ? order by idevent desc";

            if (limit > 0) {
                sql += " LIMIT ?";
            }

            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, employeeId);

            if (limit > 0) {
                pre.setInt(2, limit);
            }

            return convert(pre.executeQuery());
        } catch (SQLException e) {
            throw new FOGException(e.getMessage());
        }
    }

    public List<Event> getAllEvents(int limit) throws FOGException {
        try {
            String sql = "SELECT * FROM event "
                    + "INNER JOIN event_type "
                    + "ON event.idevent_type = event_type.idevent_type "
                    + "order by idevent desc";

            if (limit > 0) {
                sql += " LIMIT ?";
            }

            PreparedStatement pre = con.prepareStatement(sql);

            if (limit > 0) {
                pre.setInt(1, limit);
            }

            return convert(pre.executeQuery());
        } catch (Exception e) {
            throw new FOGException("Could not get all orders");
        }
    }

    /**
     * Converts a resultset of events with innerjoined event_types to a list of
     * Event-objects
     *
     * @param res resultset made with innerjoined event_types
     * @return A list of Event objects from db
     * @throws SQLException
     */
    private List<Event> convert(ResultSet res) throws SQLException {
        List<Event> events = new ArrayList<>();
        while (res.next()) {
            int eventId = res.getInt("idevent");
            int eventType = res.getInt("idevent_type");
            int orderId = res.getInt("idorder");
            int asignee = res.getInt("employee");
            int accessLevel = res.getInt("access_level");

            Calendar date = Calendar.getInstance();
            Timestamp ts = res.getTimestamp("date");
            date.setTime((Date) ts);

            String title = res.getString("title");
            String description = res.getString("description");
            String eventName = res.getString("name");
            String statusColor = res.getString("statuscolor");
            events.add(new Event(eventId, orderId, asignee, accessLevel, eventType, title, description, eventName, date, statusColor));
        }
        return events;
    }

}
