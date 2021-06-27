package chess.board.movement;



import chess.board.Board;
import chess.board.pieces.Piece;
import chess.board.squares.Square;
import effects.EffectMachineController;

import java.util.ArrayList;

public class KnightMovement extends Movement {

    public ArrayList<int[]> getValidMoves(boolean safeMovements, Piece piece){

        ArrayList<int[]> moves = new ArrayList<>();
        // Se o cavalo estiver congelado, ele não pode se movimentar:
        if (EffectMachineController.instance.isFrozen(piece.getSquare().getPosition()[0], piece.getSquare().getPosition()[1]))
            return moves;

        moves.addAll(getLMovement(piece, 0, 1));
        moves.addAll(getLMovement(piece, 0, -1));
        moves.addAll(getLMovement(piece, 1, 0));
        moves.addAll(getLMovement(piece, -1, 0));

        if (safeMovements){
            getSafeMovements(moves, piece);
        }

        return moves;
    }

    ArrayList<int[]> getLMovement(Piece piece, int yDirection, int xDirection){
        ArrayList<int[]> moves = new ArrayList<>();
        Piece selectePiece = piece;
        Square square = Board.instance.getSquare(selectePiece.getSquare().getPosition()[0]+2*yDirection, selectePiece.getSquare().getPosition()[1]+2*xDirection);
        
        if (square != null){
            moves.addAll(getCurvedPart(selectePiece, square.getPosition(), xDirection, yDirection));
        }

        return moves;
    }

    ArrayList<int[]> getCurvedPart(Piece piece, int[] squarePosition, int yDirection, int xDirection){

        ArrayList<int[]> moves = new ArrayList<>();
        Square square1 = Board.instance.getSquare(squarePosition[0] + yDirection, squarePosition[1] + xDirection);
        Square square2 = Board.instance.getSquare(squarePosition[0] - yDirection, squarePosition[1] - xDirection);

        if (square1 != null && (square1.getPiece()==null || isEnemy(square1, piece.getPlayer()))){
            moves.add(square1.getPosition());
        }
        if (square2 != null && (square2.getPiece()==null || isEnemy(square2, piece.getPlayer()))){
            moves.add(square2.getPosition());
        }

        return moves;
    }
}
