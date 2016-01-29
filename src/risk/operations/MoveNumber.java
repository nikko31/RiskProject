package risk.operations;

import risk.board.Territory;

/**
 * Created by DarkLinux on 28/01/16.
 */
public class MoveNumber implements Operation {
    private Territory from;
    private Territory to;
    private int number;

    public MoveNumber(Territory from, Territory to, int number) {
        this.from = from;
        this.to = to;
        this.number = number;
    }

    public Territory getTo() {
        return to;
    }

    public int getNumber() {
        return number;
    }

    public Territory getFrom() {
        return from;
    }

    public String getFromName() {
        return from.getTerritoryName();
    }

    public int getFromUnits() {
        return from.getCurrentUnits();
    }

    public String getToName() {
        return to.getTerritoryName();
    }

    public int getToUnits() {
        return to.getCurrentUnits();
    }


    @Override
    public String operationString() {
        return "MOVE: " + this.from.getTerritoryName() + " to " + this.to.getTerritoryName()+" "+this.number+" troups";
    }
}