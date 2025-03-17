package model;

public class EmployeModel {

    private Long codeemp;
    private String nom;
    private String prenom;
    private String poste;

    public EmployeModel() {}

    public EmployeModel(Long codeemp, String nom, String prenom, String poste) {
        this.codeemp = codeemp;
        this.nom = nom;
        this.prenom = prenom;
        this.poste = poste;
    }
    
    public EmployeModel(String nom, String prenom, String poste) {
        this.nom = nom;
        this.prenom = prenom;
        this.poste = poste;
    }

    // Getters et Setters
    public Long getCodeemp() { return codeemp; }
    public void setCodeemp(Long codeemp) { this.codeemp = codeemp; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getPoste() { return poste; }
    public void setPoste(String poste) { this.poste = poste; }

}