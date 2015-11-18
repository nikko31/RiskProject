package risk.gui;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Federico on 17/11/2015.
 *
 * questa non è utilizzata perchè initialPanel estende Jpanel

 */
public abstract class Observable {
    List<InitOptionListener> listeners;

    public Observable () {
        listeners = new ArrayList<InitOptionListener>();
    }

    public void addListener(InitOptionListener toAdd) {
        listeners.add(toAdd);
    }

    public void notifyListeners(int a,int b) {
        for (InitOptionListener sl : listeners)
            sl.playerNumber(a,b);
    }

}
