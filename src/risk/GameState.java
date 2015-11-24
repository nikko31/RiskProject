package risk;

import risk.board.Territory;
import risk.player.Player;

import java.util.*;

public class GameState {
    List<Player> players;
    Player currentPlayerTurn;
    String phase;
    Map<Territory, Player> territoriesPlayersMap;


    public GameState(LinkedList<GameResources.COLOR> playerColors, LinkedList<String> humanPlayerNames, LinkedList<String> aiPlayerNames) {
        this.players = new ArrayList<>();
        territoriesPlayersMap = new HashMap<>();
        int countId = 0;
        int numberOfPlayers = humanPlayerNames.size() + aiPlayerNames.size();
        ListIterator<GameResources.COLOR> playerColorsListIterator = playerColors.listIterator();
        //creo humanPlayers id [0,6)
        for (String hPlayername : humanPlayerNames) {
            players.add(new Player(
                    countId,
                    hPlayername,
                    GameResources.getStarterUnitsNumber(numberOfPlayers),
                    playerColorsListIterator.next()
            ));
            countId++;
        }
        //creo aiPlayers id [6,12)
        countId = 6;
        for (String aiPlayername : aiPlayerNames) {
            players.add(new Player(
                    countId,
                    aiPlayername,
                    GameResources.getStarterUnitsNumber(numberOfPlayers),
                    playerColorsListIterator.next()
            ));
            countId++;
        }
        List<Integer> shuffleKeys = new ArrayList<>(GameResources.SVG_NAME_MAP.keySet());
        Collections.shuffle(shuffleKeys);
        //inizializzo la mappa dei territori-players
        countId = 0;
        for (Integer stateId : shuffleKeys) {
            territoriesPlayersMap.put(
                    new Territory(
                            stateId,
                            GameResources.SVG_NAME_MAP.get(stateId),
                            0,
                            GameResources.CONNECTIONS.get(stateId)
                    ),
                    players.get(countId % numberOfPlayers)
            );
        }
        currentPlayerTurn = players.get(0);
    }

    public Player getPlayerTer(Territory territory) {
        return territoriesPlayersMap.get(territory);
    }

    //dato un territorio mi cambia il proprietario
    public void setPlayerTer(Territory territory, Player player) {
        territoriesPlayersMap.replace(territory, player);
    }


}
