package view;

import chess.StateMachineController;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BasicSkillButton extends JButton{
    private static final long serialVersionUID = 10;

    public BasicSkillButton(String text, int iPos, int jPos, int width, int height){
        super();
        setSize(width, height);
        setLocation(iPos, jPos);
        setLayout(null);
        this.setBackground(new Color(255, 105, 97));
        this.add(new TextLabel(text, 7, 7, 25, 25, 25, 255, 255, 255));
        addActionListener(new BasicButtonHandler());

        this.setBorderPainted(false);
		this.setFocusPainted(false);
    }

	private class BasicButtonHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
            // Solicita o uso da habilidade básica do jogador atual
            StateMachineController.instance.changeToBasicSkillSelectionState();
		}
	}
}
