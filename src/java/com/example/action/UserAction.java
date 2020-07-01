/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.action;

import com.example.UserDAO;
import com.example.User;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author mayantha_f
 */
public class UserAction extends ActionSupport {

    UserDAO userDAO = new UserDAO();

    @Override
    public String execute() {
        return SUCCESS;
    }

    public String listUser() throws SQLException {
        System.out.println("ListAction listUser() called");
        HttpServletRequest request = ServletActionContext.getRequest();

        List<User> listUser = userDAO.selectAllUsers();
        request.setAttribute("listUser", listUser);
        return "listuser";
    }

    public String newForm() {
        return "newform";
    }

    public String deleteUser() throws SQLException {
        HttpServletRequest request = ServletActionContext.getRequest();
        //get the user id using request object
        int id = Integer.parseInt(request.getParameter("id"));
        //delete the relative user from the database
        userDAO.deleteUser(id);
        return "deleteuser";
    }

    public String insertUser() throws SQLException {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        //retrieve user details using request object
        System.out.println("insertUser method in UserAction");
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String department = request.getParameter("department");
        User newUser = new User(id, username, password, department);
        //insert the retrieve data to database
        userDAO.insertUser(newUser);
        return "adduser";
    }

    public String showEditForm() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        //get the id using request object
        int id = Integer.parseInt(request.getParameter("id"));
        //get user object using selectUser method
        User existingUser = userDAO.selectUser(id);
        request.setAttribute("user", existingUser);
        return "editform";
    }

    public String updateUser() throws SQLException {
        System.out.println("updateUser() called");
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        //retrieve user details using request object
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String department = request.getParameter("department");
        boolean valid = Boolean.parseBoolean(request.getParameter("valid"));

        User user = new User(id, username, password, department, valid);
        userDAO.updateUser(user);
        return "updateuser";
    }
}
