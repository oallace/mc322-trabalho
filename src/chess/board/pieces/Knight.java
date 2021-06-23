package chess.board.pieces;


import chess.board.movement.KnightMovement;
import chess.board.squares.Square;
import chess.player.Player;

public class Knight extends Piece {
	
	public Knight(Player player, Square square){
		super(player, square);
		this.movement = new KnightMovement();
		name = player.getTeam().substring(0, 1)+"Knight";
	}
}
