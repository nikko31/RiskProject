package risk.operations;

import risk.board.Territory;

/**
 * Created by Federico on 30/11/2015.
 */
public class AttackConquest implements Operation {
    private Territory from;
    private Territory to;

    public AttackConquest(Territory from, Territory to) {
        this.from = from;
        this.to = to;
    }

    public Territory getTo() {
        return to;
    }

    public Territory getFrom() {
        return from;
    }

    @Override
    public String operationString() {
        return "ATTACK: " + this.from.getTerritoryName() + " to " + this.to.getTerritoryName() + "" +"attacker win the territory " ;
    }
}
