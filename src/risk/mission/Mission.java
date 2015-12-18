package risk.mission;


import risk.GameState;


/**
 * Created by Federico on 17/12/2015.
 */
public interface Mission {

    boolean checkHitMission(GameState gameState);
    String toString();

}
