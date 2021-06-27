package effects;

public interface IManageEffects {

    // Congela um Square, importante: só deve haver um efeito por square. Retorna se o congelamento foi realizado
    public boolean freezeSquare(int iPos, int jPos);

    // Levanta uma muralha sobre um Square
    public boolean createWall(int iPos, int jPos);

    // Desconta um turno de duração dos efeitos
    public void passShift();
}
