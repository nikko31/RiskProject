package risk.operations;

import risk.board.Territory;

/**
 * Created by Federico on 18/12/2015.
 */
public class Mission implements Operation {
    private String mission;

    public Mission(String mission) {
        this.mission = mission;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    @Override
    public String operationString() {
        return "MISSION: " + this.mission;
    }
}