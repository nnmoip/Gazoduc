package Modele.Tetrominos;

import java.util.HashMap;
import java.util.Map;

public class Pieces implements Runnable {

    public boolean[][] motif = {{true, true, true}, {false, false, false}, {false, false, false}};
    public int x = 5;
    public int y = 5;


    /* Dimension par défaut */
    public int dimension = 3;

    /* On crée un dictionnaire de pièces pour pouvoir les retrouver */
    public static Map<String, int[][]> pieces = new HashMap<String, int[][]>();

    public Pieces(){};

    public Map<String, int[][]> getPieces(){ return pieces; }

    @Override
    public void run() {
        y++;
    }
}

/* https://codegym.cc/fr/groups/posts/fr.385.matrice-en-java-tableaux-2d */
