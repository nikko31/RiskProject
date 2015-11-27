package risk.operations;

import risk.board.Territory;

/**
 * Created by DarkLinux on 27/11/15.
 */
public class TerritorySelected implements Operation {
    public Territory selected;

    public TerritorySelected(Territory selected) {
        this.selected = selected;
    }

    public Territory getSelected() {
        return selected;
    }

    @Override
    public String operationString() {
        return "SELECTED: " + this.selected.getTerritoryName();
    }
}
