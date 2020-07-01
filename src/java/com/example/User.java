/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

/**
 *
 * @author mayantha_f
 */
public class User {
    
    private int id;
    private String username;
    private String password;
    private String department;
    public boolean valid;

    public User() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public User(int id, String username, String password, String department, boolean valid) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.department = department;
        this.valid = valid;
    }

    public User(int id, String username, String password, String department) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.department = department;
    }
    
}
