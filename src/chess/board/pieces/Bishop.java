package chess.board.pieces;


import chess.board.movement.BishopMovement;
import chess.board.squares.Square;
import chess.player.Player;

public class Bishop extends Piece {
	
	public Bishop(Player player, Square square){
		super(player, square);
		this.movement = new BishopMovement();
		name = player.getTeam().substring(0, 1) + "Bishop";
	}
}
