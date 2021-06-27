import chess.StateMachineController;
import chess.board.Board;
import chess.player.nation.ISkill;
import chess.player.nation.IceNation;
import chess.player.nation.StoneNation;
import chess.state.LoadState;
import effects.EffectMachineController;
import effects.IManageEffects;
import view.Window;

public class AppChess {
	
	public static void main(String args[]) {
		
		// Model
		Board board = new Board();
		
		// Controller
		StateMachineController machine = new StateMachineController("Oallace", "Cabeçudo");
		machine.changeTo(new LoadState());

		IManageEffects effectsMachine = new EffectMachineController();

		// View
		Window window = new Window(machine);


		machine.startGame();
	}
}
