import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Gui{
    JFrame frame;
    Gui(JFrame aFrame){
        frame = aFrame;
    }

    void splashScreen(){
        JLabel splash = new JLabel(new ImageIcon("logo_resized.jpg"));
        this.frame.add(splash);
        this.frame.setVisible(true);
        try
		{
			Thread.sleep( 3000);
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
        splash.setVisible(false);
        return;
    }

    void playerEntry(){
        
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.gridx = 1;
        constraint.gridy = 0;

        // when the column value is changed, it changes the width of 
        JTextField textField = new JTextField(20);
        this.frame.add(textField, constraint);
        this.frame.setVisible(true);
        textField.addActionListener(new ActionListener(){
            // when enter is pressed, the text in the text box is recorded and removed
            @Override
            public void actionPerformed(ActionEvent event) {
                // records text in aString
                String aString = textField.getText();
                System.out.println(aString);
                // empties the text box
                textField.setText(null);
            }
        });
        return;
    }

    void run(){
        this.splashScreen();
        this.playerEntry();
    }

}