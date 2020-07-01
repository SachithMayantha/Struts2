/*
 * $Id: LoginAction.template,v 1.2 2008-03-27 05:47:21 ub3rsold4t Exp $
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.example.action;

import com.example.User;
import com.example.UserDAO;
import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpSession;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
/**
 * <code>Set welcome message.</code>
 */
public class LoginAction extends ActionSupport {

    public LoginAction() {
    }
    
    @Override
    public String execute(){
        return SUCCESS;
    }
   
    public String loginUser() {
   
        try{
        User user = new User();
            
        HttpServletRequest request = ServletActionContext.getRequest();
  
        System.out.println("loginUser() method called in UserAction");
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));

        user = UserDAO.login(user);

        if (user.isValid()) {
            System.out.println("In UserAction class if else working");
            //create a session
            HttpSession session = request.getSession(true);
            //assign the object to currentSessionUser
            session.setAttribute("user", user);
            return "loginsuccess";
        } else {
            return "loginerror"; 
        }
        }catch(Exception e){
            return "";
        }
    }
}
