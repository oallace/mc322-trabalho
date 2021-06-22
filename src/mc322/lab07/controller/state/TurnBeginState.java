package mc322.lab07.controller.state;

import mc322.lab07.controller.StateMachineController;

public class TurnBeginState extends State{
	
	public void enter() {                         // Estado de escolha do jogador atual. No final muda para o estado de seleção de peça.
		System.out.println("TurnBeginState:");
		
		if (StateMachineController.instance.getCurrentPlayer() == StateMachineController.instance.getPlayer1())
			StateMachineController.instance.setCurrentPlayer(StateMachineController.instance.getPlayer2());
		else
			StateMachineController.instance.setCurrentPlayer(StateMachineController.instance.getPlayer1());
		
		
		System.out.println(StateMachineController.instance.getCurrentPlayer().getTeam() + " is playing now");
		StateMachineController.instance.changeTo(new PieceSelectionState());
	}
}
