package mc322.lab07.model.pieces;

import mc322.lab07.controller.movement.BishopMovement;
import mc322.lab07.model.Player;
import mc322.lab07.model.squares.Square;

public class Bishop extends Piece{
	
	public Bishop(Player player, Square square){
		super(player, square);
		this.movement = new BishopMovement();
		name = player.getTeam().substring(0, 1) + "Bishop";
	}
}
