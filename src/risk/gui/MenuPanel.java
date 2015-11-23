package risk.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by DarkLinux on 18/11/15.
 */
public class MenuPanel extends JPanel implements MouseListener, ActionListener {
    private int bot_number;
    private int human_number;
    public JComboBox<Integer> human_player;
    public JComboBox<Integer> bot_player;
    private Image backgroundImage;
    public InitOptionListener listeners;

    public MenuPanel(String bg) throws IOException {

        bot_number = 0;
        human_number = 0;
        backgroundImage = ImageIO.read(new File(bg));

        Integer num[] = new Integer[7];
        for (int i = 0; i < 7; i++) {
            num[i] = new Integer(i);
        }
        human_player = new JComboBox<Integer>(num);
        human_player.addActionListener(this);

        bot_player = new JComboBox<Integer>(num);
        bot_player.addActionListener(this);

        JButton start = new JButton("Start");
        start.addMouseListener(this);

        JPanel select_panel = new JPanel(new GridLayout(2, 3));
        select_panel.add(start);
        select_panel.add(new JLabel("SELECT HUMANS NUMBER"));
        select_panel.add(new JLabel("SELECT BOTS NUMBER"));
        select_panel.add(new JLabel("INSERT PLAYER VALUE:"));
        select_panel.add(human_player);
        select_panel.add(bot_player);

        this.setLayout(new BorderLayout());
        this.add(select_panel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), null);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        System.out.println("Start Button pressed ");
        notifyListeners(human_number, bot_number);


    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (human_player == (JComboBox<Integer>) actionEvent.getSource())
            this.human_number = (int) human_player.getSelectedItem();

        if (bot_player == (JComboBox<Integer>) actionEvent.getSource())
            this.bot_number = (int) bot_player.getSelectedItem();

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }


    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    public void notifyListeners(int a, int b) {
        listeners.playersNumber(a, b);
    }

    public void setListener(InitOptionListener toAdd) {
        listeners = toAdd;
    }
}
