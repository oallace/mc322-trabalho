package chess;

public interface IOperateChess {
    public void setSelectedHighlight(int iPos, int jPos);

    public void setSelectedPiecePosition(int [] position);

    public void changeToPieceMovementState();

    public void  changeToPieceSelectionState();

    public void changeToMoveSelectionState();
}
