package chess.state;


import chess.StateMachineController;

public class PieceSelectionState extends State{
	
	public void enter() {                            // Seleção de peça. Nada ocorre até o jogador clicar em uma peça e a máquina alterar 
		System.out.println("PieceSelectionState:");  // o estado para o estado de seleção de movimento.
		
	}

	public void exit(){
		if (StateMachineController.instance.getSelectedPiece() != null)
			System.out.println("Selected Piece : " + StateMachineController.instance.getSelectedPiece().getName());
	}
	
}
