package mc322.lab07.effects;

public class WallEffect extends Effect{
    public WallEffect(int iPos, int jPos) {
        this(iPos, jPos, 1);
    }

    public WallEffect(int iPos, int jPos, int duration) {
        super(iPos, jPos, duration);
    }
}
