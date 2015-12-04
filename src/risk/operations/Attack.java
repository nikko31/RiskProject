package risk.operations;

import risk.board.Territory;

/**
 * Created by DarkLinux on 27/11/15.
 */
public class Attack implements Operation {
    private Territory from;
    private Territory to;

    public Attack(Territory from, Territory to) {
        this.from = from;
        this.to = to;
    }

    public Territory getTo() {
        return to;
    }

    public Territory getFrom() {
        return from;
    }
    public String getFromName(){return from.getTerritoryName();}

    public int getFromUnits(){ return from.getCurrentUnits();}

    public String getToName(){return to.getTerritoryName();}

    public int getToUnits(){ return to.getCurrentUnits();}

    @Override
    public String operationString() {
        return "ATTACK: " + this.from.getTerritoryName() + " to " + this.to.getTerritoryName();
    }
}
