package chess.board.pieces;


import chess.board.movement.QueenMovement;
import chess.board.squares.Square;
import chess.player.Player;

public class Queen extends Piece {
	
	public Queen(Player player, Square square){
		super(player, square);
		this.movement = new QueenMovement();
		name = player.getTeam().substring(0, 1)+"Queen";
	}
}
