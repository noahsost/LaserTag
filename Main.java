import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Color;
import java.awt.Toolkit;


public class Main extends JFrame{
    Gui gui;

    public Main(){
        this.setTitle("Photon");
        this.setSize(1600, 900);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.BLACK);
        this.setLayout(new GridBagLayout());
        gui = new Gui(this);
        gui.run();
        
    }

    public static void main(String[] args){
        new Main();

    }
}