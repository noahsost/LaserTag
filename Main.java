import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Dimension;

public class Main extends JFrame{
    Gui gui;

    public Main(){
        this.setTitle("Photon");
        this.setSize(1600, 900);
        this.setMinimumSize(new Dimension(800, 450));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(110, 110, 110));
        this.setLayout(new GridBagLayout());
        gui = new Gui(this);
        gui.run(); 
    }

    public static void main(String[] args){
        new Main();
    }
}