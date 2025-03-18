package model;


import java.util.Date;

public class AffecterModel {

    private Long codeaffecter;
    private EmployeModel employe;
    private LieuModel lieu;
    private Date date;
    
    //constructeur par défaut OBLIGATOIRE pour hibernate
    public AffecterModel() {}

    public AffecterModel(EmployeModel employe, LieuModel lieu, Date date) {
        this.employe = employe;
        this.lieu = lieu;
        this.date = date;
    }

    // Getters et Setters
    public Long getCodeaffecter() { return codeaffecter; }
    public void setCodeaffecter(Long id) { this.codeaffecter = id; }

    public EmployeModel getEmploye() { return employe; }
    public void setEmploye(EmployeModel employe) { this.employe = employe; }

    public LieuModel getLieu() { return lieu; }
    public void setLieu(LieuModel lieu) { this.lieu = lieu; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
}