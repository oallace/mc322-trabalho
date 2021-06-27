package view;

import chess.StateMachineController;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainSkillButton extends JButton{
    private static final long serialVersionUID = 10;

    public MainSkillButton(String text, int iPos, int jPos, int width, int height){
        super();
        setSize(width, height);
        setLocation(iPos, jPos);
        setLayout(null);
        this.setBackground(new Color(128, 206, 225));
        this.add(new TextLabel(text, 7, 7, 25, 25, 25, 255, 255, 255));
		addActionListener(new MainButtonHandler());

        this.setBorderPainted(false);
		this.setFocusPainted(false);
    }

    // Classe que será chamada ao clicar no JPanel.
	private class MainButtonHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
            // Solicita o uso da habilidade básica do jogador atual
            StateMachineController.instance.changeToMainSkillSelectionState();
		}
	}

}
