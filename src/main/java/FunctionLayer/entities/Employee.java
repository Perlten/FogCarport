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
public class Employee {

    private int employeeId, authenticationLevel;
    private String username, firstname, lastname, email;
    private boolean employed, resetPassword;
    private Calendar dateCreated;

    public Employee(int employeeId, int authenticationLevel, String username, String firstname, String lastname, String email, boolean employed, Calendar dateCreated, boolean resetPassword) {
        this.employeeId = employeeId;
        this.authenticationLevel = authenticationLevel;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.employed = employed;
        this.dateCreated = dateCreated;
        this.resetPassword = resetPassword;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public int getAuthenticationLevel() {
        return authenticationLevel;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public boolean isEmployed() {
        return employed;
    }

    public Calendar getDateCreated() {
        return dateCreated;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setAuthenticationLevel(int authenticationLevel) {
        this.authenticationLevel = authenticationLevel;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isResetPassword() {
        return resetPassword;
    }

    public String simpleDate() {
        SimpleDateFormat sp = new SimpleDateFormat("dd/MM/YYYY HH:mm");
        return sp.format(dateCreated.getTime());
    }
    
    public String getShortEmail(){
        if(email.length() >= 10){
            return email.substring(0,10) + "...";
        }
        return email;
    }

}
