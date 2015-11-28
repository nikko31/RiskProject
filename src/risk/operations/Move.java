package risk.operations;
import risk.board.Territory;

/**
 * Created by Federico on 28/11/2015.
 */
public class Move implements Operation {
    private Territory from;
    private Territory to;

    public Move(Territory from, Territory to) {
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
        return "MOVE: " + this.from.getTerritoryName() + " to " + this.to.getTerritoryName();
    }
}
