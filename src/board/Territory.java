package board;

/**
 * Created by Federico on 13/11/2015.
 */

import java.awt.*;
import java.util.ArrayList;





/**
 * Created by DarkLinux on 09/11/15.
 */
public class Territory {
    private String name;
    //private Player owner;
    private int numb_troops;
    private ArrayList<Territory> border;
    private Color color_ground;

    /**
     * set territory name
     * @param name
     * name of territory
     */
    void setName(String name){
        this.name=name;
    }

    /**
     * get territory name
     * @return
     * name of territory
     */
    String getName(){
        return this.name;
    }


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
    void setTroopsOn (int troops){
        this.numb_troops=troops;
    }

    /**
     * number of troops
     * on this territory
     * @return
     * number of troops
     */
    int getTroopsOn (){
        return this.numb_troops;
    }

    /**
     * set territory
     * color
     * @param color
     * color to use
     */
    void setColor(Color color){
        this.color_ground=color;
    }

    /**
     * get terrytory color
     * @return
     * color
     */
    Color getColor(){
        return this.color_ground;
    }


    /**
     * all the border
     * territories
     * @return
     * list of border territories
     */
    ArrayList<Territory> getBorder(){
        return this.border;
    }

    /**
     * add a border territory
     * @param territory
     * territory to add at
     * the border list
     */
    void addBorder(Territory territory){
        border.add(territory);
    }


    /**
     * check if the territory
     * is neighboring
     * @return
     * true in positive case
     * otherwise false
     */
    boolean isBorder(Territory near_territory){
        for(Territory index_territory: border){

            if(near_territory.equals(index_territory)){
                return true;
            }
        }

        return false;
    }

}