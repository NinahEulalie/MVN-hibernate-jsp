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
			<h2><%= request.getParameter("codeaffecter") == null ? "Ajouter" : "Modifier" %> une Affectation</h2>

		    <form action="AffecterServlet" method="post">
		        <input type="hidden" name="action" value="<%= request.getParameter("codeaffecter") == null ? "addAffectation" : "updateAffectation" %>">
		        
		        <% 
		            AffecterModel affectation = (AffecterModel) request.getAttribute("affectation");
		            Long codeAffecter = affectation != null ? affectation.getCodeaffecter() : null;
		            Long selectedEmploye = affectation != null ? affectation.getEmploye().getCodeemp() : null;
		            Long selectedLieu = affectation != null ? affectation.getLieu().getCodelieu() : null;
		        %>
		        
		        <% if (codeAffecter != null) { %>
		            <input type="hidden" name="codeaffecter" value="<%= codeAffecter %>">
		        <% } %>           
		         
	            <fieldset class="form-group">
	                <label>Employé :</label>
			        <select name="codeemp" required>
			            <%
			                List<EmployeModel> employes = (List<EmployeModel>) request.getAttribute("listEmployes");
			                for (EmployeModel emp : employes) {
			            %>
			                <option value="<%= emp.getCodeemp() %>" <%= (selectedEmploye != null && selectedEmploye.equals(emp.getCodeemp())) ? "selected" : "" %>>
			                    <%= emp.getNom() %> <%= emp.getPrenom() %> - <%= emp.getPoste() %>
			                </option>
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
			                for (LieuModel lieu : lieux) {
			            %>
			                <option value="<%= lieu.getCodelieu() %>" <%= (selectedLieu != null && selectedLieu.equals(lieu.getCodelieu())) ? "selected" : "" %>>
			                    <%= lieu.getDesignation() %> - <%= lieu.getProvince() %>
			                </option>
			            <%
			                }
			            %>
			        </select>
	            </fieldset>

	            <fieldset class="form-group">
	                <label for="date">Date d'affectation :</label>
					<input type="date" id="date_affectation" name="date" required>
	            </fieldset>

            	<button type="submit" class="btn btn-success">Enregistrer</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>