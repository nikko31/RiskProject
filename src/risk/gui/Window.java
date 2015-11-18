package risk.gui;

import javax.swing.*;

import java.awt.*;
import java.io.IOException;

/**
 * Created by Federico on 17/11/2015.
 */
public class Window extends JFrame implements InitOptionListener  {
    public int number_human,number_bot;

    public Window() throws IOException {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        InitialPanel panel=new InitialPanel("risk_menu.jpg");
        panel.addListener(this);
        this.add(panel);

        this.setSize(1000, 600);
        this.setVisible(true);
        this.setResizable(false);




    }


    public void playerNumber (int a,int b){

        System.out.println(a + " " + b);

        if(a==0 && b==0){
            JOptionPane.showMessageDialog(this,
                    "Error number players selected",
                    "warning",
                    JOptionPane.WARNING_MESSAGE);
        }
        else{
            //passa alla schermata di gioco
            number_human=a;
            number_bot=b;
            getContentPane().removeAll();
            System.out.println("numbers of players are arrived.... now open the new panel ");
            this.setLayout(new BorderLayout());
            //this.add(pannelo riskio,Borderlayout.CENTER);
            GamePanel gamepan =new GamePanel();
            this.add(gamepan,BorderLayout.SOUTH);
            this.repaint();
            printAll(getGraphics());
            gamepan.requestFocusInWindow();

        }



    }
    /*
    public static void main(String []args)throws IOException{
        Main_window risk_window=new Main_window();

    }
    */



}

