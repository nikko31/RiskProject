package risk.board;

import risk.GameResources;

import java.util.ArrayList;
import java.util.List;

public class Territory {
    private String territoryName;
    private int currentUnits;
    private int territoryID;
    private List<Integer> neighbours;

    public Territory(int id, String territoryId, int currentUnits, List<Integer> neighbours) {
        this.territoryName = territoryId;
        this.currentUnits = currentUnits;
        this.territoryID = id;
        this.neighbours = new ArrayList<>(neighbours);
    }

    public Territory(Territory territory) {
        this.territoryName = territory.territoryName;
        this.currentUnits = territory.currentUnits;
        this.territoryID = territory.territoryID;
        this.neighbours = territory.neighbours;
    }

    public static int getContinentId(int territoryId) {
        if (territoryId >= 0 && territoryId <= 8) {
            return 0;
        } else if (territoryId >= 9 && territoryId <= 12) {
            return 1;
        } else if (territoryId >= 13 && territoryId <= 19) {
            return 2;
        } else if (territoryId >= 20 && territoryId <= 25) {
            return 3;
        } else if (territoryId >= 26 && territoryId <= 37) {
            return 4;
        } else if (territoryId >= 38 && territoryId <= 41) {
            return 5;
        } else {
            throw new IllegalArgumentException("Invalide territory Id");
        }
    }

    public int getContinentId(String territory) {
        return getContinentId(GameResources.SVG_ID_MAP.get(territory));
    }

    public boolean isAttackPossible(int toTerritory) {
        for (int territoryId : this.neighbours) {
            if (territoryId == toTerritory)
                return true;
        }
        return false;
    }

    public boolean isAttackPossible(Territory toTerritory) {
        return isAttackPossible(toTerritory.getTerritoryID());
    }

    public void setCurrentUnits(int currentUnits) {
        this.currentUnits = currentUnits;
    }

    public String getTerritoryName() {
        return territoryName;
    }

    public int getCurrentUnits() {
        return currentUnits;
    }

    public int getTerritoryID() {
        return territoryID;
    }

    public List<Integer> getNeighbours() {
        return this.neighbours;
    }

}

