package risk.mission;


import risk.GameState;
import java.awt.*;

public interface Mission {

    boolean checkHitMission(int playerId,Color playerColor, GameState gameState);
    String toString();

}
