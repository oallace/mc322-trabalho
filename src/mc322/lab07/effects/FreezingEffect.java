package mc322.lab07.effects;

public class FreezingEffect extends Effect
{

    public FreezingEffect(int iPos, int jPos) {
        this(iPos, jPos, 1);
    }

    public FreezingEffect(int iPos, int jPos, int duration) {
        super(iPos, jPos, duration);
    }
}
