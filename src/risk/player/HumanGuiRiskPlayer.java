package risk.player;


import risk.GameMaster;
import risk.player.AbstractRiskPlayer;

/**
 * Created by DarkLinux on 12/11/15.
 */
public class HumanGuiRiskPlayer implements AbstractRiskPlayer {
    public String name;
    public Boolean is_bot;
    public Boolean is_eliminated;
    public int reserves;


    public HumanGuiRiskPlayer(String name) {
        this.name = name;
        this.is_bot = false;
        this.reserves = 0;
        this.is_eliminated=false;
    }


    public void eliminate_player(){
        this.is_eliminated=true;
    }

    public Boolean getIsEliminated(){
        return this.is_eliminated;
    }


    @Override
    public void reinforce(GameMaster gameMaster) {

    }

    @Override
    public void attack(GameMaster gameMaster) {

    }

    @Override
    public void forify(GameMaster gameMaster) {

    }

    @Override
    public void choose_territory() {

    }

    @Override
    public void deploy_reserve(GameMaster gameMaster) {

    }
}
