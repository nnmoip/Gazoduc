package Modele;

import java.util.Observable;


public class GrilleSimple extends Observable implements Runnable {

    public final int TAILLE = 20;

    private Piece pieceCourante = new Piece();


    public GrilleSimple() {

        new OrdonnanceurSimple(this).start(); // pour changer le temps de pause, garder la référence de l'ordonnanceur

    }

    public void action() {
        //pieceCourante.action();


    }

    public boolean validationPosition(int _nextX, int _nextY) {
        return (_nextY>=0 && _nextY < TAILLE);
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
