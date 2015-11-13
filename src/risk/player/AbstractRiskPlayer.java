package risk.player;

import risk.GameMaster;

/**
 * Created by DarkLinux on 12/11/15.
 */
public interface AbstractRiskPlayer {
    public void reinforce(GameMaster gameMaster);

    public void attack(GameMaster gameMaster);

    public void forify(GameMaster gameMaster);

    public void choose_territory();//param: territori avviabili

    public void deploy_reserve(GameMaster gameMaster);

}
