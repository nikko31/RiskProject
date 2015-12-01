package risk;

import risk.board.Card;
import risk.board.Territory;
import risk.player.Player;

import java.awt.*;
import java.util.*;
import java.util.List;

public class GameState {
    List<Player> players;
    Player currentPlayerTurn;
    Phases phase;
    LinkedList<Card> deck;
    Map<Territory, Player> territoriesPlayersMap;

    private Territory attackFrom;
    private Territory moveFrom;
    public boolean initialflag;
    int count;


    public GameState(List<Color> playerColors, List<String> humanPlayerNames, List<String> aiPlayerNames) {
        this.players = new ArrayList<>();
        phase=Phases.INITIAL;
        territoriesPlayersMap = new HashMap<>();
        deck = new LinkedList<>();
        int countId = 0;
        count = 0;
        int numberOfPlayers = humanPlayerNames.size() + aiPlayerNames.size();
        ListIterator<Color> playerColorsListIterator = playerColors.listIterator();
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

            countId++;
        }

        //inizializzo le carte/
        List<Integer> cardKeys = new ArrayList<>(GameResources.CARD_ID_STRING.keySet());
        Collections.shuffle(cardKeys);
        for(Integer cardId : cardKeys){

            deck.add(new Card(cardId,GameResources.CARD_ID_STRING.get(cardId)));
        }


        currentPlayerTurn = players.get(0);
        count++;
        initialflag=true;
        attackFrom = null;
        moveFrom = null;


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

    public Territory getMoveFrom() {
        return moveFrom;
    }

    public void setMoveFrom(Territory moveFrom) {
        this.moveFrom = moveFrom;
    }

    public Phases getPhase() {
        return phase;
    }


    public void setPhase(Phases phase) {
        this.phase = phase;
    }


    public Map<Territory, Player> getTerritoriesPlayersMap() {
        return territoriesPlayersMap;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void elimiatePlayer(Player player){
        players.remove(player);

    }

    public List<Card> getDeck() {
        return deck;
    }

    public void setDeck(LinkedList<Card> deck) {
        this.deck = deck;
    }

    public Card fishingCard(){
        if(deck.size()>0){

            return deck.remove(0);

        }

        else{
            return null;
        }
    }

    public void restoreCards(List<Card> cards){


        for(Card cardIter : cards){
            deck.add(cardIter);
        }
        //ricordati di fare un metodo che le mescola


    }


    public void nextPlayer(){
        currentPlayerTurn=players.get(count%players.size());
        count++;
        if(count==players.size()){
            count=0;
        }
    }

    public void nextPhase(){
        phase.next();

    }


}
