package mc322.lab07.effects;

public class Effect {
    private int[] squarePosition; // posição do Square sob o efeito
    private int duration; // quantos turnos há de duração


    public Effect(int iPos, int jPos, int duration){
        squarePosition = new int[2];
        squarePosition[0] = iPos;
        squarePosition[1] = jPos;
        this.duration = duration;
    }

    // desconta um turno de duração do efeito. Se a duração do efeito zerar, retorna True
    public boolean discountsShift(){
        if (--duration > 0)
            return false;
        else
            return true;
    }
}
