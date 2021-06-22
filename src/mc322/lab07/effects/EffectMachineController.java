package mc322.lab07.effects;

import mc322.lab07.view.Window;

public class EffectMachineController implements IManageEffects{
    Effect activeEffects[][];
    Window window;  // mudar: fazer conexão por uma interface

    public  EffectMachineController()
    {
        activeEffects = new Effect[8][8];
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                activeEffects[i][j] = null;
        window = null;
    }

    public void connectWindow(Window window)
    {
        this.window = window;
    }

    @Override
    public boolean freezeSquare(int iPos, int jPos) {
        if (activeEffects[iPos][jPos] == null)
        {
            activeEffects[iPos][jPos] = new FreezingEffect(iPos, jPos);
            return true;
        }
        return false;
    }

    @Override
    public boolean isFrozen(int iPos, int jPos) {
        return false;
    }

    @Override
    public boolean createWall(int iPos, int jPos) {
        if (activeEffects[iPos][jPos] == null)
        {
            activeEffects[iPos][jPos] = new WallEffect(iPos, jPos);
            return true;
        }
        return false;
    }

    @Override
    public boolean isWall(int iPos, int jPos) {
        return false;
    }

    @Override
    public void passShift()
    {
        for(int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
            {
                if(activeEffects[i][j] != null)
                    if(activeEffects[i][j].discountsShift()) //é true quando a duração do efeito termina
                        removeRepresentation(i, j);
            }
    }

    @Override
    public void createRepresentation(Effect e, int iSquarePos, int jSquarePos) {

    }

    @Override
    public void removeRepresentation(int iSquarePos, int jSquarePos) {

    }
}
