<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<link rel="stylesheet" href="/assets/css/flash-messages.css">

<head>
    <title>Editar Endereço</title>
</head>

<c:if test="${not empty flashMessage}">
    <div class="flash-message ${typeMessage}">
            ${flashMessage}
    </div>
</c:if>

<body>
<form:form modelAttribute="updateForm" action="/address/edit" method="post">
    <div>
        <form:input path="id" value="${address.id()}" type="hidden"/>
    </div>
    <div>
        <label for="street">Rua:</label>
        <form:input path="street" value="${address.street()}"/>
        <form:errors path="street" cssClass="error"/>
    </div>
    <div>
        <label for="code">Código:</label>
        <form:input path="code" pattern="\\d{8}" value="${address.code()}"/>
        <form:errors path="code" cssClass="error" />
    </div>
    <div>
        <label for="neighborhood">Bairro:</label>
        <form:input path="neighborhood" value="${address.neighborhood()}"/>
        <form:errors path="neighborhood" cssClass="error"/>
    </div>
    <div>
        <label for="city">Cidade:</label>
        <form:input path="city" value="${address.city()}"/>
        <form:errors path="city" cssClass="error"/>
    </div>

    <button type="submit">Enviar</button>
</form:form>
</body>
</html>
