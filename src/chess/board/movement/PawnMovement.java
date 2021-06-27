package chess.board.movement;



import chess.board.Board;
import chess.board.pieces.Piece;
import chess.board.squares.Square;
import chess.player.Player;
import effects.EffectMachineController;

import java.util.ArrayList;

public class PawnMovement extends Movement {

    public ArrayList<int[]> getValidMoves(boolean safeMovements, Piece piece){
        ArrayList<int[]> moves = new ArrayList<>();
        // O Movimento não é permitido se o peão estiver congelado:
        if (EffectMachineController.instance.isFrozen(piece.getSquare().getPosition()[0], piece.getSquare().getPosition()[1]))
            return moves;

        // Pega 2 como limite de movimentos caso seja o primeiro movimento do peão
        int limit = 1;
        if (!piece.getWasMoved()){
            limit = 2;
        }

        // Pegar os movimentos válidos
        int[] direction = getDirection(piece.getPlayer());
        moves.addAll(untilBlockedPath(piece, direction[0], direction[1], false, limit));
        moves.addAll(getPawnAttack(direction, piece));


        if (safeMovements){
            getSafeMovements(moves, piece);
        }

        // Analisa os movimentos válidos do peão. Se algum deles gerar uma promoção ou pulo duplo atualiza o moveType do square no qual o peão
        // irá se movimentar para PawnPromotionMovement ou PawnDoubleMovement
        for (int i = 0; i < moves.size(); i++){
            int[] position = moves.get(i);
            if (position[0] == 0 || position[0] == 7)
                Board.instance.getSquare(position[0], position[1]).setMoveType(MoveType.PawnPromotionMovement);

            if (Math.abs(position[0] - piece.getSquare().getPosition()[0]) == 2)
                Board.instance.getSquare(position[0], position[1]).setMoveType(MoveType.PawnDoubleMovement);
        }

        return moves;
    }

    // Pega a direção de movimento do peão.
    int[] getDirection(Player player){
        int[] direction = new int[2];
        direction[1] = 0;
        if (player.getTeam() == "WhiteTeam"){
            direction[0] = -1;
        }
        else{
            direction[0] = 1;
        }
        return direction;
    }

    // Analisa se nas diagonais no sentido retornado pela getDirection() há alguma peça inimiga. Se sim, o peão pode comê-las, logo são
    // posições válidas para o movimento.
    ArrayList<int[]> getPawnAttack(int[] direction, Piece piece){
        ArrayList<int[]> pawnAttack = new ArrayList<>();
        Square square;
        Piece selectedPiece = piece;

        square = Board.instance.getSquare(selectedPiece.getSquare().getPosition()[0]+direction[0], selectedPiece.getSquare().getPosition()[1]-1);
            if (square != null &&
                !EffectMachineController.instance.isWall(square.getPosition()[0], square.getPosition()[1]) && // Não ultrapassa muralhas
                (isEnemy(square, piece.getPlayer()) || square.getMoveType() == MoveType.EnPassantMovement))
            {
            pawnAttack.add(square.getPosition());
           }

        square = Board.instance.getSquare(selectedPiece.getSquare().getPosition()[0]+direction[0], selectedPiece.getSquare().getPosition()[1]+1);
        if (square != null &&
            !EffectMachineController.instance.isWall(square.getPosition()[0], square.getPosition()[1]) && // Não ultrapassa muralhas
            (isEnemy(square, piece.getPlayer()) || square.getMoveType() == MoveType.EnPassantMovement))
        {
            pawnAttack.add(square.getPosition());
        }

        return pawnAttack;
    }
}
