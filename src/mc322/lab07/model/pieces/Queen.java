package mc322.lab07.model.pieces;

import mc322.lab07.controller.movement.QueenMovement;
import mc322.lab07.model.Player;
import mc322.lab07.model.squares.Square;

public class Queen extends Piece{
	
	public Queen(Player player, Square square){
		super(player, square);
		this.movement = new QueenMovement();
		name = player.getTeam().substring(0, 1)+"Queen";
	}
}
