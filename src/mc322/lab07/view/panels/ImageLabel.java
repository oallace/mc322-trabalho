package mc322.lab07.view.panels;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.io.Serial;

public class ImageLabel extends JLabel{

	@Serial
	private static final long serialVersionUID = -1623857887826457114L;
	
	public ImageLabel(String imagePath, int iPos, int jPos, int width, int height){
		super();
		setSize(width, height);
		setLocation(iPos, jPos);
		ImageIcon image = new ImageIcon(getClass().getResource(imagePath));
		this.setIcon(image);
	}
}
