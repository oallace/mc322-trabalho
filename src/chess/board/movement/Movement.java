package chess.board.movement;



import chess.StateMachineController;
import chess.board.Board;
import chess.board.pieces.*;
import chess.board.squares.Square;
import chess.player.Player;
import effects.EffectMachineController;

import java.util.ArrayList;

public abstract class Movement {

    // Retorna uma lista com as posições de todos os movimentos possíveis para uma dada peça. O parametro safeMovements, caso tenha o valor
    // true, faz com que o método retorne apenas os movimentos que evitem que o Rei do jogador fique em cheque.
    public abstract ArrayList<int[]> getValidMoves(boolean safeMovements, Piece piece);

    // Analisa se uma peça é inimiga do jogador passado como argumento
    protected boolean isEnemy(Square square, Player player){
        if (square.getPiece() != null && square.getPiece().getPlayer() != player){
            return true;
        }
        return false;
    }

    // Retorna uma lista com as posições de todos os movimentos possíveis em uma dada direção, para uma dada peça. O parametro "includeBlocked",
    //  sendo false, indica que ao encontrar uma peça inimiga deve-se interromper o movimento sem poder comer a peça (caso do peão).
    protected ArrayList<int[]> untilBlockedPath(Piece piece, int yDirection, int xDirection, boolean includeBlocked, int limit){
        ArrayList<int[]> moves = new ArrayList<>();

        Square currentSquare = piece.getSquare();

        // Checa se está congelado:
        if (EffectMachineController.instance.isFrozen(currentSquare.getPosition()[0], currentSquare.getPosition()[1]))
            return moves;

        while (currentSquare != null && moves.size() < limit){
            currentSquare = Board.instance.getSquare(currentSquare.getPosition()[0]+yDirection, currentSquare.getPosition()[1]+xDirection);
            
            if (currentSquare != null){
                // Checa se o caminho está bloqueado por uma muralha
                if (EffectMachineController.instance.isWall(currentSquare.getPosition()[0], currentSquare.getPosition()[1]))
                {
                    return moves;
                }
                else if (currentSquare.getPiece() == null){
                    moves.add(currentSquare.getPosition());
                }

                else if (isEnemy(currentSquare, piece.getPlayer())){
                    if (includeBlocked){
                        moves.add(currentSquare.getPosition());
                    }
                    return moves;
                }

                else{
                    return moves;
                }
            }
        }
        return moves;
    }


    // Analisa se um dado square está sob ataque de alguma peça do jogador passado como argumento
    public static boolean isSquareAttacked(Player player, Square square){
        Piece piece;
        int yPawnDirection = (player.getTeam() == "WhiteTeam") ? -1 : 1;
        ArrayList<Piece> listPieces = Board.instance.getTeamPieces(player.getTeam());

        for (int i = 0; i < listPieces.size(); i++){
            piece = listPieces.get(i);

            // Se for um peão, analisa e o square está na linha de ataque diagonal do peão
            if (piece instanceof Pawn){
                int [] pawnPosition = piece.getSquare().getPosition();

                if ((pawnPosition[0] + yPawnDirection) == square.getPosition()[0] && 
                    ((pawnPosition[1] - 1) == square.getPosition()[1] || (pawnPosition[1] + 1) == square.getPosition()[1])){
                    
                    return true;
                }
            }
            // Se não for um peão, analisa se o square esta nos movimentos válidos daquela peça.
            else{
                if (piece.getMovement().getValidMoves(false, piece).contains(square.getPosition())){
                    return true;
                }
            }
        }

        return false;
    }

    // Analisa quais dos movimentos de uma lista de movimentos são seguros, excluindo os demais.
    protected void getSafeMovements(ArrayList<int[]> moves, Piece piece){
        Player enemyPlayer = (piece.getPlayer() == StateMachineController.instance.getPlayer1()) ? StateMachineController.instance.getPlayer2() : StateMachineController.instance.getPlayer1();
        
        for (int i = 0; i < moves.size(); i++){
            if (!isSafeMovement(piece, moves.get(i), enemyPlayer)){
                moves.remove(i);
                i -= 1;
            }
        }
    }


    // Analisa se um movimento é seguro
    boolean isSafeMovement(Piece piece, int[] movement, Player enemyPlayer){
        // Obtem o square em que a peça esta, para depois da analise do movimento, colocá-la nele novamente
        Square currentPieceSquare = piece.getSquare();
        currentPieceSquare.setPiece(null);

        // Caso haja alguma peça inimiga no square para o qual a peça quer se movimentar, guarda essa peça inimiga, para após a análise
        // do movimento, colocá-la novamente no movementSquare.
        Square movementSquare = Board.instance.getSquare(movement[0], movement[1]);
        Piece enemyPiece = movementSquare.getPiece();
        Board.instance.removeTeamPiece(enemyPiece);

        // Coloca a peça da qual o movimento está sendo analisado no movementSquare.
        movementSquare.setPiece(piece);
        piece.setSquare(movementSquare);

        // Obter o rei do time da peça que esta tendo o movimento analisado
        Piece king = null;
        ArrayList<Piece> pieces = Board.instance.getTeamPieces(piece.getPlayer().getTeam());
        for (int i = 0; i < pieces.size(); i++){
            if (pieces.get(i) instanceof King){
                king = pieces.get(i);
                break;
            }
        }

        // Analisa se é seguro ou não o movimento
        boolean safe = true;
        if (king != null && isSquareAttacked(enemyPlayer, king.getSquare())){
            safe = false;
        }

        // Adiciona novamente a possível peça inimiga ao tabuleiro
        Board.instance.addTeamPiece(enemyPlayer.getTeam(), enemyPiece);
        movementSquare.setPiece(enemyPiece);

        // Retorna a peça que teve o movimento analisado para o square original
        piece.setSquare(currentPieceSquare);
        currentPieceSquare.setPiece(piece);


        return safe;
    }
}
