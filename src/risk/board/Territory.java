package risk.board;

import risk.GameResources;
import risk.player.AbstractRiskPlayer;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by DarkLinux on 12/11/15.
 */

public class Territory {
    private String territoryName;
    private String player;
    private int currentUnits;
    private int territoryID;

    public Territory(int id,String territoryId, String player, int currentUnits) {
        this.territoryName = territoryId;
        this.player = player;
        this.currentUnits = currentUnits;
        this.territoryID=id;
    }

    public Territory(Territory territory) {
        this.territoryName = territory.territoryName;
        this.player = territory.player;
        this.currentUnits = territory.currentUnits;
        this.territoryID=territory.territoryID;
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
    public static int getContinentId(String territory)
    {
        return getContinentId(GameResources.SVG_ID_MAP.get(territory));
    }
    public static boolean isAttackPossible(int fromTerritory, int toTerritory) {
        return GameResources.CONNECTIONS.get(fromTerritory).contains(toTerritory);
    }

}
