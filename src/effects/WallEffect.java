package effects;

public class WallEffect extends Effect{
    public WallEffect(int iPos, int jPos) {
        this(iPos, jPos, 2);
        name = "wall";
    }

    public WallEffect(int iPos, int jPos, int duration) {
        super(iPos, jPos, duration);
    }
}
