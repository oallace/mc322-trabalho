package mc322.lab07.model.pieces;

import mc322.lab07.controller.movement.Movement;
import mc322.lab07.model.Player;
import mc322.lab07.model.squares.Square;

public abstract class Piece {

	protected Player player;

	protected Movement movement;  // Movement são os movimentos que determinada peça pode realizar.

	protected Square square;

	protected boolean wasMoved;
	
	protected String name;

	public Piece(Player player, Square square) {
		this.player = player;
		this.square = square;
		this.wasMoved = false;
	}

	public Player getPlayer() {
		return this.player;
	}

	public Movement getMovement() {
		return this.movement;
	}

	public Square getSquare() {
		return this.square;
	}

	public void setSquare(Square square) {
		this.square = square;
	}

	public boolean getWasMoved() {
		return this.wasMoved;
	}

	public void setWasMoved() {
		this.wasMoved = true;
	}

	public String getName()
	{
		return this.name;
	}
}
