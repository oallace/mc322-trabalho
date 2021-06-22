package mc322.lab07.model;

import java.util.ArrayList;

import mc322.lab07.model.pieces.Piece;
import mc322.lab07.model.squares.Square;

public class Board {

	public static Board instance;  // Instancia estática para acessar o board através do Controller e do View

	private Square board[][];      // Matriz de Squares (quadrados/blocos) do tabuleiro, os quais guardam as peças.
	
	private ArrayList<Piece> whitePieces;
	
	private ArrayList<Piece> blackPieces;
	
	
	public Board(){
		instance = this;
		board = new Square[8][8];
		whitePieces = new ArrayList<>();
		blackPieces = new ArrayList<>();
		
		// Inicializa os Squares. As peças são adicionadas ao tabuleiro pelo LoadState no Controller.
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Square square = new Square(i, j);
				board[i][j] = square;
			}
		}
	}
	
	
	public Square getSquare(int i, int j) {
		if (i>=0 && i<=7 && j>=0 && j<=7){
			return this.board[i][j];
		}
		return null;
	}


	public String getPieceName(int i, int j)
	{
		if (board[i][j].getPiece() != null)
			return board[i][j].getPiece().getName();
		return null;
	}
	
	
	public ArrayList<Piece> getTeamPieces(String team){
		if (team == "WhiteTeam") {
			return this.whitePieces;
		}
		return this.blackPieces;
	}
	
	// Adiciona uma peça ao ArrayList do time
	public void addTeamPiece(String team, Piece piece) {
		if (piece != null){
			if (team == "WhiteTeam") {
				this.whitePieces.add(piece);
			}
			else {
				this.blackPieces.add(piece);
			}
		}
	}

	// Remove uma peça do ArrayList do time
	public void removeTeamPiece(Piece piece){
		if (piece != null){
			ArrayList<Piece> teamPieces = Board.instance.getTeamPieces(piece.getPlayer().getTeam());

			for (int i = 0; i < teamPieces.size(); i++){
				if (teamPieces.get(i) == piece){
					teamPieces.remove(i);
					break;
				}
			}
		}
	}
}
