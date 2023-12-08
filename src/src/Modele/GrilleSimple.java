package Modele;

import java.util.ArrayDeque;
import java.util.Observable;


public class GrilleSimple extends Observable implements Runnable {

    public boolean jeuFini = false;
    public final int TAILLEY = 20;

    public final int TAILLEX = 10;

    private Piece pieceCourante = new Piece(this);

    private Piece nextPiece = new Piece(this);

    public boolean[][] mySavingMap = new boolean[TAILLEX][TAILLEY];

    public GrilleSimple() {

        new OrdonnanceurSimple(this).start(); // pour changer le temps de pause, garder la référence de l'ordonnanceur
        
    }

    public void action() {
        //pieceCourante.action();


    }

    public boolean validationPosition(int _nextX, int _nextY) {

        boolean valide = true;

        int xAbs; int yAbs;

        for(int j = 0; j < 4; j++)
        {
            for(int i = 0; i < 4; i++)
            {
                xAbs = _nextX + i;
                yAbs = _nextY + j;

                if((xAbs > TAILLEX -1 || xAbs < 0 || yAbs > TAILLEY -1 || yAbs < 0 || mySavingMap[xAbs][yAbs]) && pieceCourante.motif[i][j]) {// {mySavingMap[xAbs][yAbs]){
                    valide = false;
                    break;
                }
            }
            if(!valide) break;
        }

        return valide;
    }

    public void placerDansGrille(int x, int y){


        /* tableau enum de couleurs, si vaut null alors ok sinon si couleur collision */
        int xAbs; int yAbs;

        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++){
                xAbs = pieceCourante.getx() + i;
                yAbs = pieceCourante.gety() + j;

                /* On rempli les cases de la grille de stockage en fonction des cases de la pièce qui valent true */
                if(pieceCourante.motif[i][j]) mySavingMap[xAbs][yAbs] = pieceCourante.motif[i][j];
            }
        }
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
