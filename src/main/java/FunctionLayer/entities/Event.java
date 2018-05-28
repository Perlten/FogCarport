/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.entities;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author adamlass
 */
public class Event {

    private int eventId, orderid, employee, accessLevel, eventType;
    private String title, description, eventName, statusColor;
    private Calendar date;

    public Event(Employee employee, int eventType) {
        this(-1, -1, employee.getEmployeeId(), -1, eventType, null, null, null, null, null);
    }

    public Event(Employee employee, int eventType, int orderid) {
        this(-1, orderid, employee.getEmployeeId(), -1, eventType, null, null, null, null, null);
    }

    public Event(int eventType, int orderid) {
        this(-1, orderid, -1, -1, eventType, null, null, null, null, null);
    }

    public Event(int eventId, int orderid, int employee, int accessLevel, int eventType, String title, String description, String eventName, Calendar date, String statusColor) {
        this.eventId = eventId;
        this.orderid = orderid;
        this.employee = employee;
        this.accessLevel = accessLevel;
        this.eventType = eventType;
        this.title = title;
        this.description = description;
        this.eventName = eventName;
        this.date = date;
        this.statusColor = statusColor;
    }

    public String getShortDescription() {
        if (description.length() >= 40) {
            return description.substring(0, 40) + "...";
        } else {
            return description;
        }
    }

    public int getEventId() {
        return eventId;
    }

    public int getOrderid() {
        return orderid;
    }

    public int getEmployee() {
        return employee;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public int getEventType() {
        return eventType;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getEventName() {
        return eventName;
    }

    public Calendar getDate() {
        return date;
    }

    public String simpleDate() {
        SimpleDateFormat sp = new SimpleDateFormat("dd/MM/YYYY HH:mm");
        return sp.format(date.getTime());
    }

    public String getStatusColor() {
        return statusColor;
    }

    @Override
    public String toString() {
        return "Event{" + "eventId=" + eventId + ", orderid=" + orderid + ", employee=" + employee + ", accessLevel=" + accessLevel + ", eventType=" + eventType + ", title=" + title + ", description=" + description + ", eventName=" + eventName + ", statusColor=" + statusColor + ", date=" + date + '}';
    }

}
