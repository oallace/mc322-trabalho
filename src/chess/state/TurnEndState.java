package chess.state;


import java.util.ArrayList;

import chess.StateMachineController;
import chess.board.Board;
import chess.board.movement.Movement;
import chess.board.pieces.King;
import chess.board.pieces.Piece;
import chess.player.Player;
import effects.EffectMachineController;

public class TurnEndState extends State{

    public void enter(){
        System.out.println("TurnEndState:");

        EffectMachineController.instance.passShift();

        Player currentlyPlayer = StateMachineController.instance.getCurrentPlayer();
        Player enemyPlayer = (currentlyPlayer == StateMachineController.instance.getPlayer1()) ? StateMachineController.instance.getPlayer2() : StateMachineController.instance.getPlayer1();

        // Checa se o inimigo possui movimentos válidos. Se sim, então o jogo não terminou. Se não possui movimentos válidos, se o rei inimigo
        // estiver em xeque, o jogador atual ganha por xeque mate, se o rei inimigo estiver seguro (porém não houver movimento válido para 
        // nenhuma peça), ocorre um empate por afogamento.
        boolean enemyValidMoves = checkEnemyMoves(enemyPlayer);

        if (!enemyValidMoves){
            boolean isKingEnemyAttacked = checkEnemyKing(currentlyPlayer, enemyPlayer);

            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            if (isKingEnemyAttacked){
                System.out.println("\nJogador " + currentlyPlayer.getName() + " ganhou");
            }
            else{
                System.out.println("\nEmpate por afogamento");
            }

            StateMachineController.instance.changeTo(new GameEndState());
        }
        else{
            StateMachineController.instance.changeTo(new TurnBeginState());
        }
    }


    // Retorna true se existem movimentos válidos para o inimigo, ou false caso não exista.
    boolean checkEnemyMoves(Player enemyPlayer){
        ArrayList<Piece> enemyPieces = Board.instance.getTeamPieces(enemyPlayer.getTeam());
        ArrayList<int[]> moves = new ArrayList<>();

        for (int i = 0; i < enemyPieces.size(); i++){
            Piece piece = enemyPieces.get(i);
            moves.addAll(piece.getMovement().getValidMoves(true, piece));
        }

        if (moves.size() == 0)
            return false;
        
        return true;
    }

    // Retorna true caso o rei do inimigo esteja em xeque, ou false caso esteja seguro.
    boolean checkEnemyKing(Player currentlyPlayer, Player enemyPlayer){
        ArrayList<Piece> enemyPieces = Board.instance.getTeamPieces(enemyPlayer.getTeam());
        Piece king = null;
        for (int i = 0; i < enemyPieces.size(); i++){
            if (enemyPieces.get(i) instanceof King){
                king = enemyPieces.get(i);
                break;
            }
        }
        
        if (king != null && Movement.isSquareAttacked(currentlyPlayer, king.getSquare()))
            return true;
        
        return false;
    }

}
