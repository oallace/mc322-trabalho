package mc322.lab07.model.pieces;

import mc322.lab07.controller.movement.RookMovement;
import mc322.lab07.model.Player;
import mc322.lab07.model.squares.Square;

public class Rook extends Piece{
	
	public Rook(Player player, Square square){
		super(player, square);
		this.movement = new RookMovement();
		// falta decidir com base no jogador:
		name = player.getTeam().substring(0, 1)+"Rook";
	}
}