package risk.operations;

import risk.board.Territory;

/**
 * Created by Federico on 28/11/2015.
 */
public class TerritoryUnselected implements Operation {
    public Territory unselected;

    public TerritoryUnselected(Territory unselected) {
        this.unselected = unselected;
    }

    public Territory getUnselected() {
        return unselected;
    }
    public String getUnselectedName(){return unselected.getTerritoryName();}

    public String getUnselectedUnits(){ return new Integer(unselected.getCurrentUnits()).toString();}

    @Override
    public String operationString() {
        return "UNSELECTED: " + this.unselected.getTerritoryName();
    }
}
