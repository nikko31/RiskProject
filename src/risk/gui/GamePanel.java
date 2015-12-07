package risk.gui;

import org.w3c.dom.events.*;
import org.w3c.dom.events.Event;
import risk.GameResources;
import risk.GameState;
import risk.Phases;
import risk.RiskLogic;
import risk.board.Territory;
import risk.operations.*;
import risk.operations.Error;
import risk.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.*;
import java.util.List;


/**
 * @author nikko31
 */
public class GamePanel extends JPanel implements SelectedListener {

    public GamePanel(String default_map, GameState gameState, JFrame gameFrame) {
        this.gameState = gameState;
        initComponents(default_map);
        this.gameFrame = gameFrame;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     *
     * @param default_map
     */

    private void initComponents(String default_map) {

        gamePnl = new JPanel();
        nextBtn = new JButton();
        phaseLbl = new JLabel();
        playerLbl = new JLabel();
        jLabel1 = new JLabel();
        troupsLbl = new JLabel();

        setLayout(new BorderLayout());

        nextBtn.setText("Next Phase");
        nextBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nextBtnMouseClicked(evt);
            }
        });
        phaseLbl.setForeground(Color.black);
        phaseLbl.setText("Start");

        playerLbl.setForeground(new Color(171, 10, 10));
        playerLbl.setText("Player");

        jLabel1.setText("Free Troups: ");

        troupsLbl.setForeground(Color.black);
        troupsLbl.setText("45");

        GroupLayout gamePnlLayout = new GroupLayout(gamePnl);
        gamePnl.setLayout(gamePnlLayout);
        gamePnlLayout.setHorizontalGroup(
                gamePnlLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, gamePnlLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gamePnlLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(playerLbl)
                                        .addGroup(gamePnlLayout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(troupsLbl)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 461, Short.MAX_VALUE)
                                .addGroup(gamePnlLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(phaseLbl)
                                        .addComponent(nextBtn))
                                .addContainerGap())
        );
        gamePnlLayout.setVerticalGroup(
                gamePnlLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, gamePnlLayout.createSequentialGroup()
                                .addContainerGap(26, Short.MAX_VALUE)
                                .addGroup(gamePnlLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(phaseLbl)
                                        .addComponent(playerLbl))
                                .addGroup(gamePnlLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(gamePnlLayout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(nextBtn))
                                        .addGroup(gamePnlLayout.createSequentialGroup()
                                                .addGap(9, 9, 9)
                                                .addGroup(gamePnlLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel1)
                                                        .addComponent(troupsLbl))))
                                .addContainerGap())
        );

        this.riskLogic = new RiskLogic(gameState);
        File svgFile = new File(default_map);
        List<String> territories = new ArrayList<>();
        for (Territory territory : gameState.getTerritoriesPlayersMap().keySet()) {
            territories.add(territory.getTerritoryName());
        }
        app = new ImageWithClickableParts(svgFile, territories);
        app.registerEventHeader(this);
        Component svgImage = app.getAsComponent();
        svgImage.setBackground(Color.darkGray);


        this.troupsLbl.setText(Integer.toString(gameState.getCurrentPlayerTurn().getFreeUnits()));
        this.playerLbl.setText(gameState.getCurrentPlayerTurn().getPlayerName());
        this.phaseLbl.setText(gameState.getPhase().toString());

        add(gamePnl, BorderLayout.SOUTH);
        add(svgImage, BorderLayout.CENTER);
    }

    private void nextBtnMouseClicked(MouseEvent evt) {
        /*@TODO riskLogic.nextPhase();*/

        List<Operation> operationsPhase = null;
        operationsPhase = riskLogic.nextPhase();
        for(Operation operation : operationsPhase){

            switch(riskLogic.getGameState().getLastphase()) {
                case INITIAL: {
                    if(operation instanceof NewPhase){
                        this.phaseLbl.setText(operation.operationString());
                    }
                    this.revalidate();
                    this.repaint();
                    break;
                }
                case BONUS: {
                    if(operation instanceof NewPhase){
                        this.phaseLbl.setText(operation.operationString());
                    }
                    if(operation instanceof UnitsBonus){
                        System.out.println(operation.operationString());
                        this.troupsLbl.setText(Integer.toString(gameState.getCurrentPlayerTurn().getFreeUnits()));
                    }
                    this.revalidate();
                    this.repaint();

                    break;
                }
                case FORTIFY: {
                    if(operation instanceof NewPhase){
                        this.phaseLbl.setText(operation.operationString());
                    }
                    this.revalidate();
                    this.repaint();

                    break;
                }
                case ATTACK: {
                    if(operation instanceof NewPhase){
                        this.phaseLbl.setText(operation.operationString());
                    }
                    if(operation instanceof TerritoryUnselected){
                        app.deselectTerritory(((TerritoryUnselected) operation).getUnselected().getTerritoryName());
                    }
                    this.revalidate();
                    this.repaint();
                    break;
                }
                case MOVE: {
                    if(operation instanceof NewPhase){
                        this.phaseLbl.setText(operation.operationString());
                    }
                    if(operation instanceof TerritoryUnselected){
                        app.deselectTerritory(((TerritoryUnselected) operation).getUnselected().getTerritoryName());
                    }
                    this.revalidate();
                    this.repaint();

                    break;
                }
                case END_TURN: {
                    if(operation instanceof NewPhase){
                        this.phaseLbl.setText(operation.operationString());
                        this.playerLbl.setText(riskLogic.getGameState().getCurrentPlayerTurn().getPlayerName());
                        this.playerLbl.setForeground(riskLogic.getGameState().getCurrentPlayerTurn().getPlayerColor());
                        this.troupsLbl.setText(Integer.toString(gameState.getCurrentPlayerTurn().getFreeUnits()));
                    }
                    if(operation instanceof Victory){
                        JOptionPane.showMessageDialog(
                                this.gameFrame, (((Victory) operation).getPlayer().getPlayerName()), "WINNER", JOptionPane.WARNING_MESSAGE
                        );

                    }
                    this.revalidate();
                    this.repaint();

                    break;
                }

                default: throw new IllegalArgumentException("Invalid card!");
            }
        }

    }


    // Variables declaration - do not modify                     
    private JPanel gamePnl;
    private JLabel jLabel1;
    private JButton nextBtn;
    private JLabel phaseLbl;
    private JLabel playerLbl;
    private JLabel troupsLbl;
    private JFrame gameFrame;
    // End of variables declaration
    private GameState gameState;
    private ImageWithClickableParts app;
    private RiskLogic riskLogic;


    @Override
    public void updateUi(String territory) {

        System.out.println("Click on " + territory);
        /*app.setTerritoryColor(territory, Color.green);
        app.incrementUnits(territory);
        if (this.selectedTerritories.contains(territory)) {
            app.deselectTerritory(territory);
            this.selectedTerritories.remove(territory);
            app.resetTerritoryColor(territory);
        } else {
            app.selectTerritory(territory);
            this.selectedTerritories.add(territory);
        }*/
        Operation operation = riskLogic.makeMove(territory);
        Phases phase = riskLogic.getGameState().getPhase();
        switch (phase) {
            case INITIAL:
                if (operation instanceof Fortify) {
                    app.incrementUnits(((Fortify) operation).getFortify().getTerritoryName());
                    this.troupsLbl.setText(Integer.toString(gameState.getCurrentPlayerTurn().getFreeUnits()));
                }
                if (operation instanceof Error) {
                    JOptionPane.showMessageDialog(
                            this.gameFrame, ((Error) operation).getErrorStr(), "ERROR", JOptionPane.ERROR_MESSAGE
                    );
                }
                break;
            case BONUS:
                break;
            case FORTIFY:
                if (operation instanceof Fortify) {
                    app.incrementUnits(((Fortify) operation).getFortify().getTerritoryName());
                    this.troupsLbl.setText(Integer.toString(gameState.getCurrentPlayerTurn().getFreeUnits()));
                }
                if (operation instanceof Error) {
                    JOptionPane.showMessageDialog(
                            this.gameFrame, ((Error) operation).getErrorStr(), "ERROR", JOptionPane.ERROR_MESSAGE
                    );
                }
                break;
            case ATTACK:
                if (operation instanceof TerritorySelected) {
                    app.selectTerritory(((TerritorySelected) operation).getSelectedName());
                }
                if (operation instanceof TerritoryUnselected) {
                    app.deselectTerritory(((TerritoryUnselected) operation).getUnselectedName());
                }
                if (operation instanceof Attack) {
                    app.selectTerritory(((Attack) operation).getToName());
                    app.setUnits(((Attack) operation).getToName(), ((Attack) operation).getToUnits());
                    app.setUnits(((Attack) operation).getFromName(),((Attack) operation).getFromUnits());
                    app.deselectTerritory(((Attack) operation).getToName());
                    app.deselectTerritory(((Attack) operation).getFromName());

                }
                if (operation instanceof AttackConquest) {
                    //app.resetTerritoryColor(((AttackConquest) operation).getToName());
                    app.selectTerritory(((AttackConquest) operation).getToName());
                    app.setUnits(((AttackConquest) operation).getToName(), ((AttackConquest) operation).getToUnits());
                    app.setUnits(((AttackConquest) operation).getFromName(), ((AttackConquest) operation).getFromUnits());
                    app.deselectTerritory(((AttackConquest) operation).getToName());
                    app.deselectTerritory(((AttackConquest) operation).getFromName());
                    app.resetTerritoryColor(((AttackConquest) operation).getToName());
                    app.setTerritoryColor(((AttackConquest) operation).getToName(),((AttackConquest) operation).getColor());


                }
                if (operation instanceof Error) {
                    JOptionPane.showMessageDialog(
                            this.gameFrame, ((Error) operation).getErrorStr(), "ERROR", JOptionPane.ERROR_MESSAGE
                    );
                }
                break;
            case MOVE:
                if (operation instanceof TerritorySelected) {
                    app.selectTerritory(((TerritorySelected) operation).getSelectedName());
                }
                if (operation instanceof TerritoryUnselected) {
                    app.deselectTerritory(((TerritoryUnselected) operation).getUnselectedName());
                }

                if (operation instanceof Move) {
                    app.selectTerritory(((Move) operation).getToName());
                    app.deselectTerritory(((Move) operation).getToName());
                    app.deselectTerritory(((Move) operation).getFromName());
                    app.setUnits(((Move) operation).getFromName(), ((Move) operation).getFromUnits());
                    app.setUnits(((Move) operation).getToName(), ((Move) operation).getToUnits());

                }
                if (operation instanceof Error) {
                    JOptionPane.showMessageDialog(
                            this.gameFrame, ((Error) operation).getErrorStr(), "ERROR", JOptionPane.ERROR_MESSAGE
                    );
                }

                break;
            case END_TURN:
                if (operation instanceof Move) {
                    app.selectTerritory(((Move) operation).getToName());
                    app.deselectTerritory(((Move) operation).getToName());
                    app.deselectTerritory(((Move) operation).getFromName());
                    app.setUnits(((Move) operation).getFromName(), ((Move) operation).getFromUnits());
                    app.setUnits(((Move) operation).getToName(), ((Move) operation).getToUnits());
                    this.phaseLbl.setText("END TURN");

                }
                break;
        }
        System.out.println(operation.operationString());
    }

    @Override
    public void initializeSVG() {
        for (Map.Entry<Territory, Player> entry : gameState.getTerritoriesPlayersMap().entrySet()) {
            app.setTerritoryColor(entry.getKey().getTerritoryName(), entry.getValue().getPlayerColor());
            app.setUnits(entry.getKey().getTerritoryName(),1);
        }
    }
}