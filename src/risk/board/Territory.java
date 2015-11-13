package risk.board;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by DarkLinux on 12/11/15.
 */


public class Territory {
    private String name;
    //private Player owner;
    private int armies;
    private ArrayList<Territory> neighbours;
    private Color color_ground;


    public Territory(String name){
        this.name=name;
    }
    /**
     * set territory name
     * @param name
     * name of territory
     */
    public void setName(String name){
        this.name=name;
    }

    /**
     * get territory name
     * @return
     * name of territory
     */
    public String getName(){
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
     * @param armies
     * number of troops
     */
    public void setArmies (int armies){
        this.armies=armies;
    }

    /**
     * number of troops
     * on this territory
     * @return
     * number of troops
     */
    public int getArmies (){
        return this.armies;
    }

    /**
     * set territory
     * color
     * @param color
     * color to use
     */
    public void setColor(Color color){
        this.color_ground=color;
    }

    /**
     * get terrytory color
     * @return
     * color
     */
    public Color getColor(){
        return this.color_ground;
    }


    /**
     * all the border
     * territories
     * @return
     * list of border territories
     */
    public ArrayList<Territory> getNeighbours(){
        return this.neighbours;
    }

    /**
     * add a border territory
     * @param territory
     * territory to add at
     * the border list
     */
    public void addNeighbours(Territory territory){
        neighbours.add(territory);
    }


    /**
     * check if the territory
     * is neighboring
     * @return
     * true in positive case
     * otherwise false
     */
    public boolean isNeighbours (Territory near_territory){
        for(Territory index_territory: neighbours){

            if(near_territory.equals(index_territory)){
                return true;
            }
        }

        return false;
    }

}
