package chess.board.squares;


import chess.board.movement.MoveType;
import chess.board.pieces.Piece;

public class Square {
	
	private int position[];
	
	private Piece piece;

	private boolean isHighlighted; // Indica se o square está marcado com movimento de paça.

	private MoveType moveType;  // Indica se um movimento de uma peça para este Square será normal ou algum movimento especial
	
	// Haverá mais informação, como se este square está congelado.
	
	public Square(int iPos, int jPos){
		position = new int[2];
		position[0] = iPos;
		position[1] = jPos;
		piece = null;
		isHighlighted = false;
		moveType = MoveType.NormalMovement;
	}
	
	
	public int[] getPosition(){
		return this.position;
	}
	
	public Piece getPiece() {
		return this.piece;
	}
	
	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	public boolean getIsHighlighted(){
		return this.isHighlighted;
	}

	public void setIsHighlighted(boolean bool){
		this.isHighlighted = bool;
	}

	public MoveType getMoveType(){
		return moveType;
	}

	public void setMoveType(MoveType movetype){
		this.moveType = movetype;
	}
}
