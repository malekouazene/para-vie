package model;


import java.sql.Date;

public class modellistemedadmin {
    Integer num;
    String nom;
    String type;
    String dosage;
    Date dateexp;
    Date datefab;
    Float prix;
   
    public modellistemedadmin(  Integer num, String nom, String type, String dosage, Date dateexp, Date datefab, Float prix){
        this.num=num;
        this.nom=nom;
        this.type=type;
        this.dosage=dosage;
        this.dateexp=dateexp;
        this.datefab=datefab;
        this.prix=prix;
    }
    public Integer getNum(){
        return num;
    }
  
    public String getNom(){
        return nom;
    }
   
    public String getType(){
        return type;
    }
   
    public String getDosage(){
        return dosage;
    }
   
    public Date getDateexp(){
        return dateexp;
    }
   
    public Date getDatefab(){
        return datefab;
    }
   
    public Float getPrix(){
        return prix;
    }
    
}



