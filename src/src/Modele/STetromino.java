package Modele;

public class STetromino extends Pieces{

    /* cle pour l'identifier dans le dictionnaire qui contient toutes les pièces */
    String cle = "S";

    /* matrice initiale de forme S */
    public final int[][] matrxS = {
        {0, 0, 0},
        {0, 1, 1},
        {1, 1, 0}
    };

    /* matrice "reverse" de forme S lorsque l'on utilise la fonction rotation */
    public final int[][] matrxSr = {
        {0, 1, 0},
        {1, 1, 0},
        {1, 0, 0}
    };

    // public int dimension;

    public STetromino() {
        super.pieces.put(cle, matrxS);
    }
}
