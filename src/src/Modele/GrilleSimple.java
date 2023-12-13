package Modele;

import javax.swing.*;
import java.awt.*;
import java.util.Set;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Observable;


public class GrilleSimple extends Observable implements Runnable {

    // variables relative à le gestion des actions dans le temps
    public boolean enCours = false;
    public boolean enPause = false;
    // vaut true pour une question de bonne initialisation du temps
    public boolean jeuFini = true;

    // dimensions de la grille qui représente le tétris
    public final int TAILLEY = 20;
    public final int TAILLEX = 10;

    // affichage qui prendra des valeurs différentes dans le temps
    public boolean resetTemps = false;
    public int meilleurScore = 0;
    public int score = 0;
    public int nbLignes;

    // pour la gestion des pièces
    private Piece pieceCourante = new Piece(this);
    private Piece nextPiece = new Piece(this);

    // grille qui permet de sauvegarder les pièces en jeu
    public Color[][] mySavingMap = new Color[TAILLEX][TAILLEY];





    public GrilleSimple() {

        new OrdonnanceurSimple(this).start(); // pour changer le temps de pause, garder la référence de l'ordonnanceur
        
    }





    // lors d'une nouvelle partie on (re)initialise toutes les variables
    public void demarrer(){
        enCours = true;
        if(jeuFini){
            score = 0;
            nbLignes = 0;
            jeuFini = false;
            resetTemps = true;
        }
    }



    public void pause(){
        if(enPause) enPause = false;
        else enPause = true;
    }





    /*
     * si la touche enfoncée se rapporte à un des boutons disponibles (pause ou nouvelle partie)
     on fait directement l'action qui s'y rapporte
     * sinon on envoie le résultat à la pièce courante pour qu'elle effectue le mouvement demandé
     */   
    public void action(int keycode) {
        switch(keycode){
            case 10 : // touche entrée
                demarrer();
                break;
            case 80 : // touche P
                pause();
                break;
            default :
                pieceCourante.action(keycode);
        }
    }





    public boolean validationPosition(boolean[][] piece, int _nextX, int _nextY) {

        boolean valide = true;

        int xAbs; int yAbs;

        for(int j = 0; j < 4; j++)
        {
            for(int i = 0; i < 4; i++)
            {
                // positions correspondantes dans la grille globale
                xAbs = _nextX + i;
                yAbs = _nextY + j;

                if((xAbs > TAILLEX -1 || xAbs < 0 || yAbs > TAILLEY -1 || yAbs < 0 || mySavingMap[xAbs][yAbs] != null) && piece[i][j]) {// {mySavingMap[xAbs][yAbs]){
                    valide = false;
                    break;
                }
            }
            if(!valide) break;
        }

        if(valide && _nextY != pieceCourante.gety()) score +=1;

        // mise à jour du meilleur score si dépassé
        if(score > meilleurScore) meilleurScore = score;

        return valide;
    }





    // fonction qui se charge de faire descendre le tetris quand des lignes sont pleines
    public void effacerLignes(){
        boolean lignePleine;
        int nbLignesTour = 0;
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
                // la ligne suivante a vérifié à été baissée d'un cran
                j += 1;
                nbLignesTour += 1;
            }
        }

        nbLignes += nbLignesTour;

        // points bonus lorsque plusieurs lignes sont supprimées d'un coup
        switch(nbLignesTour){
            case 1: score += 40; break;
            case 2: score += 100; break;
            case 3: score += 300; break;
            case 4: score += 1200; break;
        }
        // mise à jour du meilleur score si dépassé
        if(score > meilleurScore) meilleurScore = score;
    }





    public void cleanMap()
    {
        for(int i = 0; i < TAILLEX; i ++)
        {
            for (int j = 0; j < TAILLEY; j++)
            {
                mySavingMap[i][j] = null;
            }
        }
    }





    public void placerDansGrille(int x, int y){

        int xAbs; int yAbs;

        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++){
                xAbs = pieceCourante.getx() + i;
                yAbs = pieceCourante.gety() + j;

                // On rempli les cases de la grille de stockage en fonction de la couleur de la case coloriée
                if(pieceCourante.motif[i][j]) mySavingMap[xAbs][yAbs] = pieceCourante.getColor();
            }
        }

        effacerLignes();
    }





    public void run() {

        if(enCours && !enPause){
            if (pieceCourante.PiecePlacee) {
                if (!jeuFini) {
                    if (pieceCourante.gety() < 1) {
                        jeuFini = true;
                        pieceCourante.couleur = null;
                        cleanMap();
                        enCours = false;
                    }
                    pieceCourante = nextPiece;
                    nextPiece = new Piece(this);
                }
            } else {
                pieceCourante.run();
            }


            setChanged(); // setChanged() + notifyObservers() : notification de la vue pour le rafraichissement
            notifyObservers();
        }
        
    }



    
    public Piece getPieceCourante() {
        return pieceCourante;
    }

}
