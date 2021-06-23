package chess.state;


import chess.StateMachineController;

public class TurnEndState extends State{

    public void enter(){
        System.out.println("TurnEndState:");

        boolean gameFinished = checkKing();

        if (gameFinished){
            StateMachineController.instance.changeTo(new GameEndState());
        }
        else{
            StateMachineController.instance.changeTo(new TurnBeginState());
        }
    }


    public boolean checkKing(){
        return false;
    }

}
