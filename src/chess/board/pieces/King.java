package chess.board.pieces;


import chess.board.movement.KingMovement;
import chess.board.squares.Square;
import chess.player.Player;

public class King extends Piece {
	
	public King(Player player, Square square){
		super(player, square);
		this.movement = new KingMovement();
		name = player.getTeam().substring(0, 1)+"King";
	}
}
