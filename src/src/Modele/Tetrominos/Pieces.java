package Modele.Tetrominos;

import java.util.HashMap;
import java.util.Map;
// Pour initialiser les couleurs des tetromino
import java.awt.Color;
// Pour la fonction aléatoire
import java.lang.Math;

public class Pieces implements Runnable {

    
    public boolean[][][] tetrominos = {
        {{false, false, false, false}, {true, true, true, true}, {false, false, false, false}, {false, false, false, false}}, // I index 0
        {{false, false, true, false}, {true, true, true, false}, {false, false, false, false}, {false, false, false, false}}, // J index 1
        {{false, false, false, false}, {true, true, true, false}, {false, false, true, false}, {false, false, false, false}}, // L index 2
        {{false, false, false, false}, {false, true, true, false}, {false, true, true, false}, {false, false, false, false}}, // O index 3
        {{false, false, true, false}, {false, true, true, false}, {false, true, false, false}, {false, false, false, false}}, // S index 4
        {{false, true, false, false}, {false, true, true, false}, {false, true, false, false}, {false, false, false, false}}, // T index 5
        {{false, true, false, false}, {false, true, true, false}, {false, false, true, false}, {false, false, false, false}}, // Z index 6
    };

    public boolean[][] motif; // = {{true, true, true}, {false, false, false}, {false, false, false}};

    public Color[] colors = {Color.cyan, Color.blue, Color.orange, Color.yellow, Color.green, Color.magenta, Color.red};
    
    public Color couleur;
    
    public int x = 5;
    public int y = 5;

    

    
    public void selectRandomTetromino(int num){
        int rand = (int) (Math.random() * 7);
        //System.out.println(rand);
        motif = tetrominos[rand];
        couleur = colors[rand];
    }

    public Pieces(){
        selectRandomTetromino(6);
    }

    /* On crée un dictionnaire de pièces pour pouvoir les retrouver */
    public static Map<String, int[][]> pieces = new HashMap<String, int[][]>();

    public Map<String, int[][]> getPieces(){ return pieces; }

    @Override
    public void run() {
        y++;
    }
}

/* https://codegym.cc/fr/groups/posts/fr.385.matrice-en-java-tableaux-2d */
    


