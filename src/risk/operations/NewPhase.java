package risk.operations;

import risk.Phases;

/**
 * Created by DarkLinux on 27/11/15.
 */
public class NewPhase implements Operation {
    Phases phase;

    public NewPhase(Phases phase) {
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
