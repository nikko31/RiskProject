package risk;

import risk.board.Territory;
import risk.player.Player;

import java.util.*;

public class GameState {
    List<Player> players;
    Player currentPlayerTurn;
    Phases phase;
    Map<Territory, Player> territoriesPlayersMap;

    private Territory attackFrom;
    private Territory attackTo;
    private Territory moveFrom;
    private Territory moveTo;
    private Territory fortify;

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

        attackFrom = null;
        attackTo = null;
        moveFrom = null;
        moveTo = null;
        fortify = null;
    }

    public Player getPlayerTer(Territory territory) {
        return territoriesPlayersMap.get(territory);
    }

    //dato un territorio mi cambia il proprietario
    public void setPlayerTer(Territory territory, Player player) {
        //territoriesPlayersMap.replace(territory, player);
    }

    public Player getCurrentPlayerTurn() {
        return currentPlayerTurn;
    }

    public void setCurrentPlayerTurn(Player currentPlayerTurn) {
        this.currentPlayerTurn = currentPlayerTurn;
    }

    public Territory getAttackFrom() {
        return attackFrom;
    }

    public void setAttackFrom(Territory attackFrom) {
        this.attackFrom = attackFrom;
    }

    public Territory getAttackTo() {
        return attackTo;
    }

    public void setAttackTo(Territory attackTo) {
        this.attackTo = attackTo;
    }

    public Territory getMoveFrom() {
        return moveFrom;
    }

    public void setMoveFrom(Territory moveFrom) {
        this.moveFrom = moveFrom;
    }

    public Territory getMoveTo() {
        return moveTo;
    }

    public void setMoveTo(Territory moveTo) {
        this.moveTo = moveTo;
    }

    public Territory getFortify() {
        return fortify;
    }

    public void setFortify(Territory fortify) {
        this.fortify = fortify;
    }

    public Phases getPhase() {
        return phase;
    }

    public void setPhase(Phases phase) {
        this.phase = phase;
    }
}
