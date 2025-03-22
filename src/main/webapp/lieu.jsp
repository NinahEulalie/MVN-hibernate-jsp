<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List, java.util.ArrayList, model.LieuModel" %>

<%
    Object obj = request.getAttribute("lieux");
    List<LieuModel> lieux = new ArrayList<>();

    // Vérifier si obj est bien une liste
    if (obj instanceof List<?>) {
    	lieux = (List<LieuModel>) obj;
    }
%>
<html>
<head>
	<title>Liste des lieux</title>
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
		        color: black; /* Change la couleur des liens en blanc */
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
            <div href="https://www.javaguides.net" class="navbar-brand">GESTION D'AFFECTATIONS D'EMPLOYES</div>
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
			<h3 class="text-center">Liste des lieux</h3>
			<hr>
			<div class="container text-left">
				<a href="<%=request.getContextPath()%>/newFormLieu" class="btn btn-success">Ajouter un lieu</a>
			<br><br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>DESIGNATION</th>
						<th>PROVINCE</th>
					</tr>
				</thead>
				
				<tbody>
				<% for (LieuModel lieu : lieux) { %>
					<tr>	
			            <td><%= lieu.getCodelieu() %></td>
			            <td><%= lieu.getDesignation() %></td>
			            <td><%= lieu.getProvince() %></td>
            			<td>
			                <a href="editFormLieu?codelieu=<%= lieu.getCodelieu() %>">
			                	<img src="<%=request.getContextPath()%>/assets/editing.png" alt="Edit" style="width: 25px; height: 25px;">
			                </a> &nbsp;&nbsp;&nbsp;&nbsp;
							<a href="deleteLieu?codelieu=<%= lieu.getCodelieu() %>" 
							onclick="return confirm('Supprimer ce lieu ?')">
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