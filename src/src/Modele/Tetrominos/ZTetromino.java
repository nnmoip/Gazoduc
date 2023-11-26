package Modele.Tetrominos;

import Modele.Tetrominos.Piece;

public class ZTetromino extends Piece {
    /* clé pour identifier dans le dictionnaire */

    String cle = "Z";

    /* matrice initiale */

    public final int[][] matrxZ = {
            {0, 0, 0},
            {1, 1, 0},
            {0, 1, 1}
    };

    /* matrice reverse */

    public final int [][] matrxZr = {
            {1, 0, 0},
            {1, 1, 0},
            {0, 1, 0}
    };

    public ZTetromino() {
        super.pieces.put(cle, matrxZ);
    }
}
