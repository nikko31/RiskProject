package risk.operations;

import risk.board.Territory;

import java.awt.*;

/**
 * Created by Federico on 30/11/2015.
 */
public class AttackConquest implements Operation {
    private Territory from;
    private Territory to;
    private Color color;


    public AttackConquest(Territory from, Territory to, Color color) {
        this.from = from;
        this.to = to;
        this.color = color;

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

    public Color getColor(){return color;}



    @Override
    public String operationString() {
        return "ATTACKCONQUEST: " + this.from.getTerritoryName() + " to " + this.to.getTerritoryName() + " " +"attacker win the territory " ;
    }
}
