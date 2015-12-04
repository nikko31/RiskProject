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

    public String getSelectedName(){return selected.getTerritoryName();}

    public String getSelectedUnits(){ return new Integer(selected.getCurrentUnits()).toString();}

    @Override
    public String operationString() {
        return "SELECTED: " + this.selected.getTerritoryName();
    }
}
