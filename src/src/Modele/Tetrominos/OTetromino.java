package Modele.Tetrominos;

import Modele.Tetrominos.Pieces;

public class OTetromino extends Pieces {
    String cle = "O";

    public final int[][] matrxO = {
            {1, 1},
            {1, 1}
    };

    public OTetromino(){
        super.pieces.put(cle, matrxO);
    }
}
