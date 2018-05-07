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
public class Employee {
    private int employeeId, authenticationLevel;
    private String username, firstname, lastname, email;
    private boolean employed;
    private Calendar dateCreated;

    public Employee(int employeeId, int authenticationLevel, String username, String firstname, String lastname, String email, boolean employed, Calendar dateCreated) {
        this.employeeId = employeeId;
        this.authenticationLevel = authenticationLevel;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.employed = employed;
        this.dateCreated = dateCreated;
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
    
}
