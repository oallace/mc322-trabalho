package mc322.lab07.model.pieces;

import mc322.lab07.controller.movement.KingMovement;
import mc322.lab07.model.Player;
import mc322.lab07.model.squares.Square;

public class King extends Piece{
	
	public King(Player player, Square square){
		super(player, square);
		this.movement = new KingMovement();
		name = player.getTeam().substring(0, 1)+"King";
	}
}
