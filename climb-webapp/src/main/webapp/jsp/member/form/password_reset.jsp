<%@ taglib prefix="s" uri="/struts-tags" %>

<html>

<%@include file="../../_include/head.jsp" %>

<body>


<h2>Password Reset</h2>

<s:form action="pwd_reset" method="POST">
    <s:textfield type="text" name="login" label="Login" requiredLabel="true"/>
    <s:password name="password" label="Password" requiredLabel="true" />
    <s:password name="password_check" label="Password" requiredLabel="true" />
    <s:submit value="Reset"/>
</s:form>
</body>
</html>