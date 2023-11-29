package Modele;

// Pour initialiser les couleurs des tetromino
import java.awt.Color;
// Pour la fonction aléatoire
import java.lang.Math;

public class Piece implements Runnable {

    // Liste des matrices de tetrominos, en position d'origine
    private final static boolean[][][] tetrominos = {
        {{false, false, false, false}, {true, true, true, true}, {false, false, false, false}, {false, false, false, false}}, // I index 0
        {{false, false, true, false}, {true, true, true, false}, {false, false, false, false}, {false, false, false, false}}, // J index 1
        {{false, false, false, false}, {true, true, true, false}, {false, false, true, false}, {false, false, false, false}}, // L index 2
        {{false, false, false, false}, {false, true, true, false}, {false, true, true, false}, {false, false, false, false}}, // O index 3
        {{false, false, true, false}, {false, true, true, false}, {false, true, false, false}, {false, false, false, false}}, // S index 4
        {{false, true, false, false}, {false, true, true, false}, {false, true, false, false}, {false, false, false, false}}, // T index 5
        {{false, true, false, false}, {false, true, true, false}, {false, false, true, false}, {false, false, false, false}}, // Z index 6
    };

    public boolean[][] motif; // = {{true, true, true}, {false, false, false}, {false, false, false}};

    // Liste des couleurs associées à chaque tetrominos
    private final static Color[] colors = {Color.cyan, Color.blue, Color.orange, Color.yellow, Color.green, Color.magenta, Color.red};
    
    public Color couleur;

    public int tailleMatrices = 4;

    public boolean PiecePlacee = false;
    
    private int x = 5;
    private int y = 5;

    private GrilleSimple grille;

    
    public void selectRandomTetromino(){
        int rand = (int) (Math.random() * 7);
        //System.out.println(rand);
        motif = tetrominos[rand];
        couleur = colors[rand];
    }

    public Piece(GrilleSimple grille){
        selectRandomTetromino();
        this.grille = grille;
    }

    @Override
    public void run() {
        int nextX = x;
        int nextY = y+1;
        if(grille.validationPosition(nextX, nextY)) y++;
        else{
            grille.placerDansGrille(x,y);
            PiecePlacee = true;
        }
    }

    public int getx(){
        return x;
    }

    public int gety(){
        return y;
    }
}


