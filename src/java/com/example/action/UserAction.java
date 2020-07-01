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
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author mayantha_f
 */
public class UserAction extends ActionSupport {

    UserDAO userDAO = new UserDAO();

    @Override
    public String execute() {
        HttpSession session=ServletActionContext.getRequest().getSession(false);
        return SUCCESS;
    }

    public String listUser() throws SQLException {
        System.out.println("ListAction listUser() called");
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session=request.getSession(false);
        
        List<User> listUser = userDAO.selectAllUsers();
        request.setAttribute("listUser", listUser);
        if(session!=null && session.getAttribute("user")!=null) {
        return "listuser";
        }else
            return "hello";
    }

    public String newForm() {
        HttpSession session=ServletActionContext.getRequest().getSession(false);
        if(session!=null && session.getAttribute("user")!=null) {
        return "newform";
        }else
        return "hello";
        
    }

    public String deleteUser() throws SQLException {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session=request.getSession(false);
        //get the user id using request object
        int id = Integer.parseInt(request.getParameter("id"));
        //delete the relative user from the database
        userDAO.deleteUser(id);
        if(session!=null && session.getAttribute("user")!=null) {
        return "deleteuser";
        }else
            return "hello";
    }

    public String insertUser() throws SQLException {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpSession session=request.getSession(false);
        //retrieve user details using request object
        System.out.println("insertUser method in UserAction");
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String department = request.getParameter("department");
        User newUser = new User(id, username, password, department);
        //insert the retrieve data to database
        userDAO.insertUser(newUser);
        if(session!=null && session.getAttribute("user")!=null) {
        return "insertuser";
        }else
            return "hello";
    }

    public String showEditForm() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpSession session=request.getSession(false);
        //get the id using request object
        int id = Integer.parseInt(request.getParameter("id"));
        //get user object using selectUser method
        User existingUser = userDAO.selectUser(id);
        request.setAttribute("user", existingUser);
        if(session!=null && session.getAttribute("user")!=null) {
        return "editform";
        }else
            return "hello";
    }

    public String updateUser() throws SQLException {
        System.out.println("updateUser() called");
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpSession session=request.getSession(false);
        //retrieve user details using request object
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String department = request.getParameter("department");
        boolean valid = Boolean.parseBoolean(request.getParameter("valid"));

        User user = new User(id, username, password, department, valid);
        userDAO.updateUser(user);
        if(session!=null && session.getAttribute("user")!=null) {
        return "updateuser";
        }else
            return "hello";
    }
    
    public String logoutUser(){
        HttpSession session=ServletActionContext.getRequest().getSession(false);
        session.invalidate();
    return "logoutuser";
    }
}
