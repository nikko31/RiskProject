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
        switch(getPhase()) {
            case INITIAL: {
                return "INITIAL";
            }
            case BONUS: {
                return "BONUS";
            }
            case FORTIFY: {
                return "FORTIFY";
            }
            case ATTACK: {
                return "ATTACK";
            }
            case MOVE: {
                return "MOVE";
            }
            case END_TURN: {
                return "END TURN";
                }

            default: throw new IllegalArgumentException("Invalid card!");
        }
    }
}
