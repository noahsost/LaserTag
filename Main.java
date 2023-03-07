import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JFrame;

public class Main extends JFrame{
    Gui gui;
    Database database = new Database();

    public Main(){
        this.setTitle("Photon");
        this.setSize(1600, 900);
        this.setMinimumSize(new Dimension(800, 450));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(200, 200, 200));
        this.setLayout(new GridBagLayout());
        gui = new Gui(this, database);
        gui.run(); 
    }

    public static void main(String[] args){
        new Main();
    }
}