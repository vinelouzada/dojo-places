<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" href="/assets/css/flash-messages.css">

<h1>Bora!</h1>

<br/>
<br/>

<c:if test="${not empty flashMessage}">
    <div class="flash-message ${typeMessage}">
            ${flashMessage}
    </div>
</c:if>

<form:form modelAttribute="form" action="/register" method="post">
    <div>
        <label for="street">Rua:</label>
        <form:input path="street" />
        <form:errors path="street" cssClass="error"/>
    </div>
    <div>
        <label for="code">Código:</label>
        <form:input path="code" pattern="\\d{8}" title="O Cep deve conter exatamente 8 dígitos"/>
        <form:errors path="code" cssClass="error" />
    </div>
    <div>
        <label for="neighborhood">Bairro:</label>
        <form:input path="neighborhood"/>
        <form:errors path="neighborhood" cssClass="error"/>
    </div>
    <div>
        <label for="city">Cidade:</label>
        <form:input path="city"/>
        <form:errors path="city" cssClass="error"/>
    </div>

    <button type="submit">Enviar</button>
</form:form>

<form action="/addresses">
    <button>Voltar</button>
</form>

<script src="/assets/js/cep.js"></script>