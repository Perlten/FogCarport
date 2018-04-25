/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.entities;

/**
 * Holds the contact info on the customer.
 * @author adamlass
 */
public class Customer {
    private String firstname;
    private String lastname;
    private String email;
    private int phonenumber;

    public Customer(String firstname, String lastname, String email, int phonenumber) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getPhonenumber() {
        return phonenumber;
    }

    @Override
    public String toString() {
        return "Customer{" + "firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + ", phonenumber=" + phonenumber + '}';
    }
    

}
