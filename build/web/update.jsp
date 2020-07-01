<%-- 
    Document   : update
    Created on : Jul 1, 2020, 11:21:50 AM
    Author     : mayantha_f
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Update</title>
    </head>
    <body>
        <form action="update">
            <h1>Edit User</h1><br>
            <fieldset>
                <label>User ID</label> <input type="text"
                    value="<c:out value='${user.id}'/>" name="id" readonly>
            </fieldset>

            <fieldset>
                <label>Username</label> <input type="text"
                    value="<c:out value='${user.username}' />" name="username">
            </fieldset>

            <fieldset>
                <label>Password</label> <input type="password"
                    value="<c:out value='${user.password}' />" name="password" readonly>
            </fieldset>

            <fieldset>
                <label>User Department</label> <input type="text"
                    value="<c:out value='${user.department}' />" name="department">
            </fieldset>

            <input type="submit" value="Update">
        </form>
    </body>
</html>