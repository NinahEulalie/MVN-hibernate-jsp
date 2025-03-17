<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Formulaire</title>
    <link rel="stylesheet"
        href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
        crossorigin="anonymous">
    <style>
        .container {
            margin-top: 50px;
        }
    </style>
</head>
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark bg-dark">
        <a class="navbar-brand" href="#">Lieu</a>
    </nav>
</header>

<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:choose>
                <c:when test="${lieu != null}">
                    <form action="<c:url value='/updateLieu' />" method="post">
                        <h2>Modifier un lieu</h2>
                        <input type="hidden" name="codeemp" value="<c:out value='${lieu.codelieu}' />" />
                </c:when>
                <c:otherwise>
                    <form action="<c:url value='/addLieu' />" method="post">
                        <h2>Ajouter un lieu</h2>
                </c:otherwise>
            </c:choose>
            
            <fieldset class="form-group">
                <label>Désignation</label> 
                <input type="text" value="<c:out value='${lieu.designation}' />" class="form-control" name="designation" required>
            </fieldset>

            <fieldset class="form-group">
                <label>Province</label> 
                <input type="text" value="<c:out value='${lieu.province}' />" class="form-control" name="province" required>
            </fieldset>

            <button type="submit" class="btn btn-success">Enregistrer</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>