package chess;

public interface IOperateChess {
    public void setSelectedHighlight(int iPos, int jPos);

    public void setSelectedPiecePosition(int [] position);

    public void changeToPieceMovementState();

    public void  changeToPieceSelectionState();

    public void changeToMoveSelectionState();

    public void changeToBasicSkillSelectionState();

    public void changeToMainSkillSelectionState();

    public void requestSkill(int iTarget, int jTarget);
}
