package risk.gui;


import org.apache.batik.dom.AbstractDocument;
import org.apache.batik.dom.traversal.TraversalSupport;
import org.apache.batik.swing.JSVGCanvas;
import org.apache.batik.swing.gvt.GVTTreeRendererAdapter;
import org.apache.batik.swing.gvt.GVTTreeRendererEvent;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;
import org.w3c.dom.svg.SVGDocument;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.TreeWalker;
import resources.Messages;

import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;
import java.util.*;
import java.util.List;

/**
 * WARNING: this class modifies independent attributes of the XML nodes to indicate the selection
 * One alternative solution would be to modify directly the "style" attribute where we could change
 * many values (color, stroke-width...) at one time
 */

public class ImageWithClickableParts implements EventListener {

    private static final String SENSITIVE_ZONE_IDENTIFIER = "id";
    /*---------default colors----------*/
    private static final String DEFAULT_FILL_PROPERTY_VALUE_RED = "red";

    private static final String DEFAULT_STROKE = "stroke:#000000";
    private static final String SELECTED_STROKE = "stroke:#000000";
    private static final String DEFAULT_STROKE_WIDTH = "stroke-width:1.20000005";
    private static final String SELECTED_STROKE_WIDTH = "stroke-width:5";
    private static final String DEFAULT_FILL = "fill:#ffffff";
    /*<------------------------------->*/
    private static final String STYLE_PROPERTY = "style";
    private static final String FILL_PROPERTY = "fill";
    private static final String FILL_PROPERTY_HIGHLIGHTED = "rgb(0,0,255)";//blu
    private static final String DEFAULT_UNITS_FOOTER = "_units";
    private static final String DEFAULT_TEXT_FOOTER = "_text";
    private List<Element> territoriesUnitsNodes;
    private List<String> territories;
    private JSVGCanvas svgCanvas = new JSVGCanvas();
    private Map<ObjectAndProperty<Element>, String> nodeAndProperty2Value = new HashMap<>();
    private List<SelectedListener> listeners = new ArrayList<>();
    private Map<String, Element> territoryElementMap;
    private Map<String, Element> territoryUnitsMap;

    public ImageWithClickableParts(File svgFile, List territories) {
        this.territories = new ArrayList<>(territories);
        this.territoryElementMap = new HashMap<>();
        this.territoryUnitsMap = new HashMap<>();
        createComponents(svgFile);
    }

    public Component getAsComponent() {
        return svgCanvas;
    }

    public void createComponents(File svgFile) {
        try {
            svgCanvas.setDocumentState(JSVGCanvas.ALWAYS_DYNAMIC);
            svgCanvas.setURI(svgFile.toURI().toURL().toString());
            svgCanvas.enableInputMethods(true);
            svgCanvas.addGVTTreeRendererListener(new GVTTreeRendererAdapter() {

                @Override
                public void gvtRenderingPrepare(GVTTreeRendererEvent e) {
                }

                @Override
                public void gvtRenderingCompleted(GVTTreeRendererEvent e) {
                    addListenersToSensitiveZones();
                    listeners.get(0).initializeSVG();
                }
            });
        } catch (MalformedURLException e) {
            System.out.println(Messages.ERROR_LOADING_SVG + e);
        }

    }

    protected void addListenersToSensitiveZones() {
        List<Node> allSensitiveZones = getAllSensitiveZones();
        this.territoriesUnitsNodes = new ArrayList<>();
        for (Node sensitiveZone : allSensitiveZones) {
            Element elt = (Element) sensitiveZone;
            String elementId = elt.getAttribute("id");
            for (String territory : this.territories) {
                if (elementId.contains(territory) && (!elementId.contains(DEFAULT_TEXT_FOOTER))) {
                    if (elementId.contains(DEFAULT_UNITS_FOOTER))
                        this.territoryUnitsMap.put(territory, elt);
                    else {
                        //aggiungo i listener ai territori
                        this.territoryElementMap.put(territory, elt);
                        EventTarget target = (EventTarget) elt;
                        target.addEventListener("click", this, false);
                        target.addEventListener("mouseover", this, false);
                        target.addEventListener("mouseout", this, false);
                    }
                    break;
                }
            }
        }

    }


