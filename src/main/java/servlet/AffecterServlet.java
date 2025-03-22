package servlet;

import dao.AffecterDAO;
import dao.EmployeDAO;
import dao.LieuDAO;
import model.AffecterModel;
import model.EmployeModel;
import model.LieuModel;
import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@SuppressWarnings("unused")

@WebServlet("/AffecterServlet")
public class AffecterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private AffecterDAO affecterDAO = new AffecterDAO();
	private EmployeDAO employeDAO= new EmployeDAO();
    private LieuDAO lieuDAO= new LieuDAO();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String action = request.getServletPath();
        System.out.println("Action: " + action);

        try {
            switch (action) {
                case "/addAffectation":
                	addAffectation(request, response);
                    break;
                /**case "/newFormAffecter":
                    showNewForm(request, response);
                    break;*/
                case "/editFormAffecter":
                    showEditForm(request, response);
                    break;
                case "/updateAffectation":
                	updateAffectation(request, response);
                    break;
                default:
                	listAllAffectations(request, response);
                    break;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            throw new ServletException(ex);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String action = request.getServletPath();
        System.out.println("Action: " + action);

        try {
            switch (action) {
                case "/deleteAffectation":
                	deleteAffectation(request, response);
                    break;
                case "/newFormAffecter":
                    showNewForm(request, response);
                    break;
                case "/editFormAffecter":
                        showEditForm(request, response);
                        break;
                default:
                	listAllAffectations(request, response);
                    break;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            throw new ServletException(ex);
        }
    }

    // 🔹 Afficher la liste des affectations
    private void listAllAffectations(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<AffecterModel> affectations = affecterDAO.getAllAffectations();

        // Vérification en console
        if (affectations == null) {
            System.out.println("⚠ ERREUR: La liste des affectations est NULL !");
            affectations = new ArrayList<>(); // Évite NullPointerException
        } 
        else {
            System.out.println("✅ Nombre d'affectations récupérées : " + affectations.size());
            for (AffecterModel affectation : affectations) {
                System.out.println(" - " + affectation.getCodeaffecter() + " | " + affectation.getEmploye().getNom() + " | " + " | " + affectation.getEmploye().getPrenom() + " | " 
                		+ " | " + affectation.getEmploye().getPoste() + " | " + affectation.getLieu().getDesignation() + " | " + " | " + affectation.getLieu().getProvince() + " | " + affectation.getDate());
            }
        }

        request.setAttribute("affectations", affectations);
        request.getRequestDispatcher("affecter.jsp").forward(request, response);
    }
        
        
     	// 🔹 Ajouter une affectation
        private void addAffectation(HttpServletRequest request, HttpServletResponse response) throws IOException {
        	Long codeemp = Long.parseLong(request.getParameter("codeemp"));
            Long codelieu = Long.parseLong(request.getParameter("codelieu"));
            String dateString = request.getParameter("date");
            
            // Conversion de la date saisie (format: yyyy-MM-dd)
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
			try {
				date = formatter.parse(dateString);
			} 
			catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            EmployeModel employe = employeDAO.getEmployeById(codeemp);
            LieuModel lieu = lieuDAO.getLieuById(codelieu);

            AffecterModel affectation = new AffecterModel(employe, lieu, date);
            affectation.setEmploye(employe);
            affectation.setLieu(lieu);
            affectation.setDate(date);
            
            affecterDAO.saveAffectation(affectation);
            
            response.sendRedirect("listAffectations");
        }
        
        // 🔹 Afficher le formulaire d'ajout
        private void showNewForm(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
        	List<EmployeModel> employes = employeDAO.getAllEmployes(); //Récupération de la liste des employés
            List<LieuModel> lieux = lieuDAO.getAllLieux(); //Récupération de la liste des lieux
            
            request.setAttribute("listEmployes", employes);
            request.setAttribute("listLieux", lieux);
        	
            RequestDispatcher dispatcher = request.getRequestDispatcher("affecter-form.jsp");
            dispatcher.forward(request, response);
            //return; //Évite toute exécution supplémentaire
        }

        // 🔹 Afficher le formulaire de modification
        private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        	 String codeAffectation = request.getParameter("codeaffecter");
        	 
        	 if ((codeAffectation != null && !codeAffectation.isEmpty())){
        		 try {
        			 Long codeaffecter = Long.parseLong(codeAffectation);
        			 AffecterModel existingAffectation = affecterDAO.getAffectationById(codeaffecter);     
                     List<EmployeModel> employes = employeDAO.getAllEmployes(); //Récupération de la liste des employés
                     List<LieuModel> lieux = lieuDAO.getAllLieux(); //Récupération de la liste des lieux
                     
                     request.setAttribute("affectation", existingAffectation);
                     request.setAttribute("listEmployes", employes);
                     request.setAttribute("listLieux", lieux);
        		 }
        		 catch(NumberFormatException e) {
        	            e.printStackTrace();
        	     }
        	 }
        	 else {
        	     // C'est un ajout, donc pas besoin de récupérer une affectation existante
        	     request.setAttribute("affectation", null);
        	 }
             
             
             request.getRequestDispatcher("affecter-form.jsp").forward(request, response);
             //return; //Évite toute exécution supplémentaire
        }

        // 🔹 Modifier une affectation
        private void updateAffectation(HttpServletRequest request, HttpServletResponse response) throws IOException {
        	Long codeaffecter = Long.parseLong(request.getParameter("codeaffecter"));
            Long codeemp = Long.parseLong(request.getParameter("codeemp"));
            Long codelieu = Long.parseLong(request.getParameter("codelieu"));
            String dateString = request.getParameter("date");
            
            // Conversion de la date saisie (format: yyyy-MM-dd)
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
			try {
				date = formatter.parse(dateString);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            EmployeModel employe = employeDAO.getEmployeById(codeemp);
            LieuModel lieu = lieuDAO.getLieuById(codelieu);

            AffecterModel affectation = new AffecterModel(employe, lieu, date);
            affectation.setCodeaffecter(codeaffecter);
            affecterDAO.updateAffectation(affectation);
            
            response.sendRedirect("listAffectations");
        }

        // 🔹 Supprimer une affectation
        private void deleteAffectation(HttpServletRequest request, HttpServletResponse response) throws IOException {
        	Long codeAffecter = Long.parseLong(request.getParameter("codeaffecter"));
            affecterDAO.deleteAffectation(codeAffecter);
            
            response.sendRedirect("listAffectations");
        }
	
}