package view;

import chess.IChess;
import effects.EffectMachineController;

import java.awt.Color;
import java.awt.Container;
import java.io.Serial;
import javax.swing.JFrame;


public class Window extends JFrame implements IManageRepresentation{
	
	@Serial
	private static final long serialVersionUID = 7446721714968740806L;

	public static Window instance;  // Para facilitar acesso externo.

	IChess chess;

	private final SquareButton[][] board;

	
	public Window(IChess chess){
		super();
		instance = this;
		board = new SquareButton[8][8];
		this.chess = chess;
			
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(710, 820);
		setLocationRelativeTo(null);
		setResizable(false);

		// Matriz que guarda os Squares dentro do BoardPanel.
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		contentPane.setBackground(Color.darkGray);
		
		// Instancia os componentes do jogo dentro do contentPane
		contentPane.add(new BoardPanel());
		contentPane.add(new TextLabel(chess.getPlayerName(1), 75, 10, 300, 20, 17, 255, 255, 255)); // solicitar nome para Player
		contentPane.add(new TextLabel(chess.getPlayerName(2), 75, 728, 300, 20, 17, 255, 255, 255));
		contentPane.add(new ImageLabel("./images/user.png", 20, 9, 45, 45)); // editar tamanho da imagem.
		contentPane.add(new ImageLabel("./images/user.png", 20, 727, 45, 45));
		
		setVisible(true);
	}
	
	
	public SquareButton getSquareButton(int iPos, int jPos) {
		return this.board[iPos][jPos];
	}
	
	public void setSquareButton(SquareButton square, int iPos, int jPos) {
		this.board[iPos][jPos] = square;
	}

	@Override
	// Atualiza a representação da peça, do highlight e dos efeitos, respectivamente, de um dado square do tabuleiro.
	public void actualizeSquareRepresentation(int iPos, int jPos, boolean attPiece){
		SquareButton squareButton = this.board[iPos][jPos];

		// Analisar a adição ou remoção de peça
		if (attPiece){
			// Adiciona uma peça
			if (chess.thereIsPiece(iPos, jPos)){
				String pieceName = chess.getPieceName(iPos, jPos);
				if (pieceName != null)
					squareButton.addImage(0, "./images/" +pieceName + ".png", 10, 15, 55, 55);
			}

			// Remove uma peça
			else{
				squareButton.removeImage(0);
			}
		}
			
		// Adiciona um highlight
		if (chess.squareIsHighlighted(iPos, jPos)){
			if (squareButton.getColor().equals("beige"))
				squareButton.addImage(1, "./images/circleFullBeige.png", 10, 15, 70, 70);
			else
				squareButton.addImage(1, "./images/circleFullGreen.png", 10, 15, 70, 70);
		}
		// Remove um highlight
		else{
			squareButton.removeImage(1);
		}

		// Atualizações de efeitos
		if(EffectMachineController.instance.getEffectName(iPos, jPos) != null)
		{
			squareButton.addImage(2, "./images/" + EffectMachineController.instance.getEffectName(iPos, jPos) + ".png",0, 0, 100, 100);
		}
		else
		{
			squareButton.removeImage(2);
		}
	}
}
