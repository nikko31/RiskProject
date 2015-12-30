package risk.gui;

import risk.GameState;
import risk.Phases;
import risk.RiskLogic;
import risk.board.Territory;
import risk.operations.*;
import risk.operations.Error;
import risk.player.Player;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


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
     */

    private void initComponents(String default_map) {

        JPanel gamePnl = new JPanel();
        JPanel selTerritoriesPnl = new JPanel();
        JButton nextBtn = new JButton();
        JButton missionBtn = new JButton();
        phaseLbl = new JLabel();
        playerLbl = new JLabel();
        JLabel freeUnitsLbl = new JLabel();
        JLabel territory1Lbl = new JLabel("Territory 1: ");
        JLabel territory2Lbl = new JLabel("Territory 2: ");
        troupsLbl = new JLabel();

        setLayout(new BorderLayout());

        nextBtn.setText("Next Phase");
        nextBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nextBtnMouseClicked();
            }
        });
        phaseLbl.setForeground(Color.black);
        phaseLbl.setText("Start");
        missionBtn.setText("MISSION");
        missionBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                missionBtnClicked();
            }
        });


        playerLbl.setForeground(new Color(171, 10, 10));
        playerLbl.setText("Player");

        freeUnitsLbl.setText("Free Troups: ");

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
                                                .addComponent(freeUnitsLbl)
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
                                                        .addComponent(freeUnitsLbl)
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
        stateImage1=new StateImage(new File(NULL_SVG));
        SvgState1 = stateImage1.getAsComponent();
        stateImage2=new StateImage(new File(NULL_SVG));
        SvgState2 = stateImage2.getAsComponent();
        SvgState1.setBackground(Color.gray);
        SvgState2.setBackground(Color.gray);
        selTerritoriesPnl.setLayout(new BoxLayout(selTerritoriesPnl,BoxLayout.Y_AXIS));

        selTerritoriesPnl.add(territory1Lbl);
        selTerritoriesPnl.add(SvgState1);
        selTerritoriesPnl.add(territory2Lbl);
        selTerritoriesPnl.add(SvgState2);

        selTerritoriesPnl.setBackground(Color.gray);
        /*ImageWithClickableParts app2 = new ImageWithClickableParts(
                new File("/home/DarkLinux/IdeaProjects/RiskProject/src/resources/states/alaska.svg"),
                Arrays.asList("alaska"));
        app.registerEventHeader(this);
        Component svgImage2 = app2.getAsComponent();
        svgImage2.setBackground(Color.darkGray);

        add(svgImage2, BorderLayout.WEST);*/
        add(selTerritoriesPnl, BorderLayout.WEST);
        add(gamePnl, BorderLayout.SOUTH);
        add(svgImage, BorderLayout.CENTER);
    }

    private void missionBtnClicked() {
        Operation operation = riskLogic.missionBtn();
        JOptionPane.showMessageDialog(
                this.gameFrame, ((Mission) operation).operationString(), "MISSION", JOptionPane.INFORMATION_MESSAGE
        );

    }

    private void nextBtnMouseClicked() {
        /*@TODO riskLogic.nextPhase();*/

        List<Operation> operationsPhase;
        operationsPhase = riskLogic.nextPhase();
        for (Operation operation : operationsPhase) {

            switch (riskLogic.getGameState().getLastphase()) {
                case INITIAL: {
                    if (operation instanceof NewPhase) {
                        this.phaseLbl.setText(operation.operationString());
                    }
                    this.revalidate();
                    this.repaint();
                    break;
                }
                case BONUS: {
                    if (operation instanceof NewPhase) {
                        this.phaseLbl.setText(operation.operationString());
                    }
                    if (operation instanceof UnitsBonus) {
                        System.out.println(operation.operationString());
                        this.troupsLbl.setText(Integer.toString(gameState.getCurrentPlayerTurn().getFreeUnits()));
                    }
                    this.revalidate();
                    this.repaint();

                    break;
                }
                case FORTIFY: {
                    if (operation instanceof NewPhase) {
                        this.phaseLbl.setText(operation.operationString());
                    }
                    this.revalidate();
                    this.repaint();

                    break;
                }
                case ATTACK: {
                    if (operation instanceof NewPhase) {
                        this.phaseLbl.setText(operation.operationString());
                    }
                    if (operation instanceof TerritoryUnselected) {
                        app.deselectTerritory(((TerritoryUnselected) operation).getUnselected().getTerritoryName());
                    }
                    this.revalidate();
                    this.repaint();
                    break;
                }
                case MOVE: {
                    if (operation instanceof NewPhase) {
                        this.phaseLbl.setText(operation.operationString());
                    }
                    if (operation instanceof TerritoryUnselected) {
                        app.deselectTerritory(((TerritoryUnselected) operation).getUnselected().getTerritoryName());
                    }
                    this.revalidate();
                    this.repaint();

                    break;
                }
                case END_TURN: {
                    if (operation instanceof NewPhase) {
                        this.phaseLbl.setText(operation.operationString());
                        this.playerLbl.setText(riskLogic.getGameState().getCurrentPlayerTurn().getPlayerName());
                        this.playerLbl.setForeground(riskLogic.getGameState().getCurrentPlayerTurn().getPlayerColor());
                        this.troupsLbl.setText(Integer.toString(gameState.getCurrentPlayerTurn().getFreeUnits()));
                    }
                    if (operation instanceof Victory) {
                        JOptionPane.showMessageDialog(
                                this.gameFrame, (((Victory) operation).getPlayer().getPlayerName()), "WINNER", JOptionPane.WARNING_MESSAGE
                        );

                    }
                    this.revalidate();
                    this.repaint();

                    break;
                }

                default:
                    throw new IllegalArgumentException("Invalid card!");
            }
        }

    }


    private JLabel phaseLbl;
    private JLabel playerLbl;
    private JLabel troupsLbl;
    private JFrame gameFrame;
    // End of variables declaration
    private GameState gameState;
    private ImageWithClickableParts app;
    private Component SvgState1;
    private Component SvgState2;
    private RiskLogic riskLogic;
    private StateImage stateImage1;
    private StateImage stateImage2;

    private final static String RESOURCES = "src/resources/";
    private final static String NULL_SVG = "src/resources/states/null.svg";

    @Override
    public void updateUi(String territory) {

        System.out.println("Click on " + territory);

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
                    stateImage1.createComponents(new File(RESOURCES + "/states/" + territory + ".svg"));
                    SvgState1=stateImage1.getAsComponent();
                }
                if (operation instanceof TerritoryUnselected) {
                    app.deselectTerritory(((TerritoryUnselected) operation).getUnselectedName());
                    StateImage stateImage=new StateImage(new File(NULL_SVG));
                    SvgState1=stateImage.getAsComponent();
                }
                if (operation instanceof Attack) {
                    app.selectTerritory(((Attack) operation).getToName());
                    app.setUnits(((Attack) operation).getToName(), ((Attack) operation).getToUnits());
                    app.setUnits(((Attack) operation).getFromName(), ((Attack) operation).getFromUnits());
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
                    app.setTerritoryColor(((AttackConquest) operation).getToName(), ((AttackConquest) operation).getColor());


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
                    operation = riskLogic.makeMove(territory);
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
            app.setUnits(entry.getKey().getTerritoryName(), 1);
        }
    }

    @Override
    public void printSvgState(String territory) {

        if(gameState.getAttackFrom()==null) {
            stateImage1.createComponents(new File(RESOURCES + "/states/" + territory + ".svg"));
            SvgState1 = stateImage1.getAsComponent();

        }
        else {
            stateImage2.createComponents(new File(RESOURCES + "/states/" + territory + ".svg"));
            SvgState2 = stateImage2.getAsComponent();

        }
        SvgState1.repaint();SvgState1.repaint();
    }
}