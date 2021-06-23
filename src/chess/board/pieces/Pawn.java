package chess.board.pieces;


import chess.board.movement.PawnMovement;
import chess.board.squares.Square;
import chess.player.Player;

public class Pawn extends Piece {
	
	public Pawn(Player player, Square square){
		super(player, square);
		this.movement = new PawnMovement();
		name = player.getTeam().substring(0, 1)+"Pawn";
	}
}
