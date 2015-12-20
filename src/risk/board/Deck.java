package risk.board;

import risk.GameResources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Federico on 18/12/2015.
 */
public class Deck {
    private LinkedList<Card> deck;

    public Deck(){
       initDeck();
    }


    private void initDeck() {
        deck = new LinkedList<Card>();
        List<Integer> cardKeys = new ArrayList<>(GameResources.CARD_ID_STRING.keySet());
        for (Integer cardId : cardKeys) {

            deck.add(new Card(cardId, GameResources.CARD_ID_STRING.get(cardId)));
        }

        Collections.shuffle(deck);

    }
    public Card takeCard() {
        if (deck.size() > 0) {
            Card card = deck.get(0);
            deck.remove(0);
            return card;

        } else {
            return null;
        }
    }

    public void restoreCards(List<Card> cards) {

        for (Card cardIter : cards) {
            deck.add(cardIter);
        }
        Collections.shuffle(deck);


    }

    public static int chekCardCombination(List<Card> cards){

        if(cards.size() != 3){
            return 0;
        }
        else{
            GameResources.cardType card1 = cards.get(0).getCardType();
            GameResources.cardType card2 = cards.get(1).getCardType();
            GameResources.cardType card3 = cards.get(2).getCardType();
            if(card1 == GameResources.cardType.ARTILLERY && card2 == GameResources.cardType.ARTILLERY && card3 == GameResources.cardType.ARTILLERY){
                return 4;
            }
            else if(card1 == GameResources.cardType.INFANTRY && card2 == GameResources.cardType.INFANTRY  && card3 == GameResources.cardType.INFANTRY){
                return 6;
            }
            else if(card1 == GameResources.cardType.CAVALRY && card2 == GameResources.cardType.CAVALRY && card3 == GameResources.cardType.CAVALRY){
                return 8;
            }
            else if(card1 == GameResources.cardType.CAVALRY && card2 == GameResources.cardType.INFANTRY && card3 == GameResources.cardType.ARTILLERY ){
                return 10;
            }
            else if(card1 == GameResources.cardType.CAVALRY && card2 == GameResources.cardType.ARTILLERY && card3 == GameResources.cardType.INFANTRY){
                return 10;
            }
            else if(card1 == GameResources.cardType.ARTILLERY && card2 == GameResources.cardType.INFANTRY && card3 == GameResources.cardType.CAVALRY){
                return 10;
            }
            else if(card1 == GameResources.cardType.ARTILLERY && card2 == GameResources.cardType.CAVALRY && card3 == GameResources.cardType.INFANTRY){
                return 10;
            }
            else if(card1 == GameResources.cardType.INFANTRY && card2 == GameResources.cardType.CAVALRY && card3 == GameResources.cardType.ARTILLERY){
                return 10;
            }
            else if(card1 == GameResources.cardType.INFANTRY && card2 == GameResources.cardType.ARTILLERY && card3 == GameResources.cardType.CAVALRY){
                return 10;
            }
            else if(card2 == card3 && card1 == GameResources.cardType.WILDCARD){
                return 12;
            }
            else if(card1 == card3 && card2 == GameResources.cardType.WILDCARD ){
                return 12;
            }
            else if(card1 == card2 && card3 == GameResources.cardType.WILDCARD ){
                return 12;
            }
            else{
                return 0;
            }
        }

    }


}
