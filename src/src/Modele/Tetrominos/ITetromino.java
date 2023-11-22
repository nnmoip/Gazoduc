package Modele.Tetrominos;

public class ITetromino extends Pieces {
    String cle = "I";

    public final int[][] matrxI = {
            {0, 0, 1, 0},
            {0, 0, 1, 0},
            {0, 0, 1, 0},
            {0, 0, 1, 0}
    };

    public final int[][] matrxIr = {
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {1, 1, 1, 1},
            {0, 0, 0, 0}
    };

    public ITetromino(){
        super.pieces.put(cle, matrxI);
    }
}
