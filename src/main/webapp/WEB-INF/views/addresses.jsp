<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <link rel="stylesheet" href="/assets/css/flash-messages.css">
<head>
    <title>Endereços</title>
</head>
<body>
    <h1>Endereços</h1>
    <c:if test="${not empty flashMessage}">
        <div class="flash-message ${typeMessage}">
            ${flashMessage}
        </div>
    </c:if>
    <table>
        <thead>
        <tr>
            <th>Street</th>
            <th>Código</th>
            <th>Bairro</th>
            <th>Cidade</th>
            <th>Data de Criação</th>
            <th>Data de Atualização</th>
            <th>Editar</th>
            <th>Excluir</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach var="address" items="${addresses}">
                <tr>
                    <td>${address.street()}</td>
                    <td>${address.code()}</td>
                    <td>${address.neighborhood()}</td>
                    <td>${address.city()}</td>
                    <td>${address.createdAt()}</td>
                    <td>${address.updatedAt()}</td>
                    <td>
                        <a href="/address/${address.id()}/edit"><button>Editar</button></a>
                    </td>
                    <td>
                        <form action="/address/delete" method="post">
                            <input name="id" type="hidden" value="${address.id()}">
                            <button>Excluir</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <form action="/register">
        <button>Criar Endereço</button>
    </form>
</body>
</html>
