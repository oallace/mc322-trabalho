package effects;

public class Effect {
    protected int[] squarePosition; // posição do Square sob o efeito
    protected int duration; // quantos turnos há de duração
    protected static String name = "default";


    public Effect(int iPos, int jPos, int duration){
        squarePosition = new int[2];
        squarePosition[0] = iPos;
        squarePosition[1] = jPos;
        this.duration = duration;
    }

    public int[] getSquarePosition(){return squarePosition;}

    public String getName(){return name;}

    // desconta um turno de duração do efeito. Se a duração do efeito zerar, retorna True
    public boolean discountsShift(){
        if (--duration > 0)
            return false;
        else
            return true;
    }
}
