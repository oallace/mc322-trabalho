package chess.state;


import chess.StateMachineController;
import chess.board.Board;
import chess.board.pieces.*;

public class LoadState extends State{       // Instancia as peças no tabuleiro. (na matriz board e nos Arraylist)
	
	public void enter() {                        
		System.out.println("LoadState:");
		
		// Instancia os Peões
		for (int j = 0; j < 8; j++) {
			Piece blackPawn = new Pawn(StateMachineController.instance.getPlayer2(), Board.instance.getSquare(1, j));
			Piece whitePawn = new Pawn(StateMachineController.instance.getPlayer1(), Board.instance.getSquare(6, j));

			Board.instance.getSquare(1, j).setPiece(blackPawn);
			Board.instance.getSquare(6, j).setPiece(whitePawn);
			Board.instance.addTeamPiece("BlackTeam", blackPawn);
			Board.instance.addTeamPiece("WhiteTeam", whitePawn);
		}

		// Instancia as demais peças pretas
		Piece blackRook1 = new Rook(StateMachineController.instance.getPlayer2(), Board.instance.getSquare(0, 0));
		Piece blackKnight1 = new Knight(StateMachineController.instance.getPlayer2(), Board.instance.getSquare(0, 1));
		Piece blackBishop1 = new Bishop(StateMachineController.instance.getPlayer2(), Board.instance.getSquare(0, 2));
		Piece blackQueen = new Queen(StateMachineController.instance.getPlayer2(), Board.instance.getSquare(0, 3));
		Piece blackKing = new King(StateMachineController.instance.getPlayer2(), Board.instance.getSquare(0, 4));
		Piece blackBishop2 = new Bishop(StateMachineController.instance.getPlayer2(), Board.instance.getSquare(0, 5));
		Piece blackKnight2 = new Knight(StateMachineController.instance.getPlayer2(), Board.instance.getSquare(0, 6));
		Piece blackRook2 = new Rook(StateMachineController.instance.getPlayer2(), Board.instance.getSquare(0, 7));

		Board.instance.getSquare(0, 0).setPiece(blackRook1);
		Board.instance.getSquare(0, 1).setPiece(blackKnight1);
		Board.instance.getSquare(0, 2).setPiece(blackBishop1);
		Board.instance.getSquare(0, 3).setPiece(blackQueen);
		Board.instance.getSquare(0, 4).setPiece(blackKing);
		Board.instance.getSquare(0, 5).setPiece(blackBishop2);
		Board.instance.getSquare(0, 6).setPiece(blackKnight2);
		Board.instance.getSquare(0, 7).setPiece(blackRook2);

		// Instancia as demais peças brancas
		Piece whiteRook1 = new Rook(StateMachineController.instance.getPlayer1(), Board.instance.getSquare(7, 0));
		Piece whiteKnight1 = new Knight(StateMachineController.instance.getPlayer1(), Board.instance.getSquare(7, 1));
		Piece whiteBishop1 = new Bishop(StateMachineController.instance.getPlayer1(), Board.instance.getSquare(7, 2));
		Piece whiteQueen = new Queen(StateMachineController.instance.getPlayer1(), Board.instance.getSquare(7, 3));
		Piece whiteKing = new King(StateMachineController.instance.getPlayer1(), Board.instance.getSquare(7, 4));
		Piece whiteBishop2 = new Bishop(StateMachineController.instance.getPlayer1(), Board.instance.getSquare(7, 5));
		Piece whiteKnight2 = new Knight(StateMachineController.instance.getPlayer1(), Board.instance.getSquare(7, 6));
		Piece whiteRook2 = new Rook(StateMachineController.instance.getPlayer1(), Board.instance.getSquare(7, 7));
		
		Board.instance.getSquare(7, 0).setPiece(whiteRook1);
		Board.instance.getSquare(7, 1).setPiece(whiteKnight1);
		Board.instance.getSquare(7, 2).setPiece(whiteBishop1);
		Board.instance.getSquare(7, 3).setPiece(whiteQueen);
		Board.instance.getSquare(7, 4).setPiece(whiteKing);
		Board.instance.getSquare(7, 5).setPiece(whiteBishop2);
		Board.instance.getSquare(7, 6).setPiece(whiteKnight2);
		Board.instance.getSquare(7, 7).setPiece(whiteRook2);

		// Adiciona as demais peças nos ArrayList
		Board.instance.addTeamPiece("BlackTeam", blackRook1);
		Board.instance.addTeamPiece("WhiteTeam", whiteRook1);
		Board.instance.addTeamPiece("BlackTeam", blackKnight1);
		Board.instance.addTeamPiece("WhiteTeam", whiteKnight1);
		Board.instance.addTeamPiece("BlackTeam", blackBishop1);
		Board.instance.addTeamPiece("WhiteTeam", whiteBishop1);
		Board.instance.addTeamPiece("BlackTeam", blackQueen);
		Board.instance.addTeamPiece("WhiteTeam", whiteQueen);
		Board.instance.addTeamPiece("BlackTeam", blackKing);
		Board.instance.addTeamPiece("WhiteTeam", whiteKing);
		Board.instance.addTeamPiece("BlackTeam", blackBishop2);
		Board.instance.addTeamPiece("WhiteTeam", whiteBishop2);
		Board.instance.addTeamPiece("BlackTeam", blackKnight2);
		Board.instance.addTeamPiece("WhiteTeam", whiteKnight2);
		Board.instance.addTeamPiece("BlackTeam", blackRook2);
		Board.instance.addTeamPiece("WhiteTeam", whiteRook2);
		

		StateMachineController.instance.setCurrentPlayer(StateMachineController.instance.getPlayer2());
	}
}
