package Modele.Tetrominos;

public class JTetromino extends Pieces {
    String cle = "J";

    public final int[][] matrxJ = {
            {0, 0, 1},
            {0, 0, 1},
            {0, 1, 1}
    };

    public final int[][] matrxJr = {
            {0, 0, 0},
            {1, 0, 0},
            {1, 1, 1}
    };

    public JTetromino(){
        super.pieces.put(cle, matrxJ);
    }
}
