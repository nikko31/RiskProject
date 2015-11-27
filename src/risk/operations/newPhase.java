package risk.operations;

import risk.Phases;

/**
 * Created by DarkLinux on 27/11/15.
 */
public class newPhase implements Operation {
    Phases phase;

    public newPhase(Phases phase) {
        this.phase = phase;
    }

    public Phases getPhase() {
        return phase;
    }

    @Override
    public String operationString() {
        return null;
    }
}
