package Modele;

import java.util.ArrayDeque;
import java.util.Observable;


public class GrilleSimple extends Observable implements Runnable {

    public final int TAILLEY = 20;

    public final int TAILLEX = 10;

    private Piece pieceCourante = new Piece(this);

    public boolean[][] mySavingMap = new boolean[TAILLEX][TAILLEY];

    public GrilleSimple() {

        

        new OrdonnanceurSimple(this).start(); // pour changer le temps de pause, garder la référence de l'ordonnanceur

        /*
        for(int i = 0; i < TAILLEX; i++){
            for(int j = 0; j < TAILLEY; i++){
                mySavingMap[i][j] = false;
            }
        }
        */
        
    }

    public void action() {
        //pieceCourante.action();


    }

    public boolean validationPosition(int _nextX, int _nextY) {
        /*
        boolean[][] mySavingMap2 = mySavingMap;

        boolean valide = true;

        int x = pieceCourante.getx() + 3;
        int y = pieceCourante.gety() + 3;
        int piecex = 3;
        int piecey = 3;
        int resety = pieceCourante.gety();

        int decalx = _nextX - pieceCourante.getx();
        int decaly = _nextY - pieceCourante.gety();

        for(int i = TAILLEX - 1; i >= 0 ; i--){
            // On devra effectuer la modification 4 fois seulement à partir du moment où on trouve le bon y
            y = resety;
            for(int j = TAILLEY - 1; j >= 0 ; j--){
                // Si l'on tombe sur la pièce courante
                if(i == x && j == y){
                    // Si à la position que je veux ensuite la pièce vaut true ou sort pas de la grille, j'invalide la position
                    if(x>TAILLEX || y>TAILLEY || (mySavingMap[x+decalx][y+decaly] && pieceCourante.motif[piecex][piecey])){
                        valide = false;
                        break;
                    }
                    else {
                        // J'efface la case ij qui était couverte par la matrice de ma pièce ...
                        mySavingMap2[i][j] = false;
                        // ... et je met la valeur de la case de la pièce dans la nouvelle case
                        mySavingMap2[i+decalx][j+decaly] = pieceCourante.motif[piecex][piecey];

                        if(piecey == 0){x--; piecex--;}
                        else{y--; piecey--;}
                    }
                }
            }
        }
        if(valide) mySavingMap = mySavingMap2;
        return valide;
        */

        boolean[][] mySavingMap2 = mySavingMap;

        boolean valide = true;

        int XfinPC = pieceCourante.getx() + 3;
        int YfinPC = pieceCourante.gety() + 3;

        int decalx = _nextX - pieceCourante.getx();
        int decaly = _nextY - pieceCourante.gety();

        int x = 3;
        int y = 3;


        for(int i = TAILLEX - 1; i >= 0 ; i--){
            for(int j = TAILLEY - 1; j >= 0; j--){
                if(i == XfinPC && j == YfinPC){

                    if(XfinPC > (TAILLEX -1) || XfinPC < 0 ||
                    YfinPC > (TAILLEY -1) || YfinPC < 0 ||
                    mySavingMap2[XfinPC+decalx][YfinPC+decaly] && pieceCourante.motif[x][y]){
                        valide = false;
                        break;
                    }

                    else {
                        mySavingMap2[i][j] = false;
                        mySavingMap2[XfinPC+decalx][YfinPC+decaly] = pieceCourante.motif[x][y];

                        y--; YfinPC--;
                        if(y==-1){
                            x--; XfinPC--;
                            y = 3; YfinPC = pieceCourante.gety() + 3;
                        }
                    }
                }
            }
        }

        if(valide) mySavingMap = mySavingMap2;
        return valide;

        //return (_nextY>=0 && _nextY < TAILLEY-3);
    }

    public void run() {

        pieceCourante.run();
        setChanged(); // setChanged() + notifyObservers() : notification de la vue pour le rafraichissement
        notifyObservers();

    }

    public Piece getPieceCourante() {
        return pieceCourante;
    }

}
