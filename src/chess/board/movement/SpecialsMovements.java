package chess.board.movement;


import chess.StateMachineController;
import chess.board.Board;
import chess.board.pieces.Bishop;
import chess.board.pieces.Knight;
import chess.board.pieces.Pawn;
import chess.board.pieces.Piece;
import chess.board.pieces.Queen;
import chess.board.pieces.Rook;
import chess.board.squares.Square;
import view.Window;

public abstract class SpecialsMovements {

    // Movimento normal
    public static void normalMovement(){
        // Altera a posição da peça no Board e atualiza a imagem do tabuleiro.
        Piece piece = StateMachineController.instance.getSelectedPiece();
        piece.getSquare().setPiece(null);
        Window.instance.actualizeSquareRepresentation(piece.getSquare().getPosition()[0], piece.getSquare().getPosition()[1], true);
        piece.setSquare(Board.instance.getSquare(StateMachineController.instance.getSelectedHighlight()[0], StateMachineController.instance.getSelectedHighlight()[1]));
        
        // Come uma peça inimiga caso haja alguma na posição alvo.
        if (piece.getSquare().getPiece() != null){
            Piece deadPiece = piece.getSquare().getPiece();
            System.out.println("The " + deadPiece.getName() + " was dead!");
            deadPiece.getSquare().setPiece(null);
            Window.instance.actualizeSquareRepresentation(deadPiece.getSquare().getPosition()[0], deadPiece.getSquare().getPosition()[1], true);
            
            // Retirar ela da array List do jogador, e adicionar um imageLabel na Window das peças capturadas do inimigo.
            Board.instance.removeTeamPiece(deadPiece);

            // Atualiza a pontuação do jogador após comer uma peça inimiga.
            if (deadPiece instanceof Pawn)
                piece.getPlayer().scoreChange(1);
            else if (deadPiece instanceof Knight || deadPiece instanceof Bishop)
                piece.getPlayer().scoreChange(3);
            else if (deadPiece instanceof Rook)
                piece.getPlayer().scoreChange(5);
            else if (deadPiece instanceof Queen)
                piece.getPlayer().scoreChange(9);

            String player = (piece.getPlayer() == StateMachineController.instance.getPlayer1()) ? "Player1" : "Player2";
            Window.instance.actualizePlayerScore(player, piece.getPlayer().getScore());
        }

        // Movimenta a peça para a posição alvo.
        piece.getSquare().setPiece(piece);
        piece.setWasMoved();
        Window.instance.actualizeSquareRepresentation(piece.getSquare().getPosition()[0], piece.getSquare().getPosition()[1], true);
    }



    // Movimento que altera as posições do rei e da torre
    public static void castlingMovement(){
        Piece king = StateMachineController.instance.getSelectedPiece();
        Piece rook = Board.instance.getSquare(StateMachineController.instance.getSelectedHighlight()[0], StateMachineController.instance.getSelectedHighlight()[1]).getPiece();

        // Retirar as peças do tabuleiro e atualizar a imagem
        king.getSquare().setPiece(null);
        rook.getSquare().setPiece(null);
        Window.instance.actualizeSquareRepresentation(king.getSquare().getPosition()[0], king.getSquare().getPosition()[1], true);
        Window.instance.actualizeSquareRepresentation(rook.getSquare().getPosition()[0], rook.getSquare().getPosition()[1], true);

        // Colocar as peças nas novas posições e atualizar a imagem
        int direction = rook.getSquare().getPosition()[1] - king.getSquare().getPosition()[1];
        if (direction > 0){
            king.setSquare(Board.instance.getSquare(king.getSquare().getPosition()[0], king.getSquare().getPosition()[1]+2));
            rook.setSquare(Board.instance.getSquare(king.getSquare().getPosition()[0], king.getSquare().getPosition()[1]-1));
        }
        else{
            king.setSquare(Board.instance.getSquare(king.getSquare().getPosition()[0], king.getSquare().getPosition()[1]-2));
            rook.setSquare(Board.instance.getSquare(king.getSquare().getPosition()[0], king.getSquare().getPosition()[1]+1));
        }
        king.getSquare().setPiece(king);
        rook.getSquare().setPiece(rook);
        Window.instance.actualizeSquareRepresentation(king.getSquare().getPosition()[0], king.getSquare().getPosition()[1], true);
        Window.instance.actualizeSquareRepresentation(rook.getSquare().getPosition()[0], rook.getSquare().getPosition()[1], true);

        king.setWasMoved();
        rook.setWasMoved();
    }



    // Movimento de promoção de peão
    public static void pawnPromotion(){

        normalMovement();
        
        // Remover o peão do jogo
        Piece pawn = StateMachineController.instance.getSelectedPiece();
        pawn.getSquare().setPiece(null);
        Window.instance.actualizeSquareRepresentation(pawn.getSquare().getPosition()[0], pawn.getSquare().getPosition()[1], true);
        Board.instance.removeTeamPiece(pawn);

        // Cria uma nova peça rainha, adiciona ela no tabuleiro e atualiza a imagem
        Square highlightSquare = Board.instance.getSquare(StateMachineController.instance.getSelectedHighlight()[0], StateMachineController.instance.getSelectedHighlight()[1]);
        Piece queen = new Queen(StateMachineController.instance.getCurrentPlayer(), highlightSquare);
        Board.instance.addTeamPiece(StateMachineController.instance.getCurrentPlayer().getTeam(), queen);
        queen.getSquare().setPiece(queen);
        Window.instance.actualizeSquareRepresentation(queen.getSquare().getPosition()[0], queen.getSquare().getPosition()[1], true);
    }



    // Movimento de pulo duplo do peão
    public static void pawnDoubleMovement(){
        Piece pawn = StateMachineController.instance.getSelectedPiece();

        // Obter direção no eixo Y no peão e atualizando o moveType do próximo square para EnPassant
        int yDirection = (StateMachineController.instance.getCurrentPlayer().getTeam() == "WhiteTeam") ? -1 : 1;
        Board.instance.getSquare(pawn.getSquare().getPosition()[0] + yDirection, pawn.getSquare().getPosition()[1]).setMoveType(MoveType.EnPassantMovement);
        
        normalMovement();
    }



    // Movimento de comer um peão EnPassant
    public static void enPassantMovement(){

        normalMovement();

        // Obtendo o square que se encontra o peão inimigo que deu pulo duplo
        int yEnemyPawnDirection = (StateMachineController.instance.getCurrentPlayer().getTeam() == "WhiteTeam") ? 1 : -1;
        Square enemyPawnSquare = Board.instance.getSquare(StateMachineController.instance.getSelectedHighlight()[0] + yEnemyPawnDirection, StateMachineController.instance.getSelectedHighlight()[1]);

        // Remove alguma peça inimiga que estivesse naquela posição
        if (enemyPawnSquare.getPiece() != null){
            Piece deadPiece = enemyPawnSquare.getPiece();
            System.out.println("The " + deadPiece.getName() + " was dead!");
            deadPiece.getSquare().setPiece(null);
            Window.instance.actualizeSquareRepresentation(deadPiece.getSquare().getPosition()[0], deadPiece.getSquare().getPosition()[1], true);
            
            // Retirar ela da array List do jogador, e adicionar um imageLabel na Window das peças capturadas do inimigo.
            Board.instance.removeTeamPiece(deadPiece);
        }
    }
}
