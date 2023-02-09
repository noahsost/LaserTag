import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;


public class Main extends JFrame{
    public Main(){
        this.setSize(1600, 900);
        JLabel splash = new JLabel(new ImageIcon("logo_resized.jpg"));
        this.add(splash);
        this.setVisible(true);
        try
		{
			Thread.sleep( 3000);
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

        splash.setVisible(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args){
        new Main();
    }
}