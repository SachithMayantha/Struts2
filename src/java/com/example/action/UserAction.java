/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.action;

import com.example.UserDAO;
import com.example.User;
import com.example.bean.UserInputBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
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
public class UserAction extends ActionSupport implements ModelDriven<Object> {
    
    UserDAO userDAO = new UserDAO();
    UserInputBean inputBean = new UserInputBean();
    
    @Override
    public String execute() {
        HttpSession session = ServletActionContext.getRequest().getSession(false);
        return SUCCESS;
    }
    
    public String listUser() throws SQLException {
        System.out.println("ListAction listUser() called");
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession(false);
        
        List<User> listUser = userDAO.selectAllUsers();
        request.setAttribute("listUser", listUser);
        if (session != null && session.getAttribute("user") != null) {
            return "listuser";
        } else {
            return "hello";
        }
    }
    
    public String newUser() {
        HttpSession session = ServletActionContext.getRequest().getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            return "newform";
        } else {
            return "hello";
        }
        
    }
    
    public String deleteUser() throws SQLException {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession(false);
        //get the user id using request object
        int id = Integer.parseInt(request.getParameter("id"));
        //delete the relative user from the database
        userDAO.deleteUser(id);
        if (session != null && session.getAttribute("user") != null) {
            return "deleteuser";
        } else {
            return "hello";
        }
    }
    
    public String insertUser() throws SQLException {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession(false);
    
        System.out.println("insertUser method in UserAction");
        userDAO.insertUser(inputBean);
        
        if (session != null && session.getAttribute("user") != null) {
            return "insertuser";
        } else {
            return "hello";
        }
    }
    
    public String editUser() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession(false);
        //get the id using request object
        int id = Integer.parseInt(request.getParameter("id"));
        //get user object using selectUser method
        User existingUser = userDAO.selectUser(id);
        request.setAttribute("user", existingUser);
        if (session != null && session.getAttribute("user") != null) {
            return "editform";
        } else {
            return "hello";
        }
    }
    
    public String updateUser() throws SQLException {
        System.out.println("updateUser() called");
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpSession session = request.getSession(false);
        
        userDAO.updateUser(inputBean);
        if (session != null && session.getAttribute("user") != null) {
            return "updateuser";
        } else {
            return "hello";
        }
    }
    
    public String logoutUser() {
        HttpSession session = ServletActionContext.getRequest().getSession(false);
        session.invalidate();
        return "logoutuser";
    }
    
    @Override
    public Object getModel() {
        return inputBean;
    }
}
