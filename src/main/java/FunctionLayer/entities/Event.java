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

    private int eventId, orderid, assignedEmployee, accessLevel, eventType;
    private String title, description, eventName;
    private Calendar date;

    public Event(int eventId, int orderid, int assignedEmployee, int accessLevel, int eventType, String title, String description, String eventName, Calendar date) {
        this.eventId = eventId;
        this.orderid = orderid;
        this.assignedEmployee = assignedEmployee;
        this.accessLevel = accessLevel;
        this.eventType = eventType;
        this.title = title;
        this.description = description;
        this.eventName = eventName;
        this.date = date;
    }

    public int getEventId() {
        return eventId;
    }

    public int getOrderid() {
        return orderid;
    }

    public int getAssignedEmployee() {
        return assignedEmployee;
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

    @Override
    public String toString() {
        return "Event{" + "eventId=" + eventId + ", orderid=" + orderid + ", assignedEmployee=" + assignedEmployee + ", accessLevel=" + accessLevel + ", eventType=" + eventType + ", title=" + title + ", description=" + description + ", eventName=" + eventName + ", date=" + date.getTimeInMillis() + '}';
    }

}
