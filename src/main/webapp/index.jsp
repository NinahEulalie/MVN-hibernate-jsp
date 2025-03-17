<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
    <title>Projet Hibernate</title>
    <link rel="stylesheet"
        href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
        crossorigin="anonymous">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
        <div>
            <a href="https://www.javaguides.net" class="navbar-brand">AFFECTATION D'EMPLOYES</a>
        </div>
    </nav>
</header>
<br>
<div class="container text-center">
    <h3>Gestion d'affectation d'employés</h3>
    <hr>
    <div class="btn-group">
		<a href="<%=request.getContextPath()%>/listEmployes" class="btn btn-primary">EMPLOYE</a>
        <a href="<%=request.getContextPath()%>/listLieux" class="btn btn-success">LIEU</a>
		<a href="<%=request.getContextPath()%>/listAffectations" class="btn btn-warning">AFFECTER</a>

    </div>
</div>
</body>
</html>