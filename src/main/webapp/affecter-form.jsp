<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List, java.util.ArrayList, java.util.Date, model.AffecterModel, 
model.EmployeModel, model.LieuModel, dao.EmployeDAO, dao.LieuDAO" %>

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
        <a class="navbar-brand" href="#">Affecter</a>
    </nav>
</header>

<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
			<c:choose>
                <c:when test="${not empty affecter}">
                    <form action="<c:url value='/updateAffectation' />" method="post">
                        <h2>Modifier une affectation</h2>
                        <input type="hidden" name="idpret" value="<c:out value='${affectation.codeaffecter}' />" />
                </c:when>
                <c:otherwise>
                    <form action="<c:url value='/addAffectation' />" method="post">
                        <h2>Ajouter une affectation</h2>
                </c:otherwise>
            </c:choose>        
		         
	            <fieldset class="form-group">
	                <label>Employé :</label>
			        <select name="codeemp" required>
			            <%
			                List<EmployeModel> employes = (List<EmployeModel>) request.getAttribute("listEmployes");
				            if (employes != null) {
				                for (EmployeModel emp : employes) {
				        %>
				                    <option value="<%= emp.getCodeemp() %>">
				                        <%= emp.getNom() %> <%= emp.getPrenom() %> - <%= emp.getPoste() %>
				                    </option>
				        <%
				                }
				            } else {
				        %>
				                <option disabled>Aucun employé trouvé</option>
				        <%
				            }
			            %>
			        </select>
	            </fieldset>

	            <fieldset class="form-group">
	                <label>Lieu :</label>
			        <select name="codelieu" required>
			            <%
			                List<LieuModel> lieux = (List<LieuModel>) request.getAttribute("listLieux");
				            if (lieux != null) {
				                for (LieuModel lieu : lieux) {
				        %>
				                    <option value="<%= lieu.getCodelieu() %>">
				                        <%= lieu.getDesignation() %> <%= lieu.getProvince() %>
				                    </option>
				        <%
				                }
				            } else {
				        %>
				                <option disabled>Aucun lieu trouvé</option>
				        <%
				            }
			            %>
			        </select>
	            </fieldset>

	            <fieldset class="form-group">
	                <label for="date">Date d'affectation :</label>
					<input type="date" id="date" name="date" required>
	            </fieldset>

            	<button type="submit" class="btn btn-success">Enregistrer</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>