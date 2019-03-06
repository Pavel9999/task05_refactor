<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>


    <fmt:setLocale value="${sessionScope.local}" />
    <fmt:setBundle basename="local" var="loc" />

    <fmt:message bundle="${loc}" key="local.guest_login"
                 var="guest_login_text" />
    <fmt:message bundle="${loc}" key="local.login"
                 var="login_text" />
    <fmt:message bundle="${loc}" key="local.singup"
                 var="singup_text" />


</head>
<body>

<p>${message_text}</p>

<form action="controller" method="POST">
    <input type="hidden" name="command" value="enterAsGuest">
    <input type="hidden"  name="acc_type" value="guest" />
    <input type="submit" value="${guest_login_text}" />
</form>

<form action="controller" method="POST">
    <input type="hidden" name="command" value="goToAuthorizationPage">

    <input type="submit" value="${login_text}" />
</form>

<form action="controller" method="POST">
    <input type="hidden" name="command" value="goToRegistrationPage">

    <input type="submit" value="${singup_text}" />

</form>




</body>


</html>