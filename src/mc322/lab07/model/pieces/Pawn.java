package mc322.lab07.model.pieces;

import mc322.lab07.controller.movement.PawnMovement;
import mc322.lab07.model.Player;
import mc322.lab07.model.squares.Square;

public class Pawn extends Piece{
	
	public Pawn(Player player, Square square){
		super(player, square);
		this.movement = new PawnMovement();
		name = player.getTeam().substring(0, 1)+"Pawn";
	}
}
