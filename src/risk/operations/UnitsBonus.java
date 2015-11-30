package risk.operations;

import risk.board.Territory;

/**
 * Created by Federico on 30/11/2015.
 */
public class UnitsBonus implements Operation {
    private int units;

    public UnitsBonus (int units) {
        this.units=units;
    }

    public int getUnits(){
        return units;
    }

    @Override
    public String operationString() {
        return "UNITS BONUS: " + "you recive: "+ getUnits() +" units";
    }
}