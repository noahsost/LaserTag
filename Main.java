import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.Color;

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