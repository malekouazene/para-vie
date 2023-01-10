package controller;




import java.sql.Date;

public class modellistemedadmin {
    Integer num;
    String nom;
    String type;
    String dosage;
    Date dateexp;
    Date datefab;
    Float prix;
    public modellistemedadmin(){
        super();
    }
    public modellistemedadmin(  int nummed, String nommed, String typemed, String dosage, Date dateexp, Date datefab, Float prixmed){
        this.num=nummed;
        this.nom=nommed;
        this.type=typemed;
        this.dosage=dosage;
        this.dateexp=dateexp;
        this.datefab=datefab;
        this.prix=prixmed;
    }
    public  getnum(){
        return num;
    }
    public void setNum(int num){
        this.num=num;
    }
    public String getnom(){
        return nom;
    }
    public void setNom(String nom){
        this.nom=nom;
    }
    public String gettype(){
        return type;
    }
    public void setType(String type){
        this.type=type;
    }
    public String getdosage(){
        return dosage;
    }
    public void setDosage(String dosage){
        this.dosage=dosage;
    }
    public Date getdateexp(){
        return dateexp;
    }
    public void setDateexp(Date dateexp){
        this.dateexp=dateexp;
    }
    public Date getdatefab(){
        return datefab;
    }
    public void setDatefab(Date datefab){
        this.datefab=datefab;
    }
    public Float getprix(){
        return prix;
    }
    public void setPrix(Float prix){
        this.prix=prix;
    }
}



