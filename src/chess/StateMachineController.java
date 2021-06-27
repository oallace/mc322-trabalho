package chess;


import chess.board.Board;
import chess.board.pieces.Piece;
import chess.player.Player;
import chess.player.nation.IceNation;
import chess.player.nation.StoneNation;
import chess.state.*;

public class StateMachineController implements IChess{   // A máquina de estados controla o fluxo do jogo alternando entre os estados.

    public static StateMachineController instance;  // Instancia estática para acessar o Controller através do Board e do View.

    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private State currentState;
    private Piece selectedPiece;    // Peça selecionada no estado PieceSelectionState
    private int[] selectedHighlight;  // Highlight selecionado no estado MoveSelectionState


    public StateMachineController() {
        instance = this;
        player1 = new Player("WhiteTeam", 0, "Player1", new IceNation());
        player2 = new Player("BlackTeam", 0, "Player2", new StoneNation());
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

    public Piece getSelectedPiece() {
        return this.selectedPiece;
    }

    @Override
    public String getPlayerName(int playerNum) {
        switch (playerNum){
            case 1:
                return player1.getName();
            case 2:
                return player2.getName();
            default:
                return null;
        }
    }

    @Override
    public String getPieceName(int iPos, int jPos) {
        return Board.instance.getPieceName(iPos, jPos);
    }

    @Override
    public boolean squareIsHighlighted(int iPos, int jPos) {
        return Board.instance.getSquare(iPos, jPos).getIsHighlighted();
    }

    @Override
    public boolean thereIsPiece(int iPos, int jPos) {
        if (Board.instance.getPiece(iPos, jPos) != null)
            return true;
        return false;
    }

    @Override
    public boolean isPieceSelectionState() {
        if (currentState instanceof PieceSelectionState)
            return true;
        else
            return false;
    }

    @Override
    public boolean isMoveSelectionState() {
        if (currentState instanceof MoveSelectionState)
            return true;
        else
            return false;
    }

    @Override
    public boolean isSkillSelectionState() {
//        Adicionar MainSkillSelectionState
        return  currentState instanceof BasicSkillSelectionState || currentState instanceof  MainSkillSelectionState;
    }

    @Override
    public int[] getSelectedPiecePosition(){
        if (getSelectedPiece() != null)
            return getSelectedPiece().getSquare().getPosition();
        else
            return  null;
    }

    @Override
    public boolean isCurrentPlayerPiece(int iPos, int jPos) {
        return Board.instance.getPiece(iPos, jPos).getPlayer() == currentPlayer;
    }

    @Override
    public void setSelectedPiecePosition(int[] piecePosition) {
        if (piecePosition == null)
        {
            this.selectedPiece = null;
        }
        else
        {
            this.selectedPiece = Board.instance.getPiece(piecePosition[0], piecePosition[1]);
        }
    }

    @Override
    public void changeToPieceMovementState() {
        changeTo(new PieceMovementState());
    }

    @Override
    public void changeToPieceSelectionState() {
        changeTo(new PieceSelectionState());
    }

    @Override
    public void changeToMoveSelectionState() {
        changeTo(new MoveSelectionState());
    }

    @Override
    public void changeToBasicSkillSelectionState()
    {
        // checa se há pontuação suficiente
        if (isSkillSelectionState())
            changeTo(new PieceSelectionState());
        else if (currentPlayer.getScore() >= 3)
        {
            changeTo(new BasicSkillSelectionState());
        }
    }

    @Override
    public void changeToMainSkillSelectionState()
    {
        // checa se há pontuação suficiente
        if (isSkillSelectionState())
            changeTo(new PieceSelectionState());
        else if (currentPlayer.getScore() >= 9)
        {
            changeTo(new MainSkillSelectionState());
        }
    }

    @Override
    public void requestSkill(int iTarget, int jTarget)
    {
        if (currentState instanceof  BasicSkillSelectionState)
            currentPlayer.basicSkill(iTarget, jTarget);
        else
            currentPlayer.mainSkill(iTarget, jTarget);
        changeTo(new TurnEndState());
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
