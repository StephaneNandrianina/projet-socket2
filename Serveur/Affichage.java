package affichage;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTable;

import relation.Relation;

public class Affichage extends JFrame {
    public Affichage(){

    }
    public Affichage(Relation relation){
        Object[][] tableaux = relation.getTabl();
        String[] entete = relation.getColonne();
        JTable table = new JTable(tableaux,entete);
        getContentPane().add(table.getTableHeader() , BorderLayout.NORTH);
        getContentPane().add(table , BorderLayout.CENTER);
        this.setSize(500,500);
        this.setVisible(true);
    }
}
