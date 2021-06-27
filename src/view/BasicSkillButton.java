package view;

import javax.swing.JButton;
import java.awt.Color;

public class BasicSkillButton extends JButton{
    private static final long serialVersionUID = 10;

    public BasicSkillButton(String text, int iPos, int jPos, int width, int height){
        super();
        setSize(width, height);
        setLocation(iPos, jPos);
        setLayout(null);
        this.setBackground(new Color(255, 105, 97));
        this.add(new TextLabel(text, 7, 7, 25, 25, 25, 255, 255, 255));

        this.setBorderPainted(false);
		this.setFocusPainted(false);
    }
}
