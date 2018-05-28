/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.entities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

/**
 * Order is both a customer request and an order. As long as it is not confirmed
 * it is an order. A order holds the customer information and the specification
 * of the carport.
 *
 * @author adamlass
 */
public class Order {

    private int orderid;
    private boolean confirmed;
    private boolean ordered;
    private Calendar date;
    private Customer customer;
    private Customization customization;
    private double price;

    public Order(int orderid, boolean ordered, boolean confirmed, Calendar date, Customer customer, Customization customization, double price) {
        this.orderid = orderid;
        this.confirmed = confirmed;
        this.date = date;
        this.customer = customer;
        this.customization = customization;
        this.ordered = ordered;
        this.price = price;
    }

    public Order(Customer customer, Customization customization) {
        this(-1, false, false, null, customer, customization, 0);
    }

    public int percentage() {
        int res = 0;
        if (ordered) {
            res = 100;
        } else {
            if (customization != null) {
                res += 30;
            }
            if (customization.getTile() != null || customization.getCladding()
                    != null) {
                res += 30;
            }
            if (customer != null) {
                res += 30;
            }
        }

        return res;
    }

    public double getPrice() {
        return price;
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

    public boolean isOrdered() {
        return ordered;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public void setOrdered(boolean ordered) {
        this.ordered = ordered;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String simpleDate() {
        SimpleDateFormat sp = new SimpleDateFormat("dd/MM/YYYY HH:mm");
        return sp.format(date.getTime());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.orderid;
        hash = 47 * hash + (this.confirmed ? 1 : 0);
        hash = 47 * hash + Objects.hashCode(this.date);
        hash = 47 * hash + Objects.hashCode(this.customer);
        hash = 47 * hash + Objects.hashCode(this.customization);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (this.orderid != other.orderid) {
            return false;
        }
        if (this.confirmed != other.confirmed) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.customer, other.customer)) {
            return false;
        }
        if (!Objects.equals(this.customization, other.customization)) {
            return false;
        }
        return true;
    }

}
