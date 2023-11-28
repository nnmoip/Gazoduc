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

        int x = pieceCourante.getx();
        int y = pieceCourante.gety();

        int decalY = _nextY - y;
        int decalX = _nextX - x;

        // On décrémente le nombre de fois où on a replacer y par tour de boucle
        int dely;

        for(int i = 0; i < TAILLEX; i++){
            for(int j = 0; j < TAILLEY; j++){
                // On devra effectuer la modification 4 fois seulement à partir du moment où on trouve le bon y
                dely = 4;
                if(i == x && j == y){
                    // Si à la position que je veux ensuite la pièce vaut true ou sort pas de la grille, j'invalide la position
                    if(x>TAILLEX || y>TAILLEY || (mySavingMap[i+decalX][j+decalY] && pieceCourante.motif[x][y])){
                        valide = false;
                        break;
                    }
                    else {
                        // J'efface la case ij qui était couverte par la matrice de ma pièce ...
                        mySavingMap2[i][j] = false;
                        // ... et je met la valeur de la case de la pièce dans la nouvelle case
                        mySavingMap2[i+decalX][j+decalY] = pieceCourante.motif[x][y];

                        y--; dely -=1;
                        if(dely == 0) x--;
                    }
                }
            }
        }
        if(valide) mySavingMap = mySavingMap2;
        return valide;
        */
        return (_nextY>=0 && _nextY < TAILLEY-3);
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
