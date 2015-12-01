package risk;

/**
 * Created by DarkLinux on 27/11/15.
 */
public enum Phases {
    INITIAL,
    BONUS,
    FORTIFY,
    ATTACK,
    MOVE,
    END_TURN;
    private static Phases[] phases = values();

    public Phases next() {
        if(this.ordinal() == 0){
            return phases[phases.length -1];
        }
        return phases[(this.ordinal() + 1) % phases.length];
    }
}

