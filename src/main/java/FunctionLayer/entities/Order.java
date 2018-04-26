/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.entities;

import java.util.Calendar;

/**
 * Order is both a customer request and an order.
 * As long as it is not confirmed it is an order.
 * A order holds the customer information and the specification of the carport.
 * @author adamlass
 */
public class Order {

    private int orderid;
    private boolean confirmed;
    private Calendar date;
    private Customer customer;
    private Customization customization;

    public Order(int orderid, boolean confirmed, Calendar date, Customer customer, Customization customization) {
        this.orderid = orderid;
        this.confirmed = confirmed;
        this.date = date;
        this.customer = customer;
        this.customization = customization;
    }

    public int getOrderid() {
        return orderid;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public Calendar getDate() {
        return date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Customization getCustomization() {
        return customization;
    }

    @Override
    public String toString() {
        return "Order{" + "confirmed=" + confirmed + ", date=" + date.getTime() + ", customer=" + customer + ", customization=" + customization + '}';
    }
    

}
