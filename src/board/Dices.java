package board;



/**
 * Created by DarkLinux on 09/11/15.
 */
public interface Dices {

    /**
     * deces used for attack
     * @param number
     * number of used dices
     * @return
     * the result of each roll
     * in a list
     * DOBBIAMO DECIDERE SE LA LISTA LA ORDINA
     * IL METODO OPPURE VIENE ORDINATA SOLO ALL'ESTERNO
     * ............MEGLIO RITORNARE ARRAY O ARRAYLIST????
     */
    int[] attackDices(int number);

    /**
     * dices for defence
     * @param number
     * number of used dices
     * @return
     * the result of each roll
     * in a list
     */
    int[] defenceDices(int number);


    /**
     * roll one dice
     * @return
     * integer values from 1
     * to 6
     */
    int rollDice();

    /**
     * NON SO SE METTERE TUTTO DENTRO
     * AD ATTACK DICE O A DEFENCE DICE
     * INTANTO LA METTO POI AL MASSIMO LA TOGLIAMO
     * @param value
     * result of rolldice
     */
    void addDice(int value);


}
