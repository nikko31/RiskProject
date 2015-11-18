package risk.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Federico on 17/11/2015.
 * questo pannello è la parte inferiore della schermata di
 * gioco con su cui sono indicati:giocatore corrente,truppe dispolnibili,
 * fasi e bottone per mandare avanti le fasi
 */
public class GamePanel extends JPanel implements MouseListener {
    int count;
    private JTextField text;
    private JPanel phase;
    private JLabel ini;
    private JLabel position;
    private JLabel attack;
    private JLabel move;
    private JButton nextP;
    private JLabel troops;



    public GamePanel(){
        System.out.println("inizilizzo pannello di gioco");
        count=0;
        this.setLayout(new GridLayout(1, 4));

        text= new JTextField("player number###");
        text.setEditable(false);

        phase =new JPanel(new GridLayout(4,1));
        ini=new JLabel("Initialize");
        position=new JLabel("Position");
        attack=new JLabel("Attack");
        move=new JLabel("Move");

        nextP=new JButton("NEXT");
        nextP.addMouseListener(this);
        nextP.requestFocus();

        troops=new JLabel("troops:42");


        ini.setOpaque(true);
        position.setOpaque(true);
        attack.setOpaque(true);
        move.setOpaque(true);
        this.add(text);
        this.add(troops);
        phase.add(ini);
        phase.add(position);
        phase.add(attack);
        phase.add(move);
        this.add(phase);
        this.add(nextP);
        this.requestFocus();
        this.setVisible(true);



    }

    public void mousePressed(MouseEvent e) {


    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {
        if(e.getSource()==nextP){
            nextP.setBackground(Color.YELLOW);
        }
    }

    public void mouseExited(MouseEvent e) {
        if(e.getSource()==nextP){
            nextP.setBackground(Color.WHITE);
        }
    }

    public void mouseClicked(MouseEvent e) {
        System.out.println("ho clicckato");
        if(e.getSource()==nextP){
            System.out.println("clicked nextP");
            count=count%4;
            if(count==0){
                System.out.println("ini");
                move.setBackground(Color.WHITE);
                ini.setBackground(Color.YELLOW);
                move.repaint();
                ini.repaint();
                printAll(getGraphics());
            }
            else if(count==1){
                System.out.println("position");
                ini.setBackground(Color.WHITE);
                position.setBackground(Color.YELLOW);
                position.repaint();
                ini.repaint();
                printAll(getGraphics());

            }else if(count==2){
                System.out.println("attack");
                position.setBackground(Color.WHITE);
                attack.setBackground(Color.YELLOW);
                attack.repaint();
                position.repaint();
                printAll(getGraphics());

            }else if(count==3){
                System.out.println("move");
                attack.setBackground(Color.WHITE);
                move.setBackground(Color.YELLOW);
                move.repaint();
                attack.repaint();
                printAll(getGraphics());

            }
            count++;

        }

    }




}

