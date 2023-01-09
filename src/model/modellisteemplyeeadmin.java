package model;

public class modellisteemplyeeadmin {
    Integer id;
    String nom;
    String prenom;
    String metier;
    Integer salaire;

    public modellisteemplyeeadmin(Integer id, String nom, String prenom, String metier, Integer salaire){
        this.id=id;
        this.nom=nom;
        this.prenom=prenom;
        this.metier=metier;
        this.salaire=salaire;
    }
    public Integer getId(){
        return id;
    }
  
    public String getNom(){
        return nom;
    }
   
    public String getPrenom(){
        return prenom;
    }
   
    public String getMetier(){
        return metier;
    }
   
    public Integer getSalaire(){
        return salaire;
    }
}
