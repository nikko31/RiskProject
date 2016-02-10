package risk.board;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DarkLinux on 13/11/15.
 */
public class Continent {
    private String name;
    private int continentId;
    private List<Territory> territories;
    private int bonus_armies;

    public Continent(int continentId,ArrayList<Territory> territories) {
        this.continentId = continentId;
        this.territories = new ArrayList<>(territories);
        switch(this.continentId) {
            case 0: {
                this.name = "NORTH AMERICA";
                this.bonus_armies = 5;
                break;
            }
            case 1: {
                this.name = "SOUTH AMERICA";
                this.bonus_armies = 2;
                break;
            }
            case 2: {
                this.name = "EUROPE";
                this.bonus_armies = 5;
                break;
            }
            case 3: {
                this.name = "AFRICA";
                this.bonus_armies = 3;
                break;
            }
            case 4: {
                this.name = "ASIA";
                this.bonus_armies = 7;
                break;
            }
            case 5: {
                this.name = "OCEANIA";
                this.bonus_armies = 2;
                break;
            }

        }

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

    public int getContinentId() {
        return continentId;
    }

    public void setContinentId(int continentId) {
        this.continentId = continentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Territory> getTerritories() {
        return territories;
    }

    public void setTerritories(List<Territory> territories) {
        this.territories = territories;
    }
}
