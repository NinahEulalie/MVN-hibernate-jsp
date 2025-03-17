package servlet;

import dao.EmployeDAO;
import model.EmployeModel;

//import jakarta.servlet.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import javax.servlet.RequestDispatcher;

@SuppressWarnings("unused")
@WebServlet("/EmployeServlet")
public class EmployeServlet extends HttpServlet {
	
    private static final long serialVersionUID = 1L;
    private EmployeDAO employeDAO = new EmployeDAO();
    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String action = request.getServletPath();
        System.out.println("Action: " + action);
        
        try {
            switch (action) {
	        	case "/newFormEmploye":
	        		showNewForm(request, response);
	            break;
                case "/addEmploye":
                    addEmploye(request, response);
                    break;
                case "/updateEmploye":
                    updateEmploye(request, response);
                    break;
                case "/deleteEmploye":
                    deleteEmploye(request, response);
                    break;
                case "/editFormEmploye":
                    showEditForm(request, response);
                    break;
                default:
                	listAllEmployes(request, response);
                    break;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            throw new ServletException(ex);
        }
    }

    
    // 🔹 Afficher la liste des employés
    private void listAllEmployes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<EmployeModel> employes = employeDAO.getAllEmployes();

        // Vérification en console
        if (employes == null) {
            System.out.println("⚠ ERREUR: La liste des employés est NULL !");
            employes = new ArrayList<>(); // Évite NullPointerException
        } 
        else {
            System.out.println("✅ Nombre d'employés récupérés : " + employes.size());
            for (EmployeModel emp : employes) {
                System.out.println(" - " + emp.getCodeemp() + " | " + emp.getNom() + " | " + emp.getPrenom() + " | " + emp.getPoste());
            }
        }

        request.setAttribute("employes", employes);
        request.getRequestDispatcher("employe.jsp").forward(request, response);
    }
    
    // 🔹 Ajouter un employé
    private void addEmploye(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String poste = request.getParameter("poste");

        EmployeModel employe = new EmployeModel(nom, prenom, poste);
        employeDAO.saveEmploye(employe);

        response.sendRedirect("listEmployes");
    }
    
    // 🔹 Afficher le formulaire d'ajout
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("employe-form.jsp");
        dispatcher.forward(request, response);
    }
    
    // 🔹 Afficher le formulaire de modification
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long codeemp = Long.parseLong(request.getParameter("codeemp"));
        EmployeModel employe = employeDAO.getEmployeById(codeemp);

        request.setAttribute("employe", employe);
        request.getRequestDispatcher("employe-form.jsp").forward(request, response);
    }

    // 🔹 Modifier un employé
    private void updateEmploye(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long codeemp = Long.parseLong(request.getParameter("codeemp"));
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String poste = request.getParameter("poste");

        EmployeModel employe = employeDAO.getEmployeById(codeemp);
        employe.setNom(nom);
        employe.setPrenom(prenom);
        employe.setPoste(poste);

        employeDAO.updateEmploye(employe);
        response.sendRedirect("listEmployes");
    }

    // 🔹 Supprimer un employé
    private void deleteEmploye(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long codeemp = Long.parseLong(request.getParameter("codeemp"));
        employeDAO.deleteEmploye(codeemp);
        response.sendRedirect("listEmployes");
    }
}