    private void toggleHighlight(Element sensitiveZone) {
        cleanAttributes(sensitiveZone);
        ObjectAndProperty<Element> objAndPropertyFill = new ObjectAndProperty<Element>();
        String savedFillPropertyValue = nodeAndProperty2Value.get(objAndPropertyFill);
        NamedNodeMap zoneAttributes = sensitiveZone.getAttributes();
        Node fillProperty = zoneAttributes.getNamedItem(FILL_PROPERTY);
        for (Element element : this.territoriesUnitsNodes) {

            int units = Integer.parseInt(element.getFirstChild().getFirstChild().getNodeValue());
            units++;
            element.getFirstChild().getFirstChild().setNodeValue(Integer.toString(units));
        }
        if (savedFillPropertyValue == null) {
            backupOriginalPropertyAndApplyNewProperty(fillProperty, objAndPropertyFill, FILL_PROPERTY_HIGHLIGHTED);
        } else {
            restoreOriginalProperty(fillProperty, objAndPropertyFill, savedFillPropertyValue);
        }
    }


    private void restoreOriginalProperty(Node namedItem, ObjectAndProperty<Element> objAndProperty, String savedPropertyValue) {
        namedItem.setNodeValue(savedPropertyValue);
        nodeAndProperty2Value.remove(objAndProperty);
    }

    private void backupOriginalPropertyAndApplyNewProperty(Node namedItem, ObjectAndProperty<Element> objAndProperty, String highlightedValue) {
        String targetOriginalPropertyValue = namedItem.getNodeValue();
        nodeAndProperty2Value.put(objAndProperty, targetOriginalPropertyValue);
        namedItem.setNodeValue(highlightedValue);
    }

    /**
     * clean the attributes of this Element so that we can override some and set some values.
     * Unexisting attributes but mandatory for us are created, problematic ('style' !) ones are deleted
     */
    protected void cleanAttributes(Element sensitiveZone) {

        // remove the style so we are able to override it using full blown attributes instead of concatenated stuffs in the 'style' attribute
        /*if (zoneAttributes.getNamedItem(STYLE_PROPERTY) != null) {
            System.out.println("Setting default {}" + STYLE_PROPERTY);
            sensitiveZone.setAttribute(STYLE_PROPERTY, DEFAULT_STYLE_PROPERTY_VALUE);
        }
        */
        // If no FILL_PROPERTY defined, initialize it to a color by default
        //if (zoneAttributes.getNamedItem(FILL_PROPERTY) == null) {
        System.out.println("Setting default {}" + FILL_PROPERTY);
        sensitiveZone.setAttribute(FILL_PROPERTY, DEFAULT_FILL_PROPERTY_VALUE_RED);
        //}


    }

    private List<Node> getAllSensitiveZones() {
        List<Node> oleaNodes = new ArrayList<>();
        SVGDocument svgDocument = svgCanvas.getSVGDocument();
        NodeFilter nodeFilter = createSensitiveZonesNodeFilter();
        TreeWalker treeWalker = TraversalSupport.createTreeWalker((AbstractDocument) svgDocument,
                svgDocument.getRootElement(), NodeFilter.SHOW_ALL, nodeFilter, true);
        Node currNode = treeWalker.nextNode();
        while (currNode != null) {
            oleaNodes.add(currNode);
            currNode = treeWalker.nextNode();
        }
        return oleaNodes;
    }

    /**
     * @return a node filter able to identify nodes containing sensitive zones
     */
    private NodeFilter createSensitiveZonesNodeFilter() {
        return new NodeFilter() {

            @Override
            public short acceptNode(Node node) {
                NamedNodeMap attributes = node.getAttributes();
                if (attributesContainSpecificSensitgiveZonesAttribute(attributes)) {
                    return FILTER_ACCEPT;
                } else {
                    return FILTER_SKIP;
                }
            }

            protected boolean attributesContainSpecificSensitgiveZonesAttribute(NamedNodeMap attributes) {
                return attributes != null
                        && attributes.getNamedItem(SENSITIVE_ZONE_IDENTIFIER) != null;
            }
        };
    }

    @Override
    public void handleEvent(Event evt) {
        String id;
        String type = evt.getType();
        Element sensitiveZone = (Element) evt.getTarget();
        id = sensitiveZone.getAttribute("id");
        if (type.equals("click")) {
            listeners.get(0).updateUi(id);
        }
        else if (type.equals("mouseover")) {
            this.strokeTerritory(id);
            listeners.get(0).printSvgState(id);
        } else if (type.equals("mouseout")) {
            this.unstrokeTerritory(id);
            listeners.get(0).printSvgState("null");

        }
    }

