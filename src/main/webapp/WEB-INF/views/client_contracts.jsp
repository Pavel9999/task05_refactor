<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <style>
        <%@include file="css/cars.css"%>

    </style>

    <fmt:setLocale value="${sessionScope.local}" />
    <fmt:setBundle basename="local" var="loc" />

    <fmt:message bundle="${loc}" key="local.greeting"
                 var="greeting_text" />

    <fmt:message bundle="${loc}" key="local.all_cars"
                 var="all_cars_text" />
    <fmt:message bundle="${loc}" key="local.free_cars"
                 var="free_cars_text" />
    <fmt:message bundle="${loc}" key="local.rented_cars"
                 var="rented_cars_text" />

    <fmt:message bundle="${loc}" key="local.brand"
                 var="brand_text" />
    <fmt:message bundle="${loc}" key="local.model"
                 var="model_text" />
    <fmt:message bundle="${loc}" key="local.year"
                 var="year_text" />
    <fmt:message bundle="${loc}" key="local.mkp"
                 var="mkp_text" />
    <fmt:message bundle="${loc}" key="local.color"
                 var="color_text" />
    <fmt:message bundle="${loc}" key="local.horsepower"
                 var="horsepower_text" />
    <fmt:message bundle="${loc}" key="local.engine_size"
                 var="engine_size_text" />
    <fmt:message bundle="${loc}" key="local.miliage"
                 var="miliage_text" />
    <fmt:message bundle="${loc}" key="local.price"
                 var="price_text" />
    <fmt:message bundle="${loc}" key="local.rental"
                 var="rental_text" />
    <fmt:message bundle="${loc}" key="local.rent_avaible"
                 var="rent_avaible_text" />
    <fmt:message bundle="${loc}" key="local.already_in_rent"
                 var="already_in_rent_text" />
    <fmt:message bundle="${loc}" key="local.rent_car"
                 var="rent_car_text" />

    <fmt:message bundle="${loc}" key="local.cancel"
                 var="cancel_text" />
    <fmt:message bundle="${loc}" key="local.contract_unconfirmed"
                 var="contract_unconfirmed_text" />
    <fmt:message bundle="${loc}" key="local.contract_confirmed"
                 var="contract_confirmed_text" />
    <fmt:message bundle="${loc}" key="local.contract_canceled"
                 var="contract_canceled_text" />
    <fmt:message bundle="${loc}" key="local.contract_finished"
                 var="contract_finished_text" />

    <fmt:message bundle="${loc}" key="local.logout"
                 var="logout_text" />

    <fmt:message bundle="${loc}" key="local.car"
                 var="car_text" />
    <fmt:message bundle="${loc}" key="local.contract_state"
                 var="state_text" />
    <fmt:message bundle="${loc}" key="local.all_price"
                 var="price_text" />
    <fmt:message bundle="${loc}" key="local.client"
                 var="client_text" />
    <fmt:message bundle="${loc}" key="local.manager"
                 var="manager_text" />
    <fmt:message bundle="${loc}" key="local.date_start"
                 var="date_start_text" />
    <fmt:message bundle="${loc}" key="local.date_finish"
                 var="date_finish_text" />


</head>
<body>

<p>${message_text}</p>

<p>${greeting_text}, ${full_name}<p>

<form action="controller" method="POST">
    <input type="hidden" name="command" value="logout">

    <input type="submit" value="${logout_text}" />
</form>


<form action="/controller" method="POST">
    <input type="hidden" name="command" value="all_cars">
    <input type="submit" value="${all_cars_text}" />
</form>

<form action="/controller" method="POST">
    <input type="hidden" name="command" value="free_cars">
    <input type="submit" value="${free_cars_text}" />
</form>

<form action="/controller" method="POST">
    <input type="hidden" name="command" value="client_contracts">
    <input type="submit" value="${rented_cars_text}" />
</form>



<table id="cars">
    <tr id="header">
        <td><p>${car_text}</p></td>
        <td><p>${state_text}</p></td>
        <td><p>${price_text}</p></td>
        <td><p>${manager_text}</p></td>
        <td><p>${action_text}</p></td>

    </tr>
    <c:forEach varStatus="i" var="contract" items="${contracts}" >
        <tr class="car">
            <td><p>${contract.car}</p></td>
            <td><p>${contract.state}</p></td>
            <td><p>${contract.all_price}</p></td>
            <td><p>${contract.manager}</p></td>


            <td>
                <c:if test = "${contract.state == 'unconfirmed'}">
                    <p>${contract_unconfirmed_text}</p>

                    <form action="/controller" method="POST">
                        <input type="hidden" name="command" value="cancel_rent">
                        <input type="hidden"  name="contract_id" value="${contract.id}" />

                        <input type="submit" value="${cancel_text}" />
                    </form>
                </c:if>
                <c:if test = "${contract.state == 'confirmed'}">
                    <p>${contract_confirmed_text}</p>
                </c:if>
                <c:if test = "${contract.state == 'canceled'}">
                    <p>${contract_canceled_text}</p>
                </c:if>
                <c:if test = "${contract.state == 'finished'}">
                    <p>${contract_finished_text}</p>
                </c:if>

            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>