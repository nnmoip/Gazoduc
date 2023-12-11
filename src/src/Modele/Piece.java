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
    
    /* Contient la couleur de la pièce */
    public Color couleur;

    public int tailleMatrices = 4;

    /* Quand la pièce ne peut plus bouger */
    public boolean PiecePlacee = false;
    
    private int x = 5;
    private int y = 0;

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

    public void action(int keycode) {
        switch(keycode){
            case 37 : // flèche gauche
                goLeft(); break;
            case 39 : // flèche droite
                goRight(); break;
            case 81 : // touche Q pour rotation à gauche
                turnLeft(); break;
            case 68 : // touche D pour rotation à droite
                turnRight(); break;
            case 32 : // touche espace pour poser la pièce en bas
                poseBas(); break;
        }
    }

    public void goLeft(){
        if(grille.validationPosition(motif, x-1, y)) x -= 1;
    }

    public void goRight(){
        if(grille.validationPosition(motif, x+1, y)) x += 1;
    }

    public void turnLeft(){
        boolean[][] newMotif = new boolean[4][4];
            for(int i = 0; i < 4; i++){
                for(int j = 0; j < 4; j++){
                    newMotif[i][j] = motif[j][3-i];
                }
            }
            if(grille.validationPosition(newMotif, x, y)) motif = newMotif;
    }

    public void turnRight(){
        boolean[][] newMotif = new boolean[4][4];
            for(int i = 0; i < 4; i++){
                for(int j = 0; j < 4; j++){
                    newMotif[i][j] = motif[3-j][i];
                }
            }
            if(grille.validationPosition(newMotif, x, y)) motif = newMotif;
    }

    public void poseBas(){
        while(grille.validationPosition(motif, x, y+1)){
                y += 1;
            }
    }

    @Override
    public void run() {
        
        int nextX = x;
        int nextY = y+1;
        if(grille.validationPosition(motif, nextX, nextY)){
            y++;
        }
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

    public Color getColor(){
        return couleur;
    }
}


