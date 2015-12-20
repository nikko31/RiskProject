package risk.board;


import risk.GameResources;

/**
 * Created by Federico on 29/11/2015.
 */
public class Card {

    private GameResources.cardType cardType;
    private int cardId;


    public Card(Integer cardId,String card){
        this.cardId = cardId;
        switch(card.charAt(0)) {
            case 'I': {
                this.cardType = GameResources.cardType.INFANTRY;
                break;
            }
            case 'C': {
                this.cardType = GameResources.cardType.CAVALRY;
                break;
            }
            case 'A': {
                this.cardType = GameResources.cardType.ARTILLERY;
                break;
            }
            case 'W': {
                this.cardType = GameResources.cardType.WILDCARD;
                break;
            }
            default: throw new IllegalArgumentException("Invalid card!");
        }



    }

    public GameResources.cardType getCardType() {
        return cardType;
    }

    public void setCardType(GameResources.cardType cardType) {
        this.cardType = cardType;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId.intValue();
    }


    private String cardToString(GameResources.cardType cardtype){

        if(cardtype == GameResources.cardType.INFANTRY ){
            return "INFANTRY";
        }
        else if(cardtype == GameResources.cardType.CAVALRY ){
            return "CAVALRY";
        }
        else if(cardtype == GameResources.cardType.WILDCARD){
            return "WILDCARD";
        }
        else if(cardtype == GameResources.cardType.ARTILLERY ){
            return "ARTILLERY";
        }
        else {
            return "error";
        }


    }

    public String toString(){
        return cardToString(this.cardType);
    }


}
