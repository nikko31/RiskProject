package risk.mission;

import risk.GameState;
import risk.board.Territory;
import risk.player.Player;

import java.awt.*;
import java.util.Map;

/**
 * Created by Federico on 17/12/2015.
 */
public class MissionDefeatPlayer implements Mission {
    private Color playerToDef;

    public MissionDefeatPlayer(Color playerToDef){
        this.playerToDef = playerToDef;
    }

    @Override
    public boolean checkHitMission(GameState gameState, Player player) {
        return false;
    }

    @Override
    public String toString() {
        return "you have to defeat: " + " " + this.playerToDef.toString() + " if is your color or someone defeat him," +
                " you have to conquer 24 territories ";
    }
}