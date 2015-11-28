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

    @Override
    public String operationString() {
        return "UNSELECTED: " + this.unselected.getTerritoryName();
    }
}
