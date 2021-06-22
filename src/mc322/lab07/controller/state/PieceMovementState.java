package mc322.lab07.controller.state;

import mc322.lab07.controller.StateMachineController;
import mc322.lab07.controller.movement.MoveType;
import mc322.lab07.controller.movement.SpecialsMovements;
import mc322.lab07.model.Board;
import mc322.lab07.model.pieces.Pawn;
import mc322.lab07.model.squares.Square;

public class PieceMovementState extends State{   // Movimenta a peça e inicia o estado de fim de turno.

    public void enter(){
        System.out.println("PieceMovementState:");

        // Após 1 rodada, transforma os moveType EnPassantMovement em NormalMovement.
        int iPos = (StateMachineController.instance.getCurrentPlayer().getTeam() == "WhiteTeam") ? 5 : 2;
        clearEnPassants(iPos);

        Square highlightedSquare = Board.instance.getSquare(StateMachineController.instance.getSelectedHighlight()[0], StateMachineController.instance.getSelectedHighlight()[1]);
        MoveType moveType = highlightedSquare.getMoveType();

        // Analisa qual será o tipo de movimento e executa o movimento necessário.
        if (moveType == MoveType.CastlingMovement)
            SpecialsMovements.castlingMovement();
        
        else if (moveType == MoveType.PawnPromotionMovement)
            SpecialsMovements.pawnPromotion();
        
        else if (moveType == MoveType.PawnDoubleMovement)
            SpecialsMovements.pawnDoubleMovement();
        
        else if (moveType == MoveType.EnPassantMovement && StateMachineController.instance.getSelectedPiece() instanceof Pawn)
            SpecialsMovements.enPassantMovement();
        
        else
            SpecialsMovements.normalMovement();

        
        highlightedSquare.setMoveType(MoveType.NormalMovement);
        
        StateMachineController.instance.changeTo(new TurnEndState());
    }



    // Percorre os Squares do jogador atual que possui algum EnPassant moveType e transforma em NormalMovement moveType
    void clearEnPassants(int iPos){
        for (int jPos = 0; jPos < 8; jPos++){
            Board.instance.getSquare(iPos, jPos).setMoveType(MoveType.NormalMovement);
        }
    }
}
