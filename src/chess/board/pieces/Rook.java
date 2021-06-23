package chess.board.pieces;


import chess.board.movement.RookMovement;
import chess.board.squares.Square;
import chess.player.Player;

public class Rook extends Piece {
	
	public Rook(Player player, Square square){
		super(player, square);
		this.movement = new RookMovement();
		// falta decidir com base no jogador:
		name = player.getTeam().substring(0, 1)+"Rook";
	}
}