package effects;

public interface IAskEffects
{
    // Checa se um Square está congelado
    public boolean isFrozen(int iPos, int jPos);

    // Checa se um Square está bloqueado por uma muralha
    public boolean isWall(int iPos, int jPos);

    // Retorna o nome do efeito sobre o Square
    public String getEffectName(int iPos, int jPos);
}
