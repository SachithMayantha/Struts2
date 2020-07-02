<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <title>Login</title>
    </head>

    <body>
        <br>
        <s:form action="login">
            <s:textfield name="username" label="UserName"></s:textfield>
            <s:password name="password" label="Password" type="password"></s:password>
            <s:submit value="Login"></s:submit>
        </s:form>

        <h2><s:property value="message"/></h2>
    </body>
</html>

