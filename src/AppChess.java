import chess.StateMachineController;
import chess.board.Board;
import chess.state.LoadState;
import effects.EffectMachineController;
import effects.IManageEffects;
import view.Window;

public class AppChess {
	
	public static void main(String args[]) {
		
		// Model
		Board board = new Board();
		
		// Controller
		StateMachineController machine = new StateMachineController();
		machine.changeTo(new LoadState());

		IManageEffects effectsMachine = new EffectMachineController();

		// View
		Window window = new Window(machine);


		machine.startGame();
	}
}
