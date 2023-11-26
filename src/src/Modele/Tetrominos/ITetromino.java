package Modele.Tetrominos;

public class ITetromino extends Piece {
    public int dimension = 4;
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
