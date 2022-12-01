package relation;

import java.util.Vector;

public class Relation {
    String nom;
    Vector<String> attribut;
    Object[][] val;
    
    public Relation(String nom, Vector<String> attribut, Object[][] val) {
        setNom(nom);
        setAttribut(attribut);
        setVal(val);
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public Vector<String> getAttribut() {
        return attribut;
    }
    public void setAttribut(Vector<String> attribut) {
        this.attribut = attribut;
    }
    public Object[][] getVal() {
        return val;
    }
    public void setVal(Object[][] val) {
        this.val = val;
    }

    
}