package risk.board;

import java.util.ArrayList;

/**
 * Created by DarkLinux on 13/11/15.
 */
public class Continent {
    private String name;
    private int bonus_armies;
    private ArrayList<Territory> territories;

    public Continent(String name, ArrayList<Territory> territories) {
        this.territories = new ArrayList<Territory>(territories);
        this.name = name;
    }

    public String getName() {

        return this.name;
    }



    public int getBonusArmies(){

        return this.bonus_armies;
    }


    public void setBonusArmies(int bonus_armies){
        this.bonus_armies=bonus_armies;
    }



    public ArrayList<Territory> getTerritories() {

        return this.territories;
    }
}
