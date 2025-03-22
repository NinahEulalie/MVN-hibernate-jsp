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
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color:117554">
        <a class="navbar-brand" href="#">Employé</a>
    </nav>
</header>

<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:choose>
                <c:when test="${employe != null}">
                    <form action="<c:url value='/updateEmploye' />" method="post">
                        <h2>Modifier un employé</h2>
                        <input type="hidden" name="codeemp" value="<c:out value='${employe.codeemp}' />" />
                </c:when>
                <c:otherwise>
                    <form action="<c:url value='/addEmploye' />" method="post">
                        <h2>Ajouter un employé</h2>
                </c:otherwise>
            </c:choose>
            
            <fieldset class="form-group">
                <label>Nom de l'employé</label> 
                <input type="text" value="<c:out value='${employe.nom}' />" class="form-control" name="nom" required>
            </fieldset>

            <fieldset class="form-group">
                <label>Prénom de l'employé</label> 
                <input type="text" value="<c:out value='${employe.prenom}' />" class="form-control" name="prenom" required>
            </fieldset>

            <fieldset class="form-group">
                <label>Poste de l'employé</label> 
                <input type="text" value="<c:out value='${employe.poste}' />" class="form-control" name="poste" required>
            </fieldset>

            <button type="submit" class="btn btn-success">Enregistrer</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>