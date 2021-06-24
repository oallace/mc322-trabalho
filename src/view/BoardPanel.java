package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.Serial;
import javax.swing.JPanel;


public class BoardPanel extends JPanel{
	
	@Serial
	private static final long serialVersionUID = 5792445905048410976L;
	
	public BoardPanel(){
		super();
		
		setSize(650, 650);
		setLocation(20, 65);
		setLayout(new GridLayout(8, 8));
		setBackground(Color.DARK_GRAY);
		int aux = 0;
		
		// Instancia os Paineis que representam os squares dentro do BoardPanel
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				SquareButton squareButton = new SquareButton(i, j);
				squareButton.setLayout(null);
				if (aux == 0) {
					squareButton.setBackground(new Color(244, 241, 214)); //244, 241, 214
					squareButton.setColor("beige");
				}
				else {
					squareButton.setBackground(new Color(128, 165, 91));  //128, 165, 91
					squareButton.setColor("green");
				}
				
				if (i == 7) {
					if (aux == 0)
						squareButton.add(new TextLabel(Character.toString((char)('a' + j)), 65, 60, 15, 18, 19, 128, 165, 91));
					else
						squareButton.add(new TextLabel(Character.toString((char)((int)'a' + j)), 65, 60, 15, 19, 18, 244, 241, 214));
				}
				if (j == 0) {
					if (aux == 0)
						squareButton.add(new TextLabel(Integer.toString(8 - i), 4, 4, 18, 18, 19, 128, 165, 91));
					else
						squareButton.add(new TextLabel(Integer.toString(8 - i), 4, 4, 18, 18, 19, 244, 241, 214));
				}

				this.add(squareButton);
				Window.instance.setSquareButton(squareButton, i, j);
				Window.instance.actualizeSquareRepresentation(i, j, true);
				aux = (aux == 1) ? 0:1;
			}
			
			aux = (aux == 1) ? 0:1;
		}
	}
}
