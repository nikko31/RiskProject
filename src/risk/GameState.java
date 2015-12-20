package risk;

import risk.board.Card;
import risk.board.Continent;
import risk.board.Deck;
import risk.board.Territory;
import risk.player.Player;
import risk.mission.*;

import java.awt.*;
import java.util.*;
import java.util.List;

public class GameState {
    ArrayList<Player> players;
    HashMap<Color, Player> playerEliminated;
    Player currentPlayerTurn;
    Phases phase;
    Phases lastphase;
    Deck deck;
    ArrayList<risk.mission.Mission> missions;
    Map<Territory, Player> territoriesPlayersMap;
    List<Continent> continents;
    private Territory attackFrom;
    private Territory moveFrom;
    public boolean initialflag;
    int count;


    public GameState(List<Color> playerColors, List<String> humanPlayerNames, List<String> aiPlayerNames) {
        this.players = new ArrayList<>();
        phase = Phases.INITIAL;
        territoriesPlayersMap = new HashMap<>();
        deck = new Deck();
        int countId = 0;
        count = 0;
        int numberOfPlayers = humanPlayerNames.size() + aiPlayerNames.size();
        ListIterator<Color> playerColorsListIterator = playerColors.listIterator();
        missions = new ArrayList<>();
        initMission();

        for (String hPlayername : humanPlayerNames) {
            players.add(new Player(
                    countId,
                    hPlayername,
                    GameResources.getStarterUnitsNumber(numberOfPlayers),
                    missions.get(0),
                    playerColorsListIterator.next()
            ));
            missions.remove(0);
            missions.trimToSize();
            countId++;
        }
        //creo aiPlayers id [6,12)
        countId = 6;
        for (String aiPlayername : aiPlayerNames) {
            players.add(new Player(
                    countId,
                    aiPlayername,
                    GameResources.getStarterUnitsNumber(numberOfPlayers),
                    missions.get(0),
                    playerColorsListIterator.next()
            ));
            missions.remove(0);
            missions.trimToSize();
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
                            1,
                            GameResources.CONNECTIONS.get(stateId)
                    ),
                    players.get(countId % numberOfPlayers)


            );

            countId++;
            players.get(countId % numberOfPlayers).setFreeUnits(players.get(countId % numberOfPlayers).getFreeUnits() - 1);
        }


        initContinent();
        this.playerEliminated = new HashMap<>();
        this.currentPlayerTurn = players.get(0);
        this.lastphase = Phases.INITIAL;
        this.initialflag = true;
        this.attackFrom = null;
        this.moveFrom = null;


    }


    //--------------------PHASE METHOD-----------------------------------------------------
    public Phases getPhase() {
        return phase;
    }


    public void setPhase(Phases phase) {
        this.phase = phase;
    }


    public void nextPhase() {
        switch (phase) {
            case INITIAL:
                this.setPhase(Phases.END_TURN);
                break;
            case BONUS:
                this.setPhase(Phases.FORTIFY);
                break;
            case FORTIFY:
                this.setPhase(Phases.ATTACK);
                break;
            case ATTACK:
                this.setPhase(Phases.MOVE);
                break;
            case MOVE:
                this.setPhase(Phases.END_TURN);
                break;
            case END_TURN:

                this.setPhase(Phases.INITIAL);
                break;
        }

        System.out.println("avanza di fase");
    }

    public Phases getLastphase() {
        return lastphase;
    }

    public void setLastphase(Phases lastphase) {
        this.lastphase = lastphase;
    }

    //----------------------------PLAYER METHOD----------------------------------

    public Map<Territory, Player> getTerritoriesPlayersMap() {
        return territoriesPlayersMap;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void elimiatePlayer(Player player) {
        players.remove(player);
        players.trimToSize();
        playerEliminated.put(player.getPlayerColor(), currentPlayerTurn);
        System.out.println(player.getPlayerName() + " is eliminated");

    }

    public void nextPlayer() {
        count++;
        if (count >= players.size()) {
            count = 0;
        }
        currentPlayerTurn = players.get(count % players.size());
    }

    public Player getPlayerTer(Territory territory) {
        return territoriesPlayersMap.get(territory);
    }

    //change the owner of a territory
    public void setPlayerTer(Territory territory, Player player) {
        territoriesPlayersMap.replace(territory, player);
    }

    public Player getCurrentPlayerTurn() {
        return currentPlayerTurn;
    }

    public HashMap<Color, Player> getPlayerEliminated() {
        return playerEliminated;
    }

    public void setPlayerEliminated(HashMap<Color, Player> playerEliminated) {
        this.playerEliminated = playerEliminated;
    }

    //--------------------------------DECK------------------------------------------
    /*
    public void initDeck() {
        List<Integer> cardKeys = new ArrayList<>(GameResources.CARD_ID_STRING.keySet());
        for (Integer cardId : cardKeys) {

            deck.add(new Card(cardId, GameResources.CARD_ID_STRING.get(cardId)));
        }

        Collections.shuffle(deck);

    }


    public List<Card> getDeck() {
        return deck;
    }

    public void setDeck(LinkedList<Card> deck) {
        this.deck = deck;
    }
    */

    public Card takeCard() {
         return deck.takeCard();
    }

    public void restoreCards(List<Card> cards) {
        deck.restoreCards(cards);

    }


    //---------------------OTHER METHOD--------------------------------


    public List<Continent> getContinents() {
        return continents;
    }

    public void setContinents(List<Continent> continents) {
        this.continents = continents;
    }

    public Territory getAttackFrom() {
        return attackFrom;
    }

    public void setAttackFrom(Territory attackFrom) {
        this.attackFrom = attackFrom;
    }

    public Territory getMoveFrom() {
        return moveFrom;
    }

    public void setMoveFrom(Territory moveFrom) {
        this.moveFrom = moveFrom;
    }

    public void initContinent() {
        continents = new ArrayList<>();
        ArrayList<Territory> territories = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            territories.clear();
            for (Territory territory : territoriesPlayersMap.keySet()) {
                if (Territory.getContinentId(territory.getTerritoryID()) == i) {
                    territories.add(territory);

                }
            }

            continents.add(new Continent(i, territories));

        }
    }

    public void initMission() {


        ArrayList<Integer> missionkey = new ArrayList<>(GameResources.MISSION_CONTINENT.keySet());
        for (Integer key : missionkey) {
            missions.add(new MissionContinent(key,GameResources.MISSION_CONTINENT.get(key)));
        }


        missionkey.clear();
        missionkey = new ArrayList<>(GameResources.MISSION_TERRITORY.keySet());
        for (Integer key : missionkey) {
            missions.add(new MissionTerritory(key, GameResources.MISSION_TERRITORY.get(key)));
        }

        missionkey.clear();
        missionkey = new ArrayList<>(GameResources.MISSION_DESTROY.keySet());
        for (Integer key : missionkey) {
            missions.add(new MissionDefeatPlayer(GameResources.MISSION_DESTROY.get(key)));
        }

        Collections.shuffle(missions);
    }


}
