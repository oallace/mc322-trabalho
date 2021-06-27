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

//		Testando efeitos:
		effectsMachine.createWall(0, 1);
		effectsMachine.passShift();
		ISkill stoneNation = new StoneNation();
		stoneNation.basicSkill(5, 4);
		stoneNation.mainSkill(4, 6);
		ISkill iceNation = new IceNation();
		iceNation.mainSkill(2,5);
		effectsMachine.passShift();


		machine.startGame();
	}
}
