package risk.mission;

import risk.GameState;
import risk.board.Territory;
import risk.player.Player;

import java.awt.*;


/**
 * Created by Federico on 17/12/2015.
 */
public class MissionTerritory implements Mission {
    private int terrTocon;
    private int numbTroopsForTerr;


    public MissionTerritory(int terrTocon,int numbTroopsForTerr){
        this.terrTocon = terrTocon;
        this.numbTroopsForTerr = numbTroopsForTerr;
    }

    @Override
    public boolean checkHitMission(int playerId,Color playerColor, GameState gameState) {
        int count = 0;

        for(Territory territory : gameState.getTerritoriesPlayersMap().keySet()){
            if(gameState.getPlayerTer(territory).getPlayerID() == playerId && territory.getCurrentUnits() >= this.numbTroopsForTerr){
                count ++;
            }
        }

        if (count >= terrTocon){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public String toString() {
        return "you have to conquer: " + " " + this.terrTocon + " territories with " + this.numbTroopsForTerr + " troops";
    }


}
