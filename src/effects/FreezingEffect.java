package effects;

public class FreezingEffect extends Effect
{

    public FreezingEffect(int iPos, int jPos) {
        this(iPos, jPos, 2);
        name = "freezing";
    }

    public FreezingEffect(int iPos, int jPos, int duration) {
        super(iPos, jPos, duration);
    }
}
