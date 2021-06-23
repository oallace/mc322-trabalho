import chess.StateMachineController;
import chess.board.Board;
import chess.state.LoadState;
import view.Window;

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
