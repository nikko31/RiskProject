package risk.board;


import risk.GameResources;

/**
 * Created by Federico on 29/11/2015.
 */
public class Card {

    private GameResources.Type cardType;
    private int cardId;


    public Card(Integer cardId,String card){
        this.cardId = cardId;
        switch(card.charAt(0)) {
            case 'I': {
                this.cardType = GameResources.Type.INFANTRY;
                break;
            }
            case 'C': {
                this.cardType = GameResources.Type.CAVALRY;
                break;
            }
            case 'A': {
                this.cardType = GameResources.Type.ARTILLERY;
                break;
            }
            case 'W': {
                this.cardType = GameResources.Type.WILDCARD;
                break;
            }
            default: throw new IllegalArgumentException("Invalid card!");
        }



    }

    public GameResources.Type getCardType() {
        return cardType;
    }

    public void setCardType(GameResources.Type cardType) {
        this.cardType = cardType;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId.intValue();
    }
}
