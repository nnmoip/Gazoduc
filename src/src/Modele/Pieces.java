package Modele;

import java.util.HashMap;
import java.util.Map;

public class Pieces {

    // public int dimension;

    /* On crée un dictionnaire de pièces pour pouvoir les retrouver */
    public Map<String, int[][]> pieces = new HashMap<String, int[][]>();

    /*
    public Pieces(int dimension, int[][] pieceRajout){

        pieces.add(pieceRajout);

        // this.dimension = dimension;
    }
    */

    public Pieces(){};
}

/* https://codegym.cc/fr/groups/posts/fr.385.matrice-en-java-tableaux-2d */
