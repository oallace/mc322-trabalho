package chess.board.movement;

public enum MoveType {     // Após obter os movimentos válidos para uma dada peça com as classes que herdam Movement, todos os squares
                           // desses movimentos tem seu atributo moveType atualizado para NormalMovement. Após isso, caso haja algum
    CastlingMovement,      // movimento especial para algum desses squares, o atributo moveType é alterado para este movimento especial.

    PawnPromotionMovement,

    PawnDoubleMovement,

    EnPassantMovement,

    NormalMovement;
}
