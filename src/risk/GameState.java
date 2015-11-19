package risk;

import risk.board.Territory;
import risk.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameState {
    List<Player> players;
    Player currentPlayerTurn;
    String phase;
    List<Territory>territories;
    List<Integer>turnOrder;
    public GameState(Map<Integer,String> playersInfo, GameResources.COLOR color){
        this.players=new ArrayList<>();
        turnOrder=new ArrayList<>(playersInfo.size());

        //inizializziamo i players uno ad uno....mappa territori x player->0
        for(Map.Entry<Integer,String> player : playersInfo.entrySet()){
            this.players.add(
                    new Player((int)player.getKey(), player.getValue(),
                            GameResources.getStarterUnitsNumber(playersInfo.size()),
                            color, new HashMap<String,Integer>())
            );
            //ordine turni (alla boia di giuda in ordine...aggiungeremo un incasinatore per randomizzarli)
            turnOrder.add(player.getKey());
        }
        this.phase=GameResources.START_GAME_PHASE;
        currentPlayerTurn=players.get(turnOrder.get(0));
    }


}
