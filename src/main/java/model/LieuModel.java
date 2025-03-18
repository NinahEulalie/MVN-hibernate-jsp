package model;

public class LieuModel {

    private Long codelieu;
    private String designation;
    private String province;
    
    //constructeur par défaut OBLIGATOIRE pour hibernate
    public LieuModel() {}

    public LieuModel(String designation, String province) {
        this.designation = designation;
        this.province = province;
    }

    // Getters et Setters
    public Long getCodelieu() { return codelieu; }
    public void setCodelieu(Long codelieu) { this.codelieu = codelieu; }

    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }

    public String getProvince() { return province; }
    public void setProvince(String province) { this.province = province; }

  
}