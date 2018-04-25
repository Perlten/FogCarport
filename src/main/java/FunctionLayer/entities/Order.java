/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.entities;

import java.util.Calendar;

/**
 *
 * @author adamlass
 */
public class Order {

    private boolean confirmed;
    private Calendar date;
    private Customer customer;
    private Customization customization;

    public Order(boolean confirmed, Calendar date, Customer customer, Customization customization) {
        this.confirmed = confirmed;
        this.date = date;
        this.customer = customer;
        this.customization = customization;
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

}