package risk.player;


import risk.board.Card;
import risk.board.Mission;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Player {
    private int playerID;
    private String playerName;
    private int freeUnits;
    private Color playerColor;
    private List<Card> cards;
    private Mission mission;
    public Player(int playerID, String playerName, int freeUnits,Mission mission, Color color) {
        this.playerID = playerID;
        this.playerName = playerName;
        this.playerColor = color;
        this.freeUnits = freeUnits;
        this.cards = new LinkedList<>();
        this.mission = mission;
    }

    public int getPlayerID() {
        return playerID;
    }


    public String getPlayerName() {
        return playerName;
    }


    public int getFreeUnits() {
        return freeUnits;
    }

    public void setFreeUnits(int freeUnits) {
        this.freeUnits = freeUnits;
    }

    public Color getPlayerColor() {
        return playerColor;
    }

    public void addPlayerCard(Card card){
        cards.add(card);
    }

    public void removePlayerCard(List<Card> cardsToRemove ){
        for(Card carditer : cardsToRemove){
            cards.remove(carditer);
        }
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }
}
