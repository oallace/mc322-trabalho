package mc322.lab07;

import mc322.lab07.controller.StateMachineController;
import mc322.lab07.controller.state.LoadState;
import mc322.lab07.model.Board;
import mc322.lab07.view.Window;

public class AppChess {
	
	public static void main(String args[]) {
		
		// Model
		Board board = new Board();
		
		// Controller
		StateMachineController machine = new StateMachineController("Oallace", "Cabeçudo");
		machine.changeTo(new LoadState());
		
		// View
		Window window = new Window();
		
		machine.startGame();
	}
}
