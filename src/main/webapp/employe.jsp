<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List, java.util.ArrayList, model.EmployeModel" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
    Object obj = request.getAttribute("employes");
    List<EmployeModel> employes = new ArrayList<>();

    // Vérifier si obj est bien une liste
    if (obj instanceof List<?>) {
        employes = (List<EmployeModel>) obj;
    }
%>
<html>
<head>
	<title>Liste des Employés</title>
	<link rel="stylesheet"
		href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
		integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
		crossorigin="anonymous">
		
		<style>
		    body{
		    background:rgb (237, 237, 240);
		    }
		    .navbar-custom {
		        background-color: 117554;
		        height: 80px; /* Ajustez la hauteur selon vos besoins */
		        display: flex;
		        align-items: center;
		        justify-content: space-between; /* Espacement entre la marque et les liens */
		    }
		    .navbar-custom .navbar-brand {
		        font-size: 1.5rem; /* Ajustez la taille de la police */
		        color: white; /* Change la couleur des liens en noir */
		    }
		    .navbar-custom .navbar-nav {
		        display: flex;
		        justify-content: center;
		        flex: 1; /* Permet à la liste de s'étendre et de centrer les éléments */
		    }
		    .navbar-custom .nav-link {
		        margin: 0 15px; /* Ajouter un espace entre les liens */
		        color: white; /* Change la couleur des liens en blanc */
		        font-size: 1.5rem; /* Ajuster la taille de la police */
		    }
		    .navbar-custom .nav-link:hover {
		        color: black; /* Assurez-vous que les liens restent noirs au survol */
		    }
		    
		    .text-center{
		    	font-size:3rem;
		    	margin-top: 10px;
		    }
		    
		    
		    .navigation{
		       margin-top: 10px;
		        display: flex;
		        flex-direction: row;
		        justify-content: space-between;
		        align-items: center;
		    }
		     .nav-item{
		    	display: flex;
		    	flex-direction: row;
		    }
		</style>
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark navbar-custom">
            <div class="navbar-brand">GESTION D'AFFECTATIONS D'EMPLOYES</div>
          <ul class="navbar-nav">
                <li class="nav-item" id="membre" >
                    <a href="<%=request.getContextPath()%>/listEmployes" class="nav-link">Employés</a>
                </li>
                <li class="nav-item">
                    <a href="<%=request.getContextPath()%>/listLieux" class="nav-link">
                        <i class="fas fa-book"></i> Lieux
                    </a>
                </li>
                <li class="nav-item">
                    <a href="<%=request.getContextPath()%>/listAffectations" class="nav-link">
                        <i class="fas fa-hand-holding"></i> Affectations
                    </a>
                </li>
            </ul>
        </nav>
	</header>
	<br>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">Liste des employés</h3>
			<hr>
			<div class="container text-left">
                <form action="searchMembre" method="get" class="form-inline mb-3">
                    <input type="text" name="keyword" class="form-control mr-2" placeholder="Rechercher un membre">
                    <button type="submit" class="btn btn-primary">Rechercher</button>
                </form>
				<a href="<%=request.getContextPath()%>/newFormEmploye" class="btn btn-success">Ajouter un employé</a>
			<br><br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>NOM</th>
						<th>PRENOM</th>
						<th>POSTE</th>
					</tr>
				</thead>
				
				<tbody>
				<% for (EmployeModel e : employes) { %>
					<tr>
			            <td><%= e.getCodeemp() %></td>
			            <td><%= e.getNom() %></td>
			            <td><%= e.getPrenom() %></td>
			            <td><%= e.getPoste() %></td>
            			<td>
			                <a href="editFormEmploye?codeemp=<%= e.getCodeemp() %>">
			                	<img src="<%=request.getContextPath()%>/assets/editing.png" alt="Edit" style="width: 25px; height: 25px;">
			                </a> &nbsp;&nbsp;&nbsp;&nbsp;
							<a href="deleteEmploye?codeemp=<%= e.getCodeemp() %>" 
							onclick="return confirm('Supprimer cet employé ?')">
								<img src="<%=request.getContextPath()%>/assets/trash.png" alt="Delete" style="width: 25px; height: 25px;">
							</a>
            			</td>		
					</tr>
	      				<% } %>
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>