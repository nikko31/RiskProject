package board;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by DarkLinux on 09/11/15.
 */
public interface Territory {

    /**
     * set territory name
     * @param name
     * name of territory
     */
    void setName(String name);


    /**
     * get territory name
     * @return
     * name of territory
     */
    String getName();


    /**
     * set the owner
     * @param owner
     * player owner
     *
     * PER ADESSO HO MESSO COSI
     * POI NON SO SE FAREMO CON UN SEMPLICE ID NUMERICO
     * O SE LO TOGLIEREMO !!!!!!NB LE COMMENTO PERCHE DANNO ERRORE
     * NON ESSENDO IMPLEMTNETATO IL PLAYER
     *
     */
    //void setOwner(Player owner);

    /**
     * territory owner
     * @return
     * owner of territory
     */
    //Player getOwner();

    /**
     * set the troops
     * on this territory
     * @param troops
     * number of troops
     */
    void setTroopsOn (int troops);

    /**
     * number of troops
     * on this territory
     * @return
     * number of troops
     */
    int getTroopsOn ();

    /**
     * set territory
     * color
     * @param color
     * color to use
     */
    void setColor(Color color);

    /**
     * get terrytory color
     * @return
     * color
     */
    Color getColor();


    /**
     * all the border
     * territories
     * @return
     * list of border territories
     */
    ArrayList<Territory> getBorder();

    /**
     * add a border territory
     * @param territory
     * territory to add at
     * the border list
     */
    void addBorder(Territory territory);


    /**
     * check if the territory
     * is neighboring
     * @return
     * true in positive case
     * otherwise false
     */
    boolean isBorder();

}