package Modele;

public class TTetromino extends Pieces{
    String cle = "T";

    /* matrice initiale T */
    public final int[][] matrxTs = {
            {0, 0, 0},
            {1, 1, 1},
            {0, 1, 0}
    };

    /* matrice T orientée vers l'ouest */
    public final int[][] matrxTo = {
            {0, 1, 0},
            {1, 1, 0},
            {0, 1, 0}
    };

    /* matrice T orientée vers le nord */
    public final int[][] matrxTn = {
            {0, 1, 0},
            {1, 1, 1},
            {0, 0, 0}
    };

     /* matrice T orientée vers l'est */
    public final int [][] matrxTe = {
             {0, 1, 0},
             {0, 1, 1},
             {0, 1, 0}
     };

    public TTetromino() {super.pieces.put(cle, matrxTs);}
}