    /**
     * @return an object describing the current state of the selected parts
     *//*
    private SelectedPartsState createCurrentSelectedPartsState() {
        Set<String> selectedKeys = new HashSet<String>();
        for (ObjectAndProperty<Element> objAndProp : nodeAndProperty2Value.keySet()) {
            Element element = objAndProp.getObject();
            String key = element.getAttributes().getNamedItem(SENSITIVE_ZONE_IDENTIFIER).getNodeValue();
            selectedKeys.add(key);
        }

        SelectedPartsState state = new SelectedPartsState();
        //state.setSelectedPartsKeys(selectedKeys);
        return state;
    }

    private void notifyListeners(String zoneKey, boolean isSelectedNow) {
        SelectedPartsState state = createCurrentSelectedPartsState();
        for (SelectedPartListener listener : listeners) {
            if (isSelectedNow) {
                listener.partSelected(zoneKey, state);
            } else {
                listener.partUnSelected(zoneKey, state);
            }
        }
    }*/
    /*--------------------> START Manipulate-Territory Method s<--------------------*/
    public void selectTerritory(String territory) {
        Element terr = territoryElementMap.get(territory);
        String style = terr.getAttribute(STYLE_PROPERTY);
        style = style.replaceFirst(DEFAULT_STROKE_WIDTH, SELECTED_STROKE_WIDTH);
        style = style.replaceFirst(DEFAULT_STROKE, SELECTED_STROKE);
        style = style.replaceFirst("fill-opacity:1", "fill-opacity:0.5");
        terr.setAttribute(STYLE_PROPERTY, style);
    }

    public void strokeTerritory(String territory) {
        Element terr = territoryElementMap.get(territory);
        String style = terr.getAttribute(STYLE_PROPERTY);
        style = style.replaceFirst(DEFAULT_STROKE_WIDTH, SELECTED_STROKE_WIDTH);
        style = style.replaceFirst(DEFAULT_STROKE, SELECTED_STROKE);
        terr.setAttribute(STYLE_PROPERTY, style);
    }

    public void unstrokeTerritory(String territory) {
        Element terr = territoryElementMap.get(territory);
        String style = terr.getAttribute(STYLE_PROPERTY);
        style = style.replaceFirst(SELECTED_STROKE_WIDTH, DEFAULT_STROKE_WIDTH);
        style = style.replaceFirst(SELECTED_STROKE, DEFAULT_STROKE);
        terr.setAttribute(STYLE_PROPERTY, style);
    }

    public void deselectTerritory(String territory) {
        Element terr = territoryElementMap.get(territory);
        String style = terr.getAttribute(STYLE_PROPERTY);
        style = style.replaceFirst(SELECTED_STROKE_WIDTH, DEFAULT_STROKE_WIDTH);
        style = style.replaceFirst(SELECTED_STROKE, DEFAULT_STROKE);
        style = style.replaceFirst("fill-opacity:0.5", "fill-opacity:1");
        terr.setAttribute(STYLE_PROPERTY, style);
    }

    public void setTerritoryColor(String territory, Color color) {
        Element terr = territoryElementMap.get(territory);
        String fill = terr.getAttribute(STYLE_PROPERTY);
        fill = fill.replaceFirst(DEFAULT_FILL,
                "fill: " + String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue()));
        terr.setAttribute(STYLE_PROPERTY, fill);
    }


    public void resetTerritoryColor(String territory) {
        Element terr = territoryElementMap.get(territory);
        String fill = terr.getAttribute(STYLE_PROPERTY);
        fill = fill.replaceFirst("fill:[^;]+", DEFAULT_FILL);
        terr.setAttribute(STYLE_PROPERTY, fill);
    }

    public void setUnits(String territory, int units) {
        Element territoryUnits = territoryUnitsMap.get(territory);

        territoryUnits.getFirstChild().getFirstChild().setNodeValue(Integer.toString(units));

    }

    public void incrementUnits(String territory) {
        Element territoryUnits = territoryUnitsMap.get(territory);
        int units = Integer.parseInt(territoryUnits.getFirstChild().getFirstChild().getNodeValue());
        setUnits(territory, ++units);
    }

    /*--------------------> END Manipulate-Territory Method s<--------------------*/

    public void registerEventHeader(SelectedListener listener) {
        listeners.add(listener);
    }
}

class ObjectAndProperty<T> {

    private T object;
    private String property;

    public T getObject() {
        return object;
    }

}
