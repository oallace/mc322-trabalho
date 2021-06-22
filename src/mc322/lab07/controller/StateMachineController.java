package mc322.lab07.controller;

import mc322.lab07.controller.state.State;
import mc322.lab07.controller.state.TurnBeginState;
import mc322.lab07.model.Player;
import mc322.lab07.model.pieces.Piece;

public class StateMachineController {   // A máquina de estados controla o fluxo do jogo alternando entre os estados.

    public static StateMachineController instance;  // Instancia estática para acessar o Controller através do Board e do View.

    private Player player1;

    private Player player2;

    private Player currentPlayer;

    private State currentState;

    private Piece selectedPiece;      // Peça selecionada no estado PieceSelectionState

    private int[] selectedHighlight;  // Highlight selecionado no estado MoveSelectionState


    public StateMachineController(String namePlayer1, String namePlayer2) {
        instance = this;
        player1 = new Player("WhiteTeam", 0, namePlayer1);
        player2 = new Player("BlackTeam", 0, namePlayer2);
        currentState = null;
        selectedPiece = null;
        selectedHighlight = new int[2];
    }


    public Player getPlayer1() {
        return this.player1;
    }

    public Player getPlayer2() {
        return this.player2;
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
    }

    public State getCurrentState() {
        return this.currentState;
    }

    public Piece getSelectedPiece() {
        return this.selectedPiece;
    }

    public void setSelectedPiece(Piece piece) {
        this.selectedPiece = piece;
    }

    public int[] getSelectedHighlight() {
        return this.selectedHighlight;
    }

    public void setSelectedHighlight(int iPos, int jPos) {
        this.selectedHighlight[0] = iPos;
        this.selectedHighlight[1] = jPos;
    }


    // Muda o jogo do estado atual para um outro estado. Antes de iniciar o novo estado, executa a saída do estado anterior.
    public void changeTo(State state) {
        if (this.currentState != state) {

            if (this.currentState != null) {
                this.currentState.exit();
            }

            this.currentState = state;
            if (this.currentState != null) {
                this.currentState.enter();
            }
        }
    }

    // Inicia o jogo.
    public void startGame() {
        changeTo(new TurnBeginState());
    }
}
