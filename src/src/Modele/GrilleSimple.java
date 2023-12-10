package Modele;

import java.awt.Color;
import java.util.Set;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Observable;


public class GrilleSimple extends Observable implements Runnable {

    public boolean jeuFini = false;
    public final int TAILLEY = 20;

    public final int TAILLEX = 10;

    private Piece pieceCourante = new Piece(this);

    private Piece nextPiece = new Piece(this);

    public Color[][] mySavingMap = new Color[TAILLEX][TAILLEY];

    public GrilleSimple() {

        new OrdonnanceurSimple(this).start(); // pour changer le temps de pause, garder la référence de l'ordonnanceur
        
    }

    public void action(int keycode) {
        pieceCourante.action(keycode);


    }

    public boolean validationPosition(boolean[][] piece, int _nextX, int _nextY) {

        boolean valide = true;

        int xAbs; int yAbs;

        for(int j = 0; j < 4; j++)
        {
            for(int i = 0; i < 4; i++)
            {
                xAbs = _nextX + i;
                yAbs = _nextY + j;

                if((xAbs > TAILLEX -1 || xAbs < 0 || yAbs > TAILLEY -1 || yAbs < 0 || mySavingMap[xAbs][yAbs] != null) && piece[i][j]) {// {mySavingMap[xAbs][yAbs]){
                    valide = false;
                    break;
                }
            }
            if(!valide) break;
        }

        return valide;
    }

    /* fonction qui se charge de faire descendre le tetris quand des lignes sont pleines */
    public void effacerLignes(){
        boolean lignePleine;
        for(int j = TAILLEY-1; j >= 0; j--){
            lignePleine = true;

            for(int i = TAILLEX-1; i >= 0; i--){
                if(mySavingMap[i][j] == null){
                    lignePleine = false;
                    break;
                }
            }

            if(lignePleine){
                for(int sy = j; sy > 0; sy--){
                    for(int l = TAILLEX-1; l >= 0; l--){
                        mySavingMap[l][sy] = mySavingMap[l][sy-1];
                    }
                }
                j += 1;
            }
        }
    }

    public void placerDansGrille(int x, int y){

        int xAbs; int yAbs;

        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++){
                xAbs = pieceCourante.getx() + i;
                yAbs = pieceCourante.gety() + j;

                /* On rempli les cases de la grille de stockage en fonction de la couleur de la case coloriée */
                if(pieceCourante.motif[i][j]) mySavingMap[xAbs][yAbs] = pieceCourante.getColor();
            }
        }

        effacerLignes();
    }

    public void run() {
        pieceCourante.run();
        if(pieceCourante.PiecePlacee) {
            pieceCourante = nextPiece;
            nextPiece = new Piece(this);
        }
        setChanged(); // setChanged() + notifyObservers() : notification de la vue pour le rafraichissement
        notifyObservers();
    }

    public Piece getPieceCourante() {
        return pieceCourante;
    }

}
