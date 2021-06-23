package chess.board.movement;



import chess.board.pieces.Piece;

import java.util.ArrayList;

public class QueenMovement extends Movement {

    public ArrayList<int[]> getValidMoves(boolean safeMovements, Piece piece){

        ArrayList<int[]> moves = new ArrayList<>();

        moves.addAll(untilBlockedPath(piece, 1, 0, true, 8));
        moves.addAll(untilBlockedPath(piece, -1, 0, true, 8));
        moves.addAll(untilBlockedPath(piece, 0, 1, true, 8));
        moves.addAll(untilBlockedPath(piece, 0, -1, true, 8));
        moves.addAll(untilBlockedPath(piece, 1, 1, true, 8));
        moves.addAll(untilBlockedPath(piece, -1, 1, true, 8));
        moves.addAll(untilBlockedPath(piece, 1, -1, true, 8));
        moves.addAll(untilBlockedPath(piece, -1, -1, true, 8));

        if (safeMovements){
            getSafeMovements(moves, piece);
        }

        return moves;
    }
}
