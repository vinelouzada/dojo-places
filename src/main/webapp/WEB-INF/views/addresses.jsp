<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Endereços</title>
</head>
<body>
    <h1>Endereços</h1>
    <table>
        <thead>
        <tr>
            <th>Street</th>
            <th>Código</th>
            <th>Bairro</th>
            <th>Cidade</th>
            <th>Data de Criação</th>
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
                    <td>
                        <form action="/addresses/${address.id()}/edit">
                            <button>Editar</button>
                        </form>
                    </td>
                    <td>
                        <form action="/addresses/delete/" method="post">
                            <input type="hidden" value="${address.id()}">
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
