package mc322.lab07.model.pieces;

import mc322.lab07.controller.movement.KnightMovement;
import mc322.lab07.model.Player;
import mc322.lab07.model.squares.Square;

public class Knight extends Piece{
	
	public Knight(Player player, Square square){
		super(player, square);
		this.movement = new KnightMovement();
		name = player.getTeam().substring(0, 1)+"Knight";
	}
}
