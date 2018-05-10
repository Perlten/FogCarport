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

    
    public Event(int orderid, int eventType) {
        this.eventId = -1;
        this.orderid = orderid;
        this.employee = -1;
        this.accessLevel = -1;
        this.eventType = eventType;
        this.title = null;
        this.description = null;
        this.eventName = null;
        this.date = null;
        this.statusColor = null;
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
