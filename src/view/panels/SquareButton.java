package view.panels;

import chess.StateMachineController;
import chess.board.Board;
import chess.board.pieces.Piece;
import chess.state.MoveSelectionState;
import chess.state.PieceMovementState;
import chess.state.PieceSelectionState;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;
import javax.swing.JButton;
import javax.swing.SwingUtilities;



public class SquareButton extends JButton{

	@Serial
	private static final long serialVersionUID = 4934802567644563818L;

	private final int[] position;

	private final Component[] components; // Lista de Componentes que foram adicionados ao button: 0 : Pieces
                                    //                                                       1 : Highlights
	private String color;


	public SquareButton(int iPos, int jPos){
		super();
		position = new int[2];    // A posição será usada na clickFunction para obter as coordenadas do square clicado
		position[0] = iPos;
		position[1] = jPos;
		components = new Component[2]; // 0 : peça   1 : highlight
		addActionListener(new SquareButtonlHandler());

		// Estilo do botão:
		this.setBorderPainted(false);
		this.setFocusPainted(false);
	}


	public int[] getPosition() {
		return this.position;
	}

	public String getColor(){
		return this.color;
	}

	public void setColor(String color){
		this.color = color;
	}

	// Remove um componente do Button. idx é o índice daquele componente no vetor components
	public void removeImage(int idx){
		if (components[idx] != null)
		{
			this.remove(components[idx]);
			components[idx] = null;
		}
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	// Adiciona uma imagem ao Button
	public void addImage(int idx, String imagePath, int x, int y, int width, int height) {
		if (imagePath != null) {
			ImageLabel newImage = new ImageLabel(imagePath, x, y, width, height);
			this.add(newImage);
			components[idx] = newImage; // 0 se refere à imagem da peça.
		}
		SwingUtilities.updateComponentTreeUI(this);
	}
	

	// Classe que será chamada ao clicar no JPanel.
	private class SquareButtonlHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			// Função disparada quando clicamos sobre um SquareButton:
			System.out.println("Selected Square:  Row " + position[0] + " - Column "+ position[1]);

			if (StateMachineController.instance.getCurrentState() instanceof PieceSelectionState ||
				StateMachineController.instance.getCurrentState() instanceof MoveSelectionState){
					
					// Se está no estado de MoveSelection e clicou em um highlight
					if (Board.instance.getSquare(position[0], position[1]).getIsHighlighted()){
						StateMachineController.instance.setSelectedHighlight(position[0], position[1]);
						StateMachineController.instance.changeTo(new PieceMovementState());
					}
					
					// Se esta no stado de PieceSelection ou MoveSelection e clicou em uma peça
					else if (Board.instance.getSquare(position[0], position[1]).getPiece() != null){
						Piece selectedPiece = Board.instance.getSquare(position[0], position[1]).getPiece();
						
						// Se esta peça esta sendo clicada pela segunda vez consecutiva, retira os highlights dela
						if (selectedPiece  == StateMachineController.instance.getSelectedPiece()){
							StateMachineController.instance.setSelectedPiece(null);
							StateMachineController.instance.changeTo(new PieceSelectionState());
						}
						
						// Se é uma nova peça sendo clicada, vai para o MoveSelectionState para adicionar os highlights
						else if (selectedPiece != null && StateMachineController.instance.getCurrentPlayer() == selectedPiece.getPlayer()){
							StateMachineController.instance.setSelectedPiece(selectedPiece);
							StateMachineController.instance.changeTo(new MoveSelectionState());
						}
					}
					
					// Se clicar em um square vazio, volta para o estado de seleção de peça e retira qualquer highlight que estivesse marcado
					else{
						StateMachineController.instance.setSelectedPiece(null);
						StateMachineController.instance.changeTo(new PieceSelectionState());
					}
			}
		}
	}
}
