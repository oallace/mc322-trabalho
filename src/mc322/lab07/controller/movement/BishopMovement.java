package mc322.lab07.controller.movement;

import java.util.ArrayList;

import mc322.lab07.model.pieces.Piece;

public class BishopMovement extends Movement{

    public ArrayList<int[]> getValidMoves(boolean safeMovements, Piece piece){

        ArrayList<int[]> moves = new ArrayList<>();

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
