package risk.player;

import risk.GameResources.COLOR;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DarkLinux on 19/11/15.
 */
public class Player {
    private int playerID;
    private String playerName;
    private int freeUnits;
    private COLOR playerColor;
    private Map<String, Integer> territoryUnitMap;
    private List<String> continentList;

    public Player(int playerID, String playerName, int freeUnits, COLOR color, Map<String, Integer> territoryUnitMap) {
        this.playerID = playerID;
        this.playerName = playerName;
        this.playerColor = color;
        this.freeUnits = freeUnits;
        this.territoryUnitMap = new HashMap<>(territoryUnitMap);
        this.continentList = new ArrayList<>();
    }

    public int getPlayerID() {
        return playerID;
    }


    public String getPlayerName() {
        return playerName;
    }


    public int getFreeUnits() {
        return freeUnits;
    }

    public void setFreeUnits(int freeUnits) {
        this.freeUnits = freeUnits;
    }

    public Map<String, Integer> getTerritoryUnitMap() {
        return territoryUnitMap;
    }

    public void setTerritoryUnitMap(Map<String, Integer> territoryUnitMap) {
        this.territoryUnitMap = territoryUnitMap;
    }

    public COLOR getPlayerColor() {
        return playerColor;
    }


    public List<String> getContinentList() {
        return continentList;
    }

    public void setContinentList(List<String> continentList) {
        this.continentList = continentList;
    }
}
