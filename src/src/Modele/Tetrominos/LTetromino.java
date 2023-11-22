package Modele.Tetrominos;

public class LTetromino extends Pieces {

    /* clé pour identifier dans le dictionnaire */
    String cle = "L";

    /* matrice initiale */
    public final int[][] matrxL = {
            {1, 0, 0},
            {1, 0, 0},
            {1, 1, 0}
    };

    /* matrice reverse */
    public final int[][] matrxLr = {
            {0, 0, 0},
            {0, 0, 1},
            {1, 1, 1}
    };

    public LTetromino(){
        super.pieces.put(cle, matrxL);
    }

}
