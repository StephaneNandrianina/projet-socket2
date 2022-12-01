package affiche;

import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JTable;

import relation.Relation;

public class Affichage extends JFrame {

    public Affichage() {
    }

    public Affichage(Relation relation) {
        this.setSize(500,500);
        Vector<String> attr = relation.getAttribut();
        Object val = relation.getVal();
        JTable table = new JTable(attr,val);
        getContentPane().add(table) ,border
     }
}