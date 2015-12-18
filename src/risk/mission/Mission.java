package risk.mission;


import risk.GameState;
import risk.player.Player;

/**
 * Created by Federico on 17/12/2015.
 */
public interface Mission {

    boolean checkHitMission(GameState gameState, Player Player);
    String toString();
}
