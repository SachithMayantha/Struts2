<%-- 
    Document   : form
    Created on : Jul 1, 2020, 10:00:52 AM
    Author     : mayantha_f
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Form</title>
    </head>
    <body>
        <h1>Add User</h1>
        <s:form action="insert">
            <s:textfield name="id" label="Id"></s:textfield>
            <s:textfield name="username" label="Username"></s:textfield>
            <s:password name="password" label="Password" type="password"></s:password>
            <s:textfield name="department" label="Department"></s:textfield>
            <s:submit value="Add"></s:submit>
        </s:form>
    </body>
</html>
