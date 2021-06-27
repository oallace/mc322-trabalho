package chess.player.nation;

import effects.EffectMachineController;

public class StoneNation implements ISkill
{

    @Override
    public void basicSkill(int iTarget, int jTarget)
    {
        EffectMachineController.instance.createWall(iTarget, jTarget);
    }

    @Override
    public void mainSkill(int iTarget, int jTarget)
    {
        for (int i = -1; i <= 1; i++)
            for (int j = -1; j <= 1; j++)
                if (!(i == j & j == 0))
                    EffectMachineController.instance.createWall(iTarget + i, jTarget + j);
    }
}
