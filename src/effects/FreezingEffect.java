package effects;

public class FreezingEffect extends Effect
{

    public FreezingEffect(int iPos, int jPos) {
        this(iPos, jPos, 2);
    }

    public FreezingEffect(int iPos, int jPos, int duration) {
        super(iPos, jPos, duration);
    }

    @Override
    public String getName() {
        return "freezing";
    }
}
