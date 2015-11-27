package risk;

/**
 * Created by DarkLinux on 27/11/15.
 */
public enum Phases {
    FORTIFY,
    ATTACK,
    MOVE,
    END_TURN;
    private static Phases[] phases = values();

    public Phases next() {
        return phases[(this.ordinal() + 1) % phases.length];
    }
}

