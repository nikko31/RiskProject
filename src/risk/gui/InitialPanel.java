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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Federico on 17/11/2015.
 * pannello iniziale della schermata di gioco
 * con richiesta del numero dei giocatori manda una notifica
 * al frame che in base alla al numero di giocatori
 * manda un dialog di errore o avvia il gioco;
 */
public class InitialPanel extends JPanel implements MouseListener,ActionListener {
    private int Bot_number;
    private int Human_number;
    public JComboBox<Integer> human_player;
    public JComboBox<Integer> bot_player;
    public List<InitOptionListener> listeners;

    private Image backgroundImage;


    public InitialPanel (String fileName) throws IOException {
        Bot_number=0;
        Human_number=0;

        listeners = new ArrayList<InitOptionListener>();
        backgroundImage = ImageIO.read(new File(fileName));
        this.setLayout(new BorderLayout());

        JButton start=new JButton("START");
        start.addMouseListener(this);

        //numero giocatori nei Jbox
        Integer num[]=new Integer[7];
        for(int i=0;i<7;i++){
            num[i]=new Integer(i);
        }
        human_player=new JComboBox<Integer>(num);
        human_player.addActionListener(this);

        bot_player=new JComboBox<Integer>(num);
        bot_player.addActionListener(this);
        JPanel select_panel=new JPanel(new GridLayout(2,3));

        JLabel bot=new JLabel("SELECT BOTS NUMBER");
        JLabel human=new JLabel("SELECT HUMANS NUMBER");
        JLabel info=new JLabel("INSERT PLAYER VALUE:");

        select_panel.add(start);
        select_panel.add(human);
        select_panel.add(bot);
        select_panel.add(info);
        select_panel.add(human_player);
        select_panel.add(bot_player);

        this.add(select_panel, BorderLayout.SOUTH);
        this.setVisible(true);



    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background image.
        g.drawImage(backgroundImage, 0, 0,1000,600, this);
    }



    public void mousePressed(MouseEvent e) {
        //
    }

    public void mouseReleased(MouseEvent e) {
        //
    }

    public void mouseEntered(MouseEvent e) {
        //
    }

    public void mouseExited(MouseEvent e) {
        //
    }

    public void mouseClicked(MouseEvent e) {
        System.out.println("good guy...... you select to start a game");
        System.out.println( Bot_number+ Human_number);
        if( Bot_number+ Human_number>7 || Bot_number+Human_number<2 ){

            System.out.println("Sorry players number too high or too small");
            notifyListeners(0,0);
        }
        else{

            System.out.println("correct number of player game can start ");
            notifyListeners(Human_number,Bot_number);

        }
    }

    public void actionPerformed(ActionEvent e) {
        if(human_player==(JComboBox < Integer >)e.getSource()){
            this.Human_number=(int)human_player.getSelectedItem();
            System.out.println("number of human selected: " + Human_number);
        }

        if(bot_player==(JComboBox < Integer >)e.getSource()){
            this.Bot_number=(int)bot_player.getSelectedItem();
            System.out.println("number of bot selected: " + Bot_number);
        }



    }

    /*dovevo usare la classe astratta,ma è inutile perchè
     *non posso estendere due classi contemporaneamente;
     *
     *
     */

    public void addListener(InitOptionListener toAdd) {
        listeners.add(toAdd);
    }

    public void notifyListeners(int a,int b) {
        for (InitOptionListener sl : listeners)
            sl.playerNumber(a,b);
    }






}

