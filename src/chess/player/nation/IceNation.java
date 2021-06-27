package chess.player.nation;


import effects.EffectMachineController;

public class IceNation implements ISkill{

    @Override
    public void basicSkill(int iTarget, int jTarget)
    {
        EffectMachineController.instance.freezeSquare(iTarget, jTarget);
    }

    @Override
    public void mainSkill(int iTarget, int jTarget) {
        for (int i = -1; i <= 1; i++)
            for (int j = -1; j <= 1; j++)
                EffectMachineController.instance.freezeSquare(iTarget + i, jTarget + j);

    }
}
