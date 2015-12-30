package risk.gui;

import org.w3c.dom.events.Event;

/**
 * Created by DarkLinux on 01/12/15.
 */
public interface SelectedListener {
    void updateUi(String territory);

    void initializeSVG();

    void printSvgState(String territory);
}
