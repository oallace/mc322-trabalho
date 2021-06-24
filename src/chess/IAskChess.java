package chess;

public interface IAskChess {
    public String getPlayerName(int playerNum);

    public String getPieceName(int iPos, int jPos);

    public boolean squareIsHighlighted(int iPos, int jPos);

    // Retorna True se há uma peça na posição [iPos, jPos]
    public boolean thereIsPiece(int iPos, int jPos);

    public  boolean isPieceSelectionState();

    public  boolean isMoveSelectionState();

    public int[] getSelectedPiecePosition();

    public boolean isCurrentPlayerPiece(int iPos, int jPos);
}
