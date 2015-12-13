package risk;


import risk.gui.GamePanel;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;


public class StartGame extends JFrame {

    /**
     * Creates new form StartGame
     */
    public StartGame() throws IOException {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */

    private void initComponents() throws IOException {

        ImageIcon iconA = new ImageIcon(RESOURCES + "next.png");
        JPanel menuPnl = new JPanel();
        JTextField playerName2 = new JTextField();
        JCheckBox bot2 = new JCheckBox();
        JTextField playerName1 = new JTextField();
        JCheckBox bot1 = new JCheckBox();
        JTextField playerName3 = new JTextField();
        JCheckBox bot3 = new JCheckBox();
        JButton startBtn = new JButton("Start Game", iconA);
        JLabel namesLbl = new JLabel();
        JTextField playerName4 = new JTextField();
        JCheckBox bot4 = new JCheckBox();
        JTextField playerName5 = new JTextField();
        JCheckBox bot5 = new JCheckBox();
        JTextField playerName6 = new JTextField();
        JCheckBox bot6 = new JCheckBox();

        BufferedImage myImage = ImageIO.read(new File(RESOURCES + "Risk_bg.jpg"));
        menuPnl.setOpaque(false);
        setContentPane(new ImagePanel(myImage));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        playerName2.setBackground(Color.decode("#0000FF"));

        bot2.setText("AI");

        playerName1.setBackground(Color.RED);
        playerName1.setToolTipText("");

        bot1.setText("AI");

        playerName3.setBackground(Color.decode("#00FF00"));

        bot3.setText("AI");

        startBtn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                startBtnMouseClicked();
            }
        });

        namesLbl.setForeground(new Color(201, 1, 1));
        namesLbl.setText("Players Names");

        playerName4.setBackground(Color.yellow);
        playerName4.setToolTipText("");

        bot4.setText("AI");

        playerName5.setBackground(Color.decode("#FF33FF"));

        bot5.setText("AI");

        playerName6.setBackground(Color.cyan);

        bot6.setText("AI");
        bot1.setOpaque(false);
        bot2.setOpaque(false);
        bot3.setOpaque(false);
        bot4.setOpaque(false);
        bot5.setOpaque(false);
        bot6.setOpaque(false);
        bot1.setForeground(Color.black);
        bot2.setForeground(Color.black);
        bot3.setForeground(Color.black);
        bot4.setForeground(Color.black);
        bot5.setForeground(Color.black);
        bot6.setForeground(Color.black);

        menuPnl.setOpaque(false);
        GroupLayout menuPnlLayout = new GroupLayout(menuPnl);
        menuPnl.setLayout(menuPnlLayout);
        menuPnlLayout.setHorizontalGroup(
                menuPnlLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(menuPnlLayout.createSequentialGroup()
                                .addGroup(menuPnlLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(menuPnlLayout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(startBtn))
                                        .addGroup(menuPnlLayout.createSequentialGroup()
                                                .addGroup(menuPnlLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(GroupLayout.Alignment.TRAILING, menuPnlLayout.createSequentialGroup()
                                                                .addGroup(menuPnlLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(playerName1)
                                                                        .addComponent(playerName2)
                                                                        .addComponent(playerName3, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(menuPnlLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addComponent(bot1)
                                                                        .addComponent(bot2)
                                                                        .addComponent(bot3)))
                                                        .addGroup(menuPnlLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(namesLbl)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 57, GroupLayout.PREFERRED_SIZE)))
                                                .addGap(18, 18, 18)
                                                .addGroup(menuPnlLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(playerName4)
                                                        .addComponent(playerName5)
                                                        .addComponent(playerName6, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(menuPnlLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(bot4)
                                                        .addComponent(bot5)
                                                        .addComponent(bot6))
                                                .addGap(0, 145, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        menuPnlLayout.setVerticalGroup(
                menuPnlLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, menuPnlLayout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(namesLbl)
                                .addGap(18, 18, 18)
                                .addGroup(menuPnlLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(menuPnlLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(menuPnlLayout.createSequentialGroup()
                                                        .addComponent(bot1)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(bot2)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(bot3))
                                                .addGroup(GroupLayout.Alignment.TRAILING, menuPnlLayout.createSequentialGroup()
                                                        .addComponent(playerName1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(playerName2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(playerName3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(menuPnlLayout.createSequentialGroup()
                                                .addGroup(menuPnlLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(bot4)
                                                        .addComponent(playerName4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(menuPnlLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(bot5)
                                                        .addComponent(playerName5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(menuPnlLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(bot6)
                                                        .addComponent(playerName6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                                .addGap(20, 20, 20)
                                .addComponent(startBtn)
                                .addContainerGap())
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(menuPnl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 228, Short.MAX_VALUE)
                                .addComponent(menuPnl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );

        pack();
        this.names = new LinkedList<>();
        this.names.add(playerName1);
        this.names.add(playerName2);
        this.names.add(playerName3);
        this.names.add(playerName4);
        this.names.add(playerName5);
        this.names.add(playerName6);
        this.bots = new LinkedList<>();
        this.bots.add(bot1);
        this.bots.add(bot2);
        this.bots.add(bot3);
        this.bots.add(bot4);
        this.bots.add(bot5);
        this.bots.add(bot6);
    }

    private void startBtnMouseClicked() {

        List<String> bot_names = new LinkedList<>();
        List<String> human_names = new LinkedList<>();
        List<Color> colors = new LinkedList<>();
        GameState gameState;
        int counter = 0;
        for (JTextField name : this.names) {
            if (!name.getText().equals("")) {
                if (this.bots.get(counter).isSelected())
                    bot_names.add(name.getText());
                else
                    human_names.add(name.getText());
                colors.add(name.getBackground());
            }
            counter++;
        }
        if (human_names.size() + bot_names.size() < 2 || human_names.size() + bot_names.size() > 6)
            JOptionPane.showMessageDialog(this,
                    "Error number players selected",
                    "warning",
                    JOptionPane.ERROR_MESSAGE);
        else {
            System.out.println("HUMAN PLAYERS NAMES: " + human_names.toString() + " BOT PLAYERS NAMES: " + bot_names.toString());
            getContentPane().removeAll();
            gameState = new GameState(colors, human_names, bot_names);
            this.setLayout(new BorderLayout());
            this.getContentPane().add(new GamePanel(RESOURCES + "Risk_board.svg", gameState,this));
            this.pack();
        }

    }


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new StartGame().setVisible(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private List<JTextField> names;
    private List<JCheckBox> bots;
    // End of variables declaration

    private final static String RESOURCES = "src/resources/";

    class ImagePanel extends JComponent {
        private Image image;

        public ImagePanel(Image image) {
            this.image = image;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
        }
    }
}
