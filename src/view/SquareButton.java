package view;


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

			if (Window.instance.chess.isPieceSelectionState() ||
				Window.instance.chess.isMoveSelectionState()){
					
					// Se está no estado de MoveSelection e clicou em um highlight
					if (Window.instance.chess.squareIsHighlighted(position[0], position[1])){
						Window.instance.chess.setSelectedHighlight(position[0], position[1]);
						Window.instance.chess.changeToPieceMovementState();
					}
					
					// Se esta no estado de PieceSelection ou MoveSelection e clicou em uma peça
					else if (Window.instance.chess.thereIsPiece(position[0], position[1])){
						// Se esta peça esta sendo clicada pela segunda vez consecutiva, retira os highlights dela
						if (position  == Window.instance.chess.getSelectedPiecePosition()){
							Window.instance.chess.setSelectedPiecePosition(null);
							Window.instance.chess.changeToPieceSelectionState();
						}
						
						// Se é uma nova peça sendo clicada, vai para o MoveSelectionState para adicionar os highlights
						else if (Window.instance.chess.isCurrentPlayerPiece(position[0], position[1])){
							Window.instance.chess.setSelectedPiecePosition(position);
							Window.instance.chess.changeToMoveSelectionState();
						}
					}
					
					// Se clicar em um square vazio, volta para o estado de seleção de peça e retira qualquer highlight que estivesse marcado
					else{
						Window.instance.chess.setSelectedPiecePosition(null);
						Window.instance.chess.changeToPieceSelectionState();
					}
			}
		}
	}
}
