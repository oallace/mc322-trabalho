package effects;

import view.Window;

import java.util.ArrayList;

public class EffectMachineController implements IEffects{
    Effect activeEffects[][];
    ArrayList<int[]> changes; // guarda mudanças que precisam ser informadas
    public static IEffects instance; // Interface para acesso global à componente

    public  EffectMachineController()
    {
        activeEffects = new Effect[8][8];
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                activeEffects[i][j] = null;
        changes = new ArrayList<int[]>();
        instance = this;
    }


    @Override
    public boolean freezeSquare(int iPos, int jPos) {
        if (iPos > 0 && iPos < 8 && jPos > 0 && jPos < 8)
        {
            if (activeEffects[iPos][jPos] == null) {
                activeEffects[iPos][jPos] = new FreezingEffect(iPos, jPos);
                changes.add(activeEffects[iPos][jPos].getSquarePosition());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isFrozen(int iPos, int jPos)
    {
        return (activeEffects[iPos][jPos] instanceof FreezingEffect);
    }

    @Override
    public boolean createWall(int iPos, int jPos) {
        if (iPos > 0 && iPos < 8 && jPos > 0 && jPos < 8)
        {
            if (activeEffects[iPos][jPos] == null) {
                activeEffects[iPos][jPos] = new WallEffect(iPos, jPos);
                changes.add(activeEffects[iPos][jPos].getSquarePosition());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isWall(int iPos, int jPos)
    {
        return (activeEffects[iPos][jPos] instanceof WallEffect);
    }

    @Override
    public String getEffectName(int iPos, int jPos) {
        if (activeEffects[iPos][jPos] != null)
            return activeEffects[iPos][jPos].getName();
        return null;
    }

    @Override
    public void passShift()
    {
        for(int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
            {
                if(activeEffects[i][j] != null)
                    if(activeEffects[i][j].discountsShift()) //é true quando a duração do efeito termina
                    {
                        changes.add(activeEffects[i][j].getSquarePosition());
                        activeEffects[i][j] = null;
                    }
            }

        for (int i = 0; i < changes.size(); i++)
            Window.instance.actualizeSquareRepresentation(changes.get(i)[0], changes.get(i)[1], false);
        changes.clear();

    }
}
