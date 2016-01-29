package risk.gui;

import org.apache.batik.bridge.UpdateManager;
import org.apache.batik.swing.JSVGCanvas;
import org.apache.batik.swing.gvt.GVTTreeRendererAdapter;
import org.apache.batik.swing.gvt.GVTTreeRendererEvent;
import resources.Messages;

import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by DarkLinux on 29/12/15.
 */
public class StateImage {
    private JSVGCanvas svgCanvas = new JSVGCanvas();

    public StateImage(File svgFile) {

        createComponents(svgFile);
    }

    public Component getAsComponent() {
        return svgCanvas;
    }

    public void createComponents(File svgFile) {
        try {
            svgCanvas.setDocumentState(JSVGCanvas.ALWAYS_DYNAMIC);
            svgCanvas.setURI(svgFile.toURI().toURL().toString());

        } catch (MalformedURLException e) {
            System.out.println(Messages.ERROR_LOADING_SVG + e);
        } catch (NullPointerException e) {
            System.out.println("WARNING: Troppo veloce per rilevare lo stato");
        }

    }
}
