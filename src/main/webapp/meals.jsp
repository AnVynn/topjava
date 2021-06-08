<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="ru.javawebinar.topjava.util.TimeUtil" %>

<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h1>Meals</h1>
    <table border="1">
        <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <jsp:useBean id="meals" scope="request" type="java.util.List"/>
        <c:forEach items="${meals}" var="meal">
            <tr style="color: ${meal.excess ? 'red' : 'green'}">
            <td><c:out value="${TimeUtil.getDateTime(meal.dateTime)}"/></td>
            <td><c:out value="${meal.description}"/></td>
            <td><c:out value="${meal.calories}"/></td>
            <td>Edit</td>
            <td>Delete</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>
