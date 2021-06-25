package effects;

public class WallEffect extends Effect{
    public WallEffect(int iPos, int jPos) {
        this(iPos, jPos, 2);
    }

    public WallEffect(int iPos, int jPos, int duration) {
        super(iPos, jPos, duration);
    }

    @Override
    public String getName() {
        return "wall";
    }
}
