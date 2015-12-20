package risk.mission;


import risk.GameState;

import java.awt.*;


/**
 * Created by Federico on 17/12/2015.
 */
public interface Mission {

    boolean checkHitMission(int playerId,Color playerColor, GameState gameState);
    String toString();

}
