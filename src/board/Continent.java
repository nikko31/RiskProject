package board;

import java.util.ArrayList;

/**
 * Created by DarkLinux on 09/11/15.
 */
public interface Continent {

    /**
     * this method is used to return all the
     * territories of a specific continent
     *
     * @return
     * a list of each territory in this
     * continent
     */
    ArrayList<Territory> getTerritory();



    /**
     *set continent name
     * @param name
     * continent name
     */
    void setName(String name);


    /**
     * getName return the continent name
     * @return
     * continent name
     */
    String getName();

    /**
     * get the number of bonus troop
     * if a player hold all the territories
     * of a continent
     * DA AGGIORNARE SE USIAMO CLASSE TROOP O SE LASCIAMO COME
     * INTERO?????
     */
    int getBonusTroops();


    /**
     * set the number of bonus troops
     * of continent
     * @param value
     * value represent the number of bonus troops
     */
    void setBonusTroops(int value);

    /**
     * add a territory at the
     * ArrayList<Territory>
     * @param a
     * territory to add
     *
     */
    void addTerritory(Territory a);

    /**
     * control if the territory is
     * in this continent
     * @param name
     * name of territory to control
     * @return
     * the territory in positive case
     * otherwise null
     */
    Territory getTerritory(String name);


    /**
     * number of territories
     * @return
     * the number of territories
     * of a specific continent
     */
    int getNumOfTer();

    /**
     * set the number of
     * territories in a country
     * @param number
     * represent the number of territories
     */
    void setNumOfTer(int number);

}