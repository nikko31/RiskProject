package risk.board;

import java.util.ArrayList;

/**
 * Created by DarkLinux on 13/11/15.
 */
public class Continent {
    private String name;
    private ArrayList<Territory> territories;

    public Continent(String name, ArrayList<Territory> territories) {
        this.territories = new ArrayList<Territory>(territories);
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Territory> getTerritories() {
        return this.territories;
    }
}
