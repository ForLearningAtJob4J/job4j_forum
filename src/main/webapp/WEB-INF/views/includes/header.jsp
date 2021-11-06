<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<ul class="nav">
    <li class="nav-item">
        <a class="nav-link" href="index">Темы</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="<c:url value="create"/>">Добавить тему</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="<c:url value="logout"/>"><c:out value="${user.name}"/> Выйти</a>
    </li>
</ul>
