package risk;

import risk.gui.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Game extends JFrame implements InitOptionListener, SelectedPartListener {
    private final static String RESOURCES = "src/resources/";
    private String default_map;

    public Game(String default_map, String default_bg) throws IOException {
        this.default_map = default_map;
        MenuPanel menuPnl = new MenuPanel(default_bg);
        menuPnl.setListener(this);
        this.add(menuPnl);
        this.setSize(1000, 600);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);


    }

    public void runGame(int humans, int bots) {
        //RiskLogic riskLogic = new RiskLogic(humans,bots);
        getContentPane().removeAll();
        this.setLayout(new BorderLayout());
        GamePanel gamePnl =new GamePanel();
        File svgFile = new File(default_map);
        //lista prova stati
        List<String >terr=new ArrayList<>();
        terr.add("alaska");
        terr.add("venezuela");
        //
        ImageWithClickableParts app = new ImageWithClickableParts(svgFile,terr);
        app.registerSelectedPartListener(this);
        Component svgImage = app.getAsComponent();
        this.add(gamePnl, BorderLayout.SOUTH);
        this.repaint();
        printAll(getGraphics());
        this.add(svgImage, BorderLayout.CENTER);
        gamePnl.requestFocusInWindow();
        this.pack();
    }

    @Override
    public void playersNumber(int a, int b) {
        System.out.println("Number of players= " + a + " hum " + b + " bot.");
        if ((a == 0 && b == 0) || (a + b < 2) || (a + b > 6)) {
            JOptionPane.showMessageDialog(this,
                    "Error number players selected",
                    "warning",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            this.runGame(a, b);
        }
    }

    public static void main(String[] args) throws IOException {
        String default_map = RESOURCES + "Risk_board.svg";
        String default_bg = RESOURCES + "Risk_bg.jpg";
        Game game = new Game(default_map, default_bg);
    }

    @Override
    public void partSelected(String partKey, SelectedPartsState event) {

    }

    @Override
    public void partUnSelected(String partKey, SelectedPartsState event) {

    }
}