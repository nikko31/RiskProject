package risk.operations;

/**
 * Created by FEDE on 20/12/2015.
 */

import risk.board.Card;

import java.util.LinkedList;

public class Cards implements Operation {

    LinkedList<Card> cards;

    public Cards(LinkedList<Card> cards) {
        this.cards = new LinkedList<>();
        this.cards.addAll(cards);
    }

    public LinkedList<Card> getcards() {
        return cards;
    }

    @Override
    public String operationString() {
        return "CARDS: " + this.cards.size();
    }
